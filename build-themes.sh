#!/usr/bin/bash


source define_colors.sh

H=/mnt/data2/devel/android-sqlunet-as/semantikos

declare -A tasks
tasks=(
[xn]=browser
[wn]=browserwncommon
[vn]=browservn
[fn]=browserfn
[sn]=browsersn
)

for m in ${!tasks[@]}; do
  d=${tasks[$m]}
  res=$H/$d/src/main/res
  seeds=input/${m}-day.txt 
  echo -e "${Y}${m}${Z}"
          
  ./build-theme.sh "$res" "$seeds"
done  
