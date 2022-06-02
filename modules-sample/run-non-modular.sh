#!/usr/bin/env bash
echo "Running as non-modular Java app..."
java \
    -cp modular/target/modular-0.0.1-SNAPSHOT.jar:non-modular/target/non-modular-0.0.1-SNAPSHOT.jar:main-module/target/main-module-0.0.1-SNAPSHOT.jar \
    io.github.fvarrui.modules.main.Main