#!/bin/bash
# Can't be bothered to write a format function
for z in `seq 10 20`
   do
   for j in `seq 1 9`;
      do
      for i in `seq 10 30`;
         do
         echo stressTest > logtest.20"$z"-0"$j"-"$i".log
      done
   done
done
