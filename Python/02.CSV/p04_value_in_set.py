# -*- coding: utf-8 -*-
"""
Created on Mon Dec 10 15:01:20 2018

@author: kosmo10
"""

#!/usr/bin/env python3
import sys
import pandas as pd

#input_file = sys.argv[1]
#output_file = sys.argv[2]
input_file = "./Data/01_data.csv"
output_file = "./Output/out04.csv"

data_frame = pd.read_csv(input_file)

important_dates = ['1/20/14', '1/30/14']
data_frame_value_in_set = data_frame.loc[data_frame['Purchase Date']\
.isin(important_dates), :]
data_frame_value_in_set.to_csv(output_file, index=False)
