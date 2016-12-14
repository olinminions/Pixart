#!/bin/bash

echo "compiling pixart files"
javac *.java
javac PixartGUI.java
echo "executing pixart"
java PixartGUI

rm *.class
