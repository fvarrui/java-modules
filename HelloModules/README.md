# Hello Modules

First modular Java application.

## Package the project

```bash
mvn packaage
```

## Run the module

```bash
java --module-path target/HelloModules-0.0.1.jar --module hellomodules
```

## Run an specific class inside the module

```bash
java --module-path target/HelloModules-0.0.1.jar --module hellomodules/io.github.fvarrui.modules.hello.HelloModules
```

## Print module description

```bash
jar --describe-module --file=target/HelloModules-0.0.1.jar
```

## Create a custom runtime image

```bash
jlink --module-path "%JAVA_HOME%\jmods" --module-path target\HelloModules-0.0.1.jar --add-modules hellomodules --output target\hellomodules-image
```