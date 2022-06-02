# login-app

Simple console application for users to login.

## Build the app

Run next command in the parent project:

```bash
mvn clean package
```

## Change to login-cli/target directory

In order to run the following commands, change to `login-cli/target` directory:

```bash
cd login-cli/target
```

## Run the app using the modulepath

```bash
java --module-path libs:login-cli-1.0.0.jar --module login.cli/modules.workshop.login.cli.Main
```

## Run the app using the classpath (in legacy mode)

Loading all our classes in the unnamed module:

```bash
java --class-path libs/*:login-cli-1.0.0.jar modules.workshop.login.cli.Main
```

## Find dependencies between modules

```bash
jdeps --multi-release 17 --ignore-missing-deps --print-module-deps --module-path libs;login-cli-1.0.0.jar libs\*.jar login-cli-1.0.0.jar
```

## Generate a custom Java runtime image

```bash
jlink --compress=2 --no-header-files --no-man-pages --strip-debug --add-modules java.base,java.desktop,java.logging,java.management,java.naming,java.security.jgss,java.security.sasl --output runtime
```

## Generate dot files (module dependencies graph)

```bash
jdeps --multi-release 17 --ignore-missing-deps --dot-output dotoutput --module-path libs;login-cli-1.0.0.jar libs\*.jar login-cli-1.0.0.jar
```

## Extras

[DOT graph visualizer (engine=fdp)](https://dreampuf.github.io/GraphvizOnline/)

