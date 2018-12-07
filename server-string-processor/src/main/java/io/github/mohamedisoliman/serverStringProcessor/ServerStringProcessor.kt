package io.github.mohamedisoliman.serverStringProcessor

import com.squareup.javapoet.ClassName
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeSpec
import io.github.mohamedisoliman.server_string_annotations.DynamicString
import java.io.IOException
import java.util.HashSet
import java.util.TreeSet
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Filer
import javax.annotation.processing.Messager
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement
import javax.lang.model.util.ElementFilter
import javax.lang.model.util.Elements
import javax.lang.model.util.Types
import javax.tools.Diagnostic

/**
 * Created by Mohamed Ibrahim on 9/17/18.
 */
class ServerStringProcessor : AbstractProcessor() {

  private lateinit var messager: Messager
  private lateinit var typesUtil: Types
  private lateinit var elementsUtil: Elements
  private lateinit var filer: Filer

  @Synchronized override fun init(processingEnv: ProcessingEnvironment) {
    super.init(processingEnv)
    filer = processingEnv.filer
    messager = processingEnv.messager
    elementsUtil = processingEnv.elementUtils
    typesUtil = processingEnv.typeUtils
  }

  override fun process(
    supportedAnnotations: Set<TypeElement>,
    roundEnvironment: RoundEnvironment
  ): Boolean {
    if (!roundEnvironment.processingOver()) {

      // find all the classes that uses the supported annotations
      val typeElements = HashSet<TypeElement>()
      roundEnvironment.rootElements
          .filter { it is TypeElement }
          .forEach { item ->
            item.enclosedElements.forEach { subElement ->
              subElement.annotationMirrors.forEach { mirror ->
                supportedAnnotations.filter { mirror.annotationType.asElement() == it }
                    .forEach {
                      typeElements.add(item as TypeElement)
                    }
              }
            }
          }

      messager.printMessage(Diagnostic.Kind.WARNING, typeElements.size.toString())

      //create the wrapper class
      typeElements.forEach {

        val packageName = elementsUtil.getPackageOf(it)
            ?.qualifiedName.toString()

        val typeName = it.simpleName
            .toString()
        val className = ClassName.get(packageName, typeName)

        val generatedClassName = ClassName
            .get(packageName, "${typeName}_ServerStringBinding")

        messager.printMessage(Diagnostic.Kind.WARNING, typeName)

        // define the wrapper class
        val classBuilder = TypeSpec.classBuilder(generatedClassName)
            .addModifiers(Modifier.PUBLIC)

        val targetParamName = "target"

        // add constructor
        classBuilder.addMethod(
            MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(className, targetParamName)
                .addStatement("\$N(\$N)", "bindServerStrings", targetParamName)
                .build()
        )

        // add method that maps the views with id

        val bindViewsMethodBuilder = MethodSpec.methodBuilder("bindServerStrings")
            .addModifiers(Modifier.PRIVATE)
            .returns(Void.TYPE)
            .addParameter(className, targetParamName)

        ElementFilter.fieldsIn(it.enclosedElements)
            .forEach { variableElement ->
              val bindView = variableElement.getAnnotation(DynamicString::class.java)
              if (bindView != null) {
                bindViewsMethodBuilder.addStatement(
                    "((\$T)\$N.findViewById(\$L)).setText(\$T.getStringValue(\$N.getString(\$L)))",
                    variableElement,
                    targetParamName,
                    bindView.viewId,
                    ClassName.get("io.github.mohamedisoliman.binder", "ServerString"),
                    targetParamName,
                    bindView.serverKey
                )
              }
            }
        classBuilder.addMethod(bindViewsMethodBuilder.build())

        try {
          JavaFile.builder(
              packageName,
              classBuilder.build()
          )
              .build()
              .writeTo(filer)
        } catch (e: IOException) {
          messager.printMessage(Diagnostic.Kind.ERROR, e.toString(), it)
        }

      }
    }
    return true
  }

  override fun getSupportedSourceVersion(): SourceVersion {
    return SourceVersion.latestSupported()
  }

  override fun getSupportedAnnotationTypes(): Set<String> {
    return TreeSet(listOf(DynamicString::class.java.canonicalName))
  }
}
