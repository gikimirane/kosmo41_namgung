# -*- coding: utf-8 -*-
"""
Created on Mon Dec 10 12:53:22 2018

@author: kosmo10
"""

#! /usr/bin/env python3
import sys
import pandas as pd


# input_file = sys.argv[1]
# input_file = sys.argv[2]
input_file = "./Data/01_data.csv"
output_file = "./Output/out02.csv"

data_frame = pd.read_csv(input_file)
print(data_frame)
data_frame.to_csv(output_file,index=False)