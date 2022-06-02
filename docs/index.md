# Java Modules

## JPMS (Java Platform Module System)

**JPMS** was introduced since **Java 9**, allowing modular software development on the Java platform.

Before JPMS we usually structure our classes in different packages, according to different criteria, and then package (zip) them into **JAR files** (which we call **libraries**). Probably, some classes in a JAR file depend on classes packaged in another JAR file. That's what we called: **dependencies**. 

Managing these dependencies by our selves can be hard, specially when we have to deal with transitive dependencies. For this reason, we need tools which help us to manage those dependencies, like **Maven** or **Gradle**. JPMS does not pretend to be a replace for those building tools.

For a Java developer a JAR file is a **library**, a high level artifact which provide us with new functionality. We import a library, and have full access to the classes it contains. So, combining those **libraries** we can build more complex software, like fitting pieces in a puzzle, and that's wonderful!

A class in library 1 can import any class from library 2, without restrictions. And even more, the imported class in library 2 can import a class from library 1. This permissiveness allows cycles of dependencies to occur between libraries. **Java Modules provide us with strong encapsulation, and those cycles are forbidden.**

codebase spread across multiple JAR files has a good chance of cycles between classes in different JAR files, but cycles between classes in different modules are forbidden.

The only developer who should modularize a codebase is its author, so until that happens, the module system has to provide a way for code in modules to reach out to code not in modules. This led to the design of automatic modules which are so well explained in this book.

to building maintainable software, modularity is a key principle.

use modules in a way to maximize maintainability and extensibility.

Learning by doing

## 1. Modularity matters

Turning an inner class into a lambda is a fairly small and localized change within a single class. Modularizing an application affects design, compilation, packaging, deployment, and so on. Clearly, it’s much more than just another language feature.

### What is modularity?

goal of modularity (managing and reducing complexity),

modularization is the act of decomposing a system into self-contained but interconnected modules. Modules are identifiable artifacts containing code, with metadata describing the module and its relation to other modules.

An application then consists of multiple modules working together.

three core tenets:

- Strong encapsulation: a clear line is drawn between code that is publicly usable and code that is deemed an internal implementation detail. This prevents accidental or unwanted coupling between modules: you simply cannot use what has been encapsulated. Consequently, encapsulated code may change freely without affecting users of the module.

- Well-defined interfaces: Code that is not encapsulated is, by definition, part of the public API of a module. Since other modules can use this public code, it must be managed with great care. A breaking change in nonencapsulated code can break other modules that depend on it. modules should expose well-defined and stable interfaces to other modules.

- Explicit dependencies: Modules often need other modules to fulfill their obligations. Such dependencies must be part of the module definition, in order for modules to be self-contained. Explicit dependencies give rise to a module graph:nodes represent modules, and edges represent dependencies between modules.

To use a module, knowing its public API is enough. Also,
a module exposing well-defined interfaces while encapsulating its implementation
details can readily be swapped with alternative implementations conforming to the
same API.

Modularity is an architectural principle that can prevent these problems to a high degree when applied correctly.

### Before Java 9

#### External tooling to manage dependencies: Maven

One of the problems solved by the Maven build tool is the dependency management. Dependencies between JARs are defined in a **Project Object Model (POM)** file. So, Maven's great success is due to its canonical repository called Maven Central, where almost all Java libraries are published along with their POMs. Various other build tools such as Gradle or Ant (with Ivy) use the same repository. They all automatically resolve (transitive) dependencies for you at compile-time.

### JARs as modules?

JAR files seem to be the closest we can get to modules pre-Java 9. They have a
name, group related code, and can offer well-defined public interfaces.

![](C:\Users\fvarrui\GitHub\java-modules\docs\assets\2022-05-25-13-11-34-image.png)

Classes are supposed to be internal classes of the libraries. For example, `com.google.common.base.internal.Finalizer` is used in Guava itself, but is not part of the official API. It's a public class, since other Guava packages use `Finalizer`. Unfortunately, this also means there's no impediment for `com.myapp.Main` to use classes like `Finalizer`. In other words, there's no strong encapsulation.

