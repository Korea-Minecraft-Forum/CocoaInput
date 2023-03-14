#!/bin/bash
echo "Build libcocoainput for macOS"
mkdir ../common/src/main/resources/darwin
cd src/main/c/darwin/libcocoainput
make && make install
