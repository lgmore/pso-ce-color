#!/bin/bash
cat VAR* > vardecision.txt
cat FUN* > objetivos.txt
paste -d " " vardecision.txt objetivos.txt > resultados.txt

