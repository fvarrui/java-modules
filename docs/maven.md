# Maven and Java Modules

Maven uses the `maven-compiler-plugin` to compile our source code into `.class`files.

In case we want to compile a modularized project (a project containing `module-info.java` module descriptor file), we must use `maven-compiler-plugin:3.8.0` or higher.

## What version do I have?

We can discover which version of any plugin is being used by Maven with the `help:describe` goal:

```bash
$ mvn help:describe -Dplugin=<groupIp>:<artifactId>
```

In our case, we want to see what `maven-compiler-plugin` version is being used:

```bash
$ mvn help:describe -Dplugin=org.apache.maven.plugins:maven-compiler-plugin

[INFO] Scanning for projects...
[INFO]
[INFO] -------------------< io.github.fvarrui:rest-client >--------------------
[INFO] Building rest-client 0.0.1-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- maven-help-plugin:3.2.0:describe (default-cli) @ rest-client ---
[INFO] org.apache.maven.plugins:maven-compiler-plugin:3.1

Name: Maven Compiler Plugin
Description: The Compiler Plugin is used to compile the sources of your
  project.
Group Id: org.apache.maven.plugins
Artifact Id: maven-compiler-plugin
Version: 3.1
Goal Prefix: compiler

This plugin has 3 goals:

compiler:compile
  Description: Compiles application sources

compiler:help
  Description: Display help information on maven-compiler-plugin.
    Call mvn compiler:help -Ddetail=true -Dgoal=<goal-name> to display
    parameter details.

compiler:testCompile
  Description: Compiles application test sources.

For more information, run 'mvn help:describe [...] -Ddetail'

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  1.621 s
[INFO] Finished at: 2022-05-27T11:26:24+01:00
[INFO] ------------------------------------------------------------------------
```

> Last output shows that Maven is using version `3.1` , and we need at least `3.8.0` for compiling Java Modules.

## Change version

You just have to add next `plugin`entry to your `pom.xml` in order to upgrade `maven-compiler-plugin` version:

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.8.0</version>
</plugin>
```

And then we can check that now is using the newer version:

```bash
$ mvn help:describe -Dplugin=org.apache.maven.plugins:maven-compiler-plugin

[INFO] Scanning for projects...
[INFO]
[INFO] -------------------< io.github.fvarrui:rest-client >--------------------
[INFO] Building rest-client 0.0.1-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- maven-help-plugin:3.2.0:describe (default-cli) @ rest-client ---
[INFO] org.apache.maven.plugins:maven-compiler-plugin:3.8.0

Name: Apache Maven Compiler Plugin
Description: The Compiler Plugin is used to compile the sources of your
  project.
Group Id: org.apache.maven.plugins
Artifact Id: maven-compiler-plugin
Version: 3.8.0
Goal Prefix: compiler

This plugin has 3 goals:

compiler:compile
  Description: Compiles application sources

compiler:help
  Description: Display help information on maven-compiler-plugin.
    Call mvn compiler:help -Ddetail=true -Dgoal=<goal-name> to display
    parameter details.

compiler:testCompile
  Description: Compiles application test sources.

For more information, run 'mvn help:describe [...] -Ddetail'

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  1.417 s
[INFO] Finished at: 2022-05-27T11:45:15+01:00
[INFO] ------------------------------------------------------------------------
```
