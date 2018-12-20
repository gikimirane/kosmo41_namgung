# -*- coding: utf-8 -*-
"""
Created on Tue Dec 11 09:46:14 2018

@author: kosmo10
"""


#! /usr/bin/env python3
import sys
import pandas as pd
# input_file = sys.argv[1]
# input_file = sys.argv[2]
input_file = "./Data/02_data_unnecessary_header_footer.csv"
output_file = "./Output/pout07.csv"

data_frame = pd.read_csv(input_file, header=None)
data_frame = data_frame.drop([0,1,2,16,17,18])
data_frame.columns = data_frame.iloc[0]
data_frame = data_frame.reindex(data_frame.index.drop(3))

data_frame.to_csv(output_file,index=False)