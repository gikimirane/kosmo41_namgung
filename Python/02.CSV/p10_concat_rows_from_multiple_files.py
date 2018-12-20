# -*- coding: utf-8 -*-
"""
Created on Tue Dec 11 10:03:20 2018

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
output_file = "./Output/pout10.csv"

all_files = glob.glob(os.path.join(input_path,'*sales*'))
all_data_frames=[]

for file in all_files:
    data_frame = pd.read_csv(file, index_col=None)
    all_data_frames.append(data_frame)

# header는 알아서 1개 빼고 나머지는 중복되니까 빼준다.
# axis 0은 여러개가 있을 시 위아래(행)로 붙혀준다, axis=1(좌우) 열로 붙혀준다
data_frame_concat = pd.concat(all_data_frames, axis=0,ignore_index=True)
data_frame_concat.to_csv(output_file, index=False)