The same holds for internal classes from the Java platform itself. Packages such as
`sun.misc` have always been accessible to application code, even though documentation warns they are unsupported APIs that should not be used. Despite this warning, utility classes such as `sun.misc.BASE64Encoder` are used in application code all the time. Technically, that code may break with any update of the Java runtime, since they are internal implementation classes. Lack of encapsulation essentially forced those classes to be considered semipublic APIs anyway, since Java highly values backward compatibility. This is an unfortunate situation, arising from the lack of true encapsulation.ç

```bash
java -classpath lib/guava-19.0.jar:\
                lib/hibernate-validator-5.3.1.jar:\
                lib/jboss-logging-3.3.0Final.jar:\
                lib/classmate-1.3.1.jar:\
                lib/validation-api-1.1.0.Final.jar \
    -jar MyApplication.jar
```

Setting up the correct classpath is up to the user.

### Classpath Hell

![](C:\Users\fvarrui\GitHub\java-modules\docs\assets\2022-05-25-12-56-04-image.png)

The classpath is used by the Java runtime to locate classes. classes. In our example, we run Main, and all classes that are directly or indirectly referenced from this class need to be loaded at some point. You can view the classpath as a list of all classes that may be loaded at runtime.

There's no notion of JARs or logical grouping anymore. All classes are sequenced
into a flat list, in the order defined by the -classpath argument. When the JVM
loads a class, it reads the classpath in sequential order to find the right one. As soon as the class is found, the search ends and the class is loaded.

What if a class cannot be found on the classpath? Then you will get a run-time exception. Because classes are loaded lazily, this could be triggered when some
unlucky user clicks a button in your application for the first time. The JVM cannot
efficiently verify the completeness of the classpath upon starting. There is no way to
tell in advance whether the classpath is complete, or whether you should add another
JAR. Obviously, that’s not good.

We can let Maven to construct the right set of JARs to put on the classpath, based on the explicit dependency information in POMs. Since Maven resolves dependencies transitively, it's possible for two versions of the same library (say, Guava 19 and Guava 18) to end up in this set.

### Java 9 modules

With Java 9, we get a new ally in the quest for well-structured applications: the Java module system.

Two main goals were defined:

- Modularize the JDK itself.

- Offer a module system for applications to use.

The module system introduces a native concept of modules into the Java language
and runtime. Modules can either export or strongly encapsulate packages. Furthermore, they express dependencies on other modules explicitly. As you can see, all three tenets of modularity are addressed by the Java module system.

![](C:\Users\fvarrui\GitHub\java-modules\docs\assets\2022-05-25-13-13-29-image.png)

The most essential platform module in the modular JDK is `java.base`. It exposes packages such as java.lang and java.util, which no other module can do without. Because you cannot avoid using types from these packages, every module requires `java.base` implicitly.

Accidental dependencies on code from other nonreferenced modules can be prevented. The toolchain knows which additional modules are necessary for running a module by inspecting its (transitive) dependencies, and optimizations can be applied using this knowledge.

Most important benefits of the Java Platform Module System:

- Reliable configuration: The module system checks whether a given combination of modules satisfies all dependencies before compiling or running code.

- Strong encapsulation: Modules explicitly choose what to expose to other modules. Accidental dependencies on internal implementation details are prevented.

- Scalable development: Explicit boundaries enable teams to work in parallel while still creating maintainable codebases. Only explicitly exported public types are shared, creating boundaries that are automatically enforced by the module system.

- Security: Gaining reflective access to sensitive internal classes is not possible anymore.

- Optimization: Because the module system knows which modules belong together, including platform modules, no other code needs to be considered during JVM startup.

## 2. Modules and the modular JDK

