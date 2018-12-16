# ServerString
Bind Server strings to your Views (mini ButterKnife for Dynamic strings )

## Usage
In your Application class or after getting your Strings from Server

```java
public class App extends Application {

  @Override public void onCreate() {
    super.onCreate();
    ServerString.getInstance().setJson(JsonSample.getJsonSource());
  }
}
```
Then in your Activity annotate your TextView with key and View resource Id

```java
@DynamicString(serverKey = R.string.server_key_glossdiv_title, viewId = R.id.text_3)
  TextView textView3;
```
Then in Activity onCreate call     
```java
ServerString.bind(this);
```
