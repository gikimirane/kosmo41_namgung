# -*- coding: utf-8 -*-
"""
Created on Tue Dec 11 09:35:57 2018

@author: kosmo10
"""


#! /usr/bin/env python3
import sys
import csv
# input_file = sys.argv[1]
# input_file = sys.argv[2]
input_file = "./Data/02_data_unnecessary_header_footer.csv"
output_file = "./Output/out07.csv"

row_counter=0

with open(input_file,'r',newline='') as csv_in_file:
    with open(output_file,'w',newline='') as csv_out_file:
        filereader = csv.reader(csv_in_file)
        filewriter = csv.writer(csv_out_file)
        
        for row in filereader:
            if row_counter >=3 and row_counter <=15:
                filewriter.writerow([value.strip() for value in row])
            row_counter += 1
            
# 상단 3줄, 하단 3줄은 불필요 데이터이므로 제외를 위해 row_counter를 확보
# row를 for문 돌면서 strip() (공란제거) 하고 write 한다