Take CORBA—once considered the future of enterprise computing, and now a
mostly forgotten technology. (To those who are still using it: we feel for you.) The
classes supporting CORBA in the JDK are still present in rt.jar to this day. Each and
every distribution of Java, regardless of the applications it runs, includes those
CORBA classes. No matter whether you use CORBA or not, the classes are there.
Carrying this legacy in the JDK results in unnecessary use of disk space, memory,
and CPU time. In the context of using resource-constrained devices, or creating
small containers for the cloud, these resources are in short supply. Not to mention the
cognitive overhead of obsolete classes showing up in IDE autocompletions and
documentation during development.
Simply removing these technologies from the JDK isn’t a viable option, though.
Backward compatibility is one of the most important guiding principles for Java.
Removal of APIs would break a long streak of backward compatibility. Although it
may affect only a small percentage of users, plenty of people are still using
technologies like CORBA. In a modular JDK, people who aren’t using CORBA can
choose to ignore the module containing CORBA.

### The Modular JDK

You already saw a glimpse of how JDK 9 is split into modules in Figure 1-2. The
JDK now consists of about 90 platform modules, instead of a monolithic library.

![](C:\Users\fvarrui\GitHub\java-modules\docs\assets\2022-05-25-13-18-37-image.png)

There are no cycles in this graph. That’s not by accident: the Java module system
does not allow compile-time circular dependencies between modules.

>  Tip: You can get the full list of platform modules by running:
> 
> ```bash
> java --list-modules
> ```

`java.se` and `java.se.ee`. These are so-called aggregator modules, and they serve to logically group several other modules.

### Module Descriptors

The module descriptor lives in a file called `module-info.java`.

```java
module java.prefs {
    requires java.xml;
    exports java.util.prefs;
}
```

module names must be unique

As with package names, you can use conventions such as reverse DNS notation (e.g.,
com.mycompany.project.somemodule)

A dependency is declared with the requires keyword followed by a
module name

The implicit dependency on java.base may
be added to a module descriptor. Doing so adds no value, similar to how you can
(but generally don’t) add "import java.lang.String" to a class using strings.

A module descriptor can also contain exports statements. Strong encapsulation is
the default for modules. Only when a package is explicitly exported, like
java.util.prefs in this example, can it be accessed from other modules

Packages
inside a module that are not exported are inaccessible from other modules by default.

Other modules cannot refer to types in encapsulated packages, even if they have a
dependency on the module

### Readability

Reading another module means you can access types from its exported
packages. You set up readability relations between modules through requires
clauses in the module descriptor. By definition, every module reads itself. A module
that requires another module reads the other module.

### Accesibility

Normal Java accessibility rules are still in play after readability has been
established.

![](C:\Users\fvarrui\GitHub\java-modules\docs\assets\2022-05-25-13-24-13-image.png)

Combining accessibility and readability provides the strong encapsulation guarantees we so desire in a module

Only public types in exported packages are accessible in other modules. If a type is
in an exported package but not public, traditional accessibility rules block its use. If
it is public but not exported, the module system’s readability rules prevent its use.
Violations at compile-time result in a compiler error, whereas violations at run-time
result in IllegalAccessError.

As of Java 9, public means
public only to all other packages inside that module. Only when the package
containing the public type is exported can it be used by other modules. This is
what strong encapsulation is all about. It forces developers to carefully design a
package structure where types meant for external consumption are clearly
separated from internal implementation concerns.

Even reflection cannot break strong
encapsulation.

### Implied Readability

Readability is not transitive by default.

![](C:\Users\fvarrui\GitHub\java-modules\docs\assets\2022-05-25-13-25-43-image.png)

Figure 2-2. Readability is not transitive: java.desktop does not read java.xml through java.prefs

Sometimes you do want read relations to be transitive

The requires keyword is now followed by the transitive modifier, slightly
changing the semantics. A normal requires allows a module to access types in
exported packages from the required module only. requires transitive means
the same and more. In addition, any module requiring java.sql will now
automatically be requiring java.logging and java.xml. That means you get access
to the exported packages of those modules as well by virtue of these implied
readability relations. With requires transitive, module authors can set up
additional readability relations for users of the module.

