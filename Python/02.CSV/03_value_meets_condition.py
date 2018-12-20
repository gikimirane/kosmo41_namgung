# -*- coding: utf-8 -*-
"""
Created on Mon Dec 10 13:04:37 2018

@author: kosmo10
"""

#! /usr/bin/env python3
import sys
import csv


# input_file = sys.argv[1]
# input_file = sys.argv[2]
input_file = "./Data/01_data.csv"
output_file = "./Output/out03.csv"

with open(input_file,'r',newline='') as csv_in_file:
    with open(output_file,'w',newline='') as csv_out_file:
        #csv 내장 모듈 사용하여 읽고 쓰기
        filereader = csv.reader(csv_in_file)
        filewriter = csv.writer(csv_out_file)
        
        header = next(filereader)
        filewriter.writerow(header)
        for row_list in filereader:
            supplier = str(row_list[0]).strip()
            cost = str(row_list[3]).strip('$').replace(',','')
            if supplier == 'Supplier Z' or float(cost) > 600.0:
                filewriter.writerow(row_list)