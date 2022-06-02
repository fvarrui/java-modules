@echo off
java ^
    --module-path modular\target\modular-0.0.1-SNAPSHOT.jar;non-modular\target\non-modular-0.0.1-SNAPSHOT.jar;main-module\target\main-module-0.0.1-SNAPSHOT.jar ^
    --module main/io.github.fvarrui.modules.main.Main