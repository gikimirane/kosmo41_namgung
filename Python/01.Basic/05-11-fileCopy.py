# -*- coding: utf-8 -*-
"""
Created on Mon Dec 10 11:43:27 2018

@author: kosmo10
"""

import sys

#파일 복사
# inputfile = sys.argv[1]
# outputfile = sys.argv[2]

inputfile = input("Input File Name : ")
outputfile = input("Output File Name : ")

with open(inputfile,'r') as infile:
    with open(outputfile,'w') as outfile:
        for line in infile:
            outfile.write(line)
            
            