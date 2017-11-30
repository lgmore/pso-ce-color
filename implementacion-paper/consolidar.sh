#!/bin/bash
find . -type f -name 'FUN*' -exec cat {} + >> objetivos.txt
find . -type f -name 'VAR*' -exec cat {} + >> vardecision.txt
paste -d " " vardecision.txt objetivos.txt > resultados.txt

