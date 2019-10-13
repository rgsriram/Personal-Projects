#! /bin/bash

RAW_OUTPUT_DIR="MROUTPUT"
OUTPUT_DIR="SaavnTrending-25-31"
OUTPUT_PREFIX="saavn"

for day in $(ls "$RAW_OUTPUT_DIR");
do
	sort -k 3,3n "${RAW_OUTPUT_DIR}/raw_${day}.txt" | tail -100r | cut -f 1 > "${OUTPUT_DIR}/${OUTPUT_PREFIX}_${day}.txt"
done

