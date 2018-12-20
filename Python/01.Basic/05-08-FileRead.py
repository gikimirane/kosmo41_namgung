# -*- coding: utf-8 -*-
"""
Created on Mon Dec 10 10:30:24 2018

@author: kosmo10
"""
import sys
import glob
import os
# 하나의 텍스트 파일 읽기 (Old Version)
# input_file = sys.avgv[1]

input_file = input("File Name : ")

# 파일을 열건데 'r'ead mode로 열거다
filereader = open(input_file,'r')

# 한 줄씩 읽어준다.
for row in filereader :
    print(row.strip())

# 앞뒤로 여백제거 = strip

filereader.close()

print("="*30)

# 하나의 텍스트 파일 읽기 (New version)
input_file = input("File name : ")

# with 문이 끝날 때 자동으로 파일 객체를 닫는다.
with open(input_file,'r',newline='') as filereader :
    for row in filereader :
        print(row.strip())

print("="*30)

# glob 를 이용해 다수의 텍스트 파일 읽기
# inputPath = sys.argv[1]

inputPath = input("Input path : ")

for input_file in glob.glob(os.path.join(inputPath,'*.txt')):
    with open(input_file,'r',newline='') as filereader :
        for row in filereader :
            print(row.strip())
        print("*"*30)