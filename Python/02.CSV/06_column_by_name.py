# -*- coding: utf-8 -*-
"""
Created on Mon Dec 10 15:09:54 2018

@author: kosmo10
"""

#! /usr/bin/env python3
import sys
import csv

# input_file = sys.argv[1]
# input_file = sys.argv[2]
input_file = "./Data/01_data.csv"
output_file = "./Output/out06.csv"

my_columns =['Invoice Number','Purchase Date']
my_columns_index=[]

with open(input_file,'r',newline='') as csv_in_file:
    with open(output_file,'w',newline='') as csv_out_file:
        # csv 내장 모듈 사용하여 읽고 쓰기
        filereader = csv.reader(csv_in_file)
        filewriter = csv.writer(csv_out_file)
        
        # 헤더 한줄 읽어온다
        header = next(filereader)
        
        # 헤더 값으로 인덱스 값을 구해온다.
        for index_value in range(len(header)):
            if header[index_value] in my_columns:
                my_columns_index.append(index_value)
        # 읽어두었던 헤더를 파일에 write 한다.
        filewriter.writerow(my_columns)
        
        # 내용을 파일에 write 한다.
        for row_list in filereader:
            row_list_output=[]
            for index_value in my_columns_index:
                row_list_output.append(row_list[index_value])
            filewriter.writerow(row_list_output)