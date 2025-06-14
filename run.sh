#!/bin/bash

SOURCE_DIR="br"
OUTPUT_DIR="bin"
LIB_DIR="br/inatel/stazhi/lib"
JAR_FILE="postgresql-42.7.7.jar"

mkdir -p "$OUTPUT_DIR"

echo "Compilando arquivos .java..."

# Detecta separador de classpath correto
if [[ "$(uname -s)" =~ MINGW.* ]]; then
    CP_SEP=";"
else
    CP_SEP=":"
fi

# Compilação com classpath do driver
javac -cp "$LIB_DIR/$JAR_FILE" -d "$OUTPUT_DIR" $(find "$SOURCE_DIR" -name '*.java')

if [ $? -eq 0 ]; then

    echo "Executando Main..."
    java -cp "$OUTPUT_DIR${CP_SEP}$LIB_DIR/$JAR_FILE" br.inatel.stazhi.Main
else
    echo "Erro na compilação. Verifique os arquivos .java."
    exit 1
fi
