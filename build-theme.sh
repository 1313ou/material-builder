#!/usr/bin/bash

D="./output"
Dcore="./output"
echo "$1" "$2"
if [ -n "$1" ]; then
  D="$1"
  Dcore=$(readlink -f "${D}/../../../../core/src/main/res")
  fi
if [ -n "$2" ]; then
  seeds=$(readlink -f "$2")
  seeds="-f $2"
else
        echo "Seeds needed"
        exit 3
  fi

source define_colors.sh
if [ ! -e "${D}" ]; then
        echo -e "${R}app   ${D}$Z"
        exit 1
fi

if [ ! -e "${Dcore}" ]; then
        echo -e "${R}core  ${Dcore}$Z"
        exit 2
fi

echo -e "${M}app   ${D}$Z"
echo -e "${M}core  ${Dcore}$Z"
echo -e "${M}seeds ${seeds}$Z"

mkdir -p "$Dcore/values"
mkdir -p "$D/values"
mkdir -p "$D/values-night"

./run.sh -o attrs > "$Dcore/values/attrs.xml"

./run.sh -o themeday > "$D/values/themes.xml"
./run.sh -o overlaysday > "$D/values/themes_overlays.xml"

./run.sh -o themenight > "$D/values-night/themes.xml"
./run.sh -o overlaysnight > "$D/values-night/themes_overlays.xml"

./run.sh -o colorsday $seeds  -x > "$D/values/colors.xml"
./run.sh -o colorsnight $seeds -x > "$D/values-night/colors.xml"
