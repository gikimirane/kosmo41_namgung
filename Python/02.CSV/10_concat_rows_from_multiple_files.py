# -*- coding: utf-8 -*-
"""
Created on Tue Dec 11 10:30:57 2018

@author: kosmo10
"""

#! /usr/bin/env python3
import sys
import csv
import glob
import os
# input_file = sys.argv[1]
# input_file = sys.argv[2]
input_path = "./Data"
output_file = "./Output/out10.csv"

first_file=True
# sales가 포함한 파일을 모두 열어서 합체! 첫번째 파일의 header만 쓰고 나머지는 안쓰도록 
for input_file in glob.glob(os.path.join(input_path,'*sales*')):
       
    with open(input_file,'r',newline='') as csv_in_file:
        with open(output_file,'a',newline='') as csv_out_file:
            filereader = csv.reader(csv_in_file)
            filewriter = csv.writer(csv_out_file)
            
            #첫번째 파일이면 모두 저장
            if first_file:
                for row in filereader:
                    filewriter.writerow(row)
                first_file = False
            else :
            #아니면 첫번째 줄만 떼고 나머지를 for문 돌면서 저장 
                header = next(filereader)
                for row in filereader:
                    filewriter.writerow(row)