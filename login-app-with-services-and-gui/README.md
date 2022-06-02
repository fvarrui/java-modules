# login-app-with-services-and-gui

JavaFX application for users to login, using a login service with a decoupled implementation.

## Build the app

Run next command in the parent project:

```bash
mvn clean package
```

## Change to login-gui/target directory

In order to run the following commands, change to `login-gui/target` directory:

```bash
cd login-gui/target
```

## Run the app using the modulepath

```bash
java --module-path libs:login-gui-1.0.0.jar --module login.gui/modules.workshop.login.gui.LoginApplication
```

## Run the app using the classpath (in legacy mode)

Loading all our classes in the unnamed module:

```bash
java --class-path libs/*:login-gui-1.0.0.jar --module-path libs --add-modules javafx.controls,javafx.fxml modules.workshop.login.gui.LoginApplication
```

## Find dependencies between modules

```bash
jdeps --multi-release 17 --ignore-missing-deps --print-module-deps --module-path libs;login-gui-1.0.0.jar libs/*.jar login-gui-1.0.0.jar
```

## Generate a custom Java runtime image

```bash
jlink --compress=2 --no-header-files --no-man-pages --strip-debug --add-modules <last command result> --output runtime
```

## Generate dot files (module dependencies graph)

```bash
jdeps --multi-release 17 --ignore-missing-deps --dot-output dotoutput --module-path libs;login-gui-1.0.0.jar libs\*.jar login-gui-1.0.0.jar
```

## Extras

[DOT graph visualizer (engine=fdp)](https://dreampuf.github.io/GraphvizOnline/)

