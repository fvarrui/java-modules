# Java Modules

## JPMS (Java Platform Module System)

**JPMS** was introduced since **Java 9**, allowing modular software development on the Java platform.

Before JPMS we usually structure our classes in different packages, according to different criteria, and then package (zip) them into **JAR files** (which we call **libraries**). Probably, some classes in a JAR file depend on classes packaged in another JAR file. That's what we called: **dependencies**. 

Managing these dependencies by our selves can be hard, specially when we have to deal with transitive dependencies. For this reason, we need tools which help us to manage those dependencies, like **Maven** or **Gradle**. JPMS does not pretend to be a replace for those building tools.

For a Java developer a JAR file is a **library**, a high level artifact which provide us with new functionality. We import a library, and have full access to the classes it contains. So, combining those **libraries** we can build more complex software, like fitting pieces in a puzzle, and that's wonderful!

A class in library 1 can import any class from library 2, without restrictions. And even more, the imported class in library 2 can import a class from library 1. This permissiveness allows cycles of dependencies to occur between libraries. 

Java Modules provide us with strong encapsulation, and those cycles are forbidden. 







codebase spread across multiple JAR files has a good chance of cycles between classes in different JAR files, but cycles between classes in different modules are forbidden.

The only developer who should modularize a codebase is its author, so until that happens, the module system has to provide a way for code in modules to reach out to code not in modules. This led to the design of automatic modules which are so well explained in this book.

to building maintainable software, modularity is a key principle.

use modules in a way to maximize maintainability and extensibility.

Learning by doing
