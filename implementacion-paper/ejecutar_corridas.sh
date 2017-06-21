#!/bin/bash
for i in `seq 0 9`; do
  java -jar -Dlog4j.configuration=file:logger.properties pso-clahe-color.jar ./ejecutor.sh Stonehenge_lc.jpg 1 2 3
  wait
done

touch termino_bien.txt

