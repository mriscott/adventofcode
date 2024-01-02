#!/bin/sh
echo Using day $1
sed s/Day1/Day$1/ fw.txt >Day$1.java
