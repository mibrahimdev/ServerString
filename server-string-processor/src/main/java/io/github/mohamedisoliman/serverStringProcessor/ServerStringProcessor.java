package io.github.mohamedisoliman.serverStringProcessor;

import io.github.mohamedisoliman.server_string_annotations.DynamicString;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * Created by Mohamed Ibrahim on 9/17/18.
 */
public class ServerStringProcessor extends AbstractProcessor {

  private Messager messager;
  private Types typesUtil;
  private Elements elementsUtil;
  private Filer filer;

  @Override
  public synchronized void init(ProcessingEnvironment processingEnv) {
    super.init(processingEnv);
    filer = processingEnv.getFiler();
    messager = processingEnv.getMessager();
    elementsUtil = processingEnv.getElementUtils();
    typesUtil = processingEnv.getTypeUtils();
  }

  @Override
  public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

    return false;
  }

  @Override public Set<String> getSupportedAnnotationTypes() {
    return new TreeSet<>(Collections.singletonList(DynamicString.class.getCanonicalName()));
  }
}