A nontransitive dependency means the
dependency is necessary to support the internal implementation of that module. A
transitive dependency means the dependency is necessary to support the API of the
module.

implied readability: it can be used to aggregate several modules into a single new
module.

```java
module java.se {
    requires transitive java.desktop;
    requires transitive java.sql;
    requires transitive java.xml;
    requires transitive java.prefs;
    // .. many more
}
```

### Qualified exports

you’ll want to expose a package only to certain other modules. You
can do this by using qualified exports in the module descriptor.

```java
module java.xml {
...
exports com.sun.xml.internal.stream.writers to java.xml.ws
...
}
```

module sharing useful internals with another platform
module. The exported package is accessible only by the modules specified after to.

In general, avoid using qualified exports between modules in an application.

One of the great
things about modules is that you effectively decouple producers from consumers of
APIs. Qualified exports break this property because now the names of consumer
modules are part of the provider module’s descriptor.



### Module Resolution and the Module Path

The Java compiler and runtime use module descriptors to resolve the right
modules when compiling and running modules. Modules are resolved from the
module path, as opposed to the classpath

The Java runtime and compiler know
exactly which module to resolve from the module path when looking for types from
a given package

When you want to run an application packaged as a module, you need all of its
dependencies as well. Module resolution is the process of computing a minimal
required set of modules given a dependency graph and a root module chosen from
that graph. Every module reachable from the root module ends up in the set of
resolved modules.

the dependency graph must be acyclic.

When running app, the modules are resolved in this
way, and the module system gets the modules from the module path.

Another check is for uniqueness of exported packages. Only
one module on the module path may expose a given packag

Versions do not play a
role during module resolution

### Using the Modular JDK Without Modules

Java 9 can be used like previous versions of Java,
without moving your code into modules. The module system is completely opt-in for
application code

when you compile this code without a module
descriptor, put it on the classpath, and run it, it will just work

Code compiled and loaded outside a module ends up in the unnamed module. In
contrast, all modules you’ve seen so far are explicit modules, defining their name in
module-info.java. The unnamed module is special: it reads all other modules,
including the java.logging module in this case.

You need to be aware of two more things when using the classpath in Java 9. First,
because the platform is modularized, it strongly encapsulates internal
implementation classes. In Java 8 and earlier, you could use these unsupported
internal APIs without repercussions. With Java 9, you cannot compile against
encapsulated types in platform modules. To aid migration, code compiled on earlier
versions of Java using these internal APIs continues to run on the JDK 9 classpath
for now.

The second thing to be aware of when compiling code in the unnamed module is that java.se is taken as the root module during compilation.

## Working with modules



### Versioned Modules

Talking about modules for frameworks and libraries inevitably leads to questions
around versioning. Modules are independently deployable and reusable units.
Applications are built by combining the right deployment units (modules). Having
just a module name is not sufficient to select the right modules that work together.
You need versions as well.

Modules in the Java module system cannot declare a version in module-info.java.
Still, it is possible to attach version information when creating a modular JAR. You
can set a version by using the `--module-version=<V>` flag of the jar tool. The version V is set as an attribute on the compiled module-info.class and is available at
run-time. Adding a version to your modular JARs is a good practice, especially the
for API modules discussed earlier in this chapter.

Selection and retrieval of
the right versions of dependencies are delegated to existing build tools. Maven and
Gradle et al. handle this by externalizing the dependency version information into a
POM file. Other tools may use other means, but the fact remains this information
must be stored outside the module.

![](C:\Users\fvarrui\GitHub\java-modules\docs\assets\2022-05-30-16-19-27-image.png)

1. A build tool such as Maven or Gradle downloads dependencies from a repository
   such as Maven Central. Version information in a build descriptor (for example,
   pom.xml) controls which versions are downloaded.

2. The build tool sets up the module path with the downloaded versions.

3. Then, the Java compiler or runtime resolves the module graph from the module
   path, which contains the correct versions without duplicates for the application to
   work. Duplicate versions of a module result in an error.
