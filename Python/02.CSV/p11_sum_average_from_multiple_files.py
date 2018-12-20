# -*- coding: utf-8 -*-
"""
Created on Tue Dec 11 11:50:10 2018

@author: kosmo10
"""

#! /usr/bin/env python3
import sys
import os
import glob
import pandas as pd

# input_file = sys.argv[1]
# input_file = sys.argv[2]

input_path = "./Data"
output_file = "./Output/pout11.csv"

all_files = glob.glob(os.path.join(input_path,'*sales*'))
all_data_frames =[]
for input_file in all_files:
    data_frame = pd.read_csv(input_file,index_col=None)
    
    # 값을 하나씩 꺼내서(for문으로) sum을 구하고 total에 넣음 
    total_sales = pd.DataFrame([float(str(value).strip('$').replace(',',''))\
                                for value in data_frame.loc[:,'Sale Amount']]).sum()
    average_sales = pd.DataFrame([float(str(value).strip('$').replace(',',''))\
                                  for value in data_frame.loc[:,'Sale Amount']]).mean()
    data = {'file_name' : os.path.basename(input_file),
            'total_sales' : total_sales,
            'average_sales' : average_sales}
    
    all_data_frames.append(pd.DataFrame(data,columns =['file_name',\
                                                       'total_sales','average_sales']))
    
    data_frames_concat =pd.concat(all_data_frames, axis=0, ignore_index=True)
    data_frames_concat.to_csv(output_file,index=False)
    