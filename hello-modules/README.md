# Hello Modules

Our first modular Java application.   

## Create the project

Generate a new simple Maven project from the command line:

```bash
mvn archetype:generate \
    -DarchetypeGroupId=org.apache.maven.archetypes \ 
    -DarchetypeArtifactId=maven-archetype-quickstart
    -DgroupId=modules.workshop.hello \
    -DartifactId=hello-modules \
    -Dversion=1.0.0
```

Or just create a new Maven project with your favourite IDE.

## Set up the POM

Set Java version to 17 (or at least 9+) and source code encoding to UTF-8:

```xml
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
</properties>
```

Set `maven-compiler-plugin`to version 3.8 (for compiling Java modules descriptors):

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.8.0</version>
</plugin>
```

Add `maven-jar-plugin` to create a runnable JAR:

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-jar-plugin</artifactId>
    <version>3.2.2</version>
    <configuration>
        <archive>
            <manifest>
                <mainClass>modules.workshop.hello.HelloModules</mainClass>
            </manifest>
        </archive>
    </configuration>
</plugin>
```

## Create a class

Create the `HelloModules` main class which just shows a dialog in the GUI:

```java
package modules.workshop.hello;

import javax.swing.JOptionPane;

public class HelloModules {

    public static void main(String[] args) {
        // show a dialog 
        JOptionPane.showMessageDialog(
                null, 
                "Welcome to the wonderful world of Java modules!", // dialog mesage 
                "Java Modules", // dialog title 
                JOptionPane.INFORMATION_MESSAGE // dialog type
            );
    }

}
```

We can run the app from the IDE to check if works fine:

![](C:\Users\fvarrui\GitHub\java-modules\hello-modules\assets\2022-06-01-23-53-01-image.png)

## Create the module descriptor: `module-info.java`

Create the file `module-info.java` in the root package of the module:

```java
module hello.modules {	
	requires java.desktop;
}
```

> `java.desktop` module is required for using all related desktop stuff (awt, swing, ...)
> 
> `java.base` module is always implicitly required by all modules.

> **Eclipse tip**: generate this file in **Project context menu** > **Configure** > **Create module-info.java**.

## Package the project

Compile and package the project:

```bash
mvn package
```

## Run the modular application

Run the application with modules from `MANIFEST.MF` main class:

```bash
java --module-path target/hello-modules-1.0.0.jar --module hello.modules
```

## Run an specific class inside the module

Run the application with modules from an specific class:

```bash
java --module-path target/hello-modules-1.0.0.jar --module hello.modules/modules.workshop.hello.HelloModules
```

## Run as a non-modular application (LEGACY)

Run the application in legacy mode (non-modular):

```bash
java --class-path target/hello-modules-1.0.0.jar modules.workshop.hello.HelloModules
```

## Print module description

To show our brand new module description:

```bash
jar --describe-module --file=target/hello-modules-1.0.0.jar
```

To show a system module description:

```bash
java --describe-module java.base
```

To list all available system modules:

```bash
java --list-modules
```

## Discover modules

```bash
jdeps
```

## Create a custom Java runtime image

Create a reduced runtime image specifically created for this application with a launcher script:

```bash
jlink --compress=2 --no-header-files --no-man-pages --strip-debug \
    --module-path target/hello-modules-0.0.1.jar \
    --add-modules hello.modules \
    --output target/runtime
    --launcher hello=hello.modules/modules.workshop.hello.HelloModules
```