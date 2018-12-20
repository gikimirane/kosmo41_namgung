# -*- coding: utf-8 -*-
"""
Created on Mon Dec 10 13:09:48 2018

@author: kosmo10
"""


#! /usr/bin/env python3
import sys
import pandas as pd


# input_file = sys.argv[1]
# input_file = sys.argv[2]
input_file = "./Data/01_data.csv"
output_file = "./Output/pout03.csv"

data_frame = pd.read_csv(input_file)
data_frame['Cost'] = data_frame['Cost'].str.strip('$').astype(float)
data_frame_value_meets_condition = data_frame.loc[(data_frame['Supplier Name']\
                                                   .str.contains('Z')) | (data_frame['Cost'] > 600.0),:]

data_frame_value_meets_condition.to_csv(output_file,index=False)