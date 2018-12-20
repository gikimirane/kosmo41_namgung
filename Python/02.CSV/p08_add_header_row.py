# -*- coding: utf-8 -*-
"""
Created on Tue Dec 11 10:03:20 2018

@author: kosmo10
"""

#! /usr/bin/env python3
import sys
import pandas as pd
# input_file = sys.argv[1]
# input_file = sys.argv[2]
input_file = "./Data/03_data_no_header_row.csv"
output_file = "./Output/pout08.csv"

header_list = ['Supplier Name','Invoice Number',\
               'Part Number','Cost','Purchase Date']
data_frame = pd.read_csv(input_file, header=None, names=header_list)
data_frame.to_csv(output_file,index=False)