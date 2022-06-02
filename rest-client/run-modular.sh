#!/bin/bash
echo "Running as modular Java app..."
java \
    -cp non-modular/target/non-modular-0.0.1-SNAPSHOT.jar \
    --module-path modular/target/modular-0.0.1-SNAPSHOT.jar:main-module/target/main-module-0.0.1-SNAPSHOT.jar \
    --module main/io.github.fvarrui.modules.main.Main