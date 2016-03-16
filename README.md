# AndroidReflectKit
反射操作封装
## Import
[JitPack](https://jitpack.io/)

Add it in your project's build.gradle at the end of repositories:

```gradle
repositories {
  // ...
  maven { url "https://jitpack.io" }
}
```

Step 2. Add the dependency in the form

```gradle
dependencies {
  compile 'com.github.vilyever:AndroidReflectKit:1.0.4'
}
```

## Usage
```java
ArrayList<Field> fields = ReflectKit.getFields(this.getClass());
ArrayList<Method> fields = ReflectKit.getMethods(this.getClass());
```

## License
[Apache License Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.txt)
