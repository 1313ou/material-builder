#!/usr/bin/bash

D="./output"

mkdir -p "$D/values"
mkdir -p "$D/values-night"

./run.sh -o attrs > "$D/values/attrs.xml"
./run.sh -o themeday > "$D/values/themes.xml"
./run.sh -o overlaysday > "$D/values/themes_overlays.xml"

./run.sh -o themenight > "$D/values-night/themes.xml"
./run.sh -o overlaysnight > "$D/values-night/themes_overlays.xml"

./run.sh -o colorsday -f input/wn-day.txt  -x > "$D/values/colors.xml"
./run.sh -o colorsnight -f input/wn-day.txt -x > "$D/values-night/colors.xml"
