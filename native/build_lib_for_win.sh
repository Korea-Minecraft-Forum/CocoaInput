#!/bin/bash
echo "Build libcocoainput for Windows"
mkdir -p ../common/src/main/resources/win
cd src/main/c/win
make && make install
