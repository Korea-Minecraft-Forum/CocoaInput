#!/bin/bash
echo "Build libcocoainput for X11"
mkdir -p ../common/src/main/resources/x11
cd src/main/c/x11
make && make install
