# -*- coding: utf-8 -*-
"""
Created on Wed Dec 12 10:30:48 2018

@author: kosmo10
"""


#! /usr/bin/env python3

import numpy as np
import matplotlib.pyplot as plt

plt.style.use('ggplot')

N=500
normal = np.random.normal(loc=0.0,scale=1.0,size=N)
lognormal = np.random.lognormal(mean=0.0,sigma=1.0,size=N)
index_value = np.random.random_integers(low=0,high=N-1,size=N)
normal_sample=normal[index_value]
lognormal_sample=lognormal[index_value]
box_plot_data = [normal,normal_sample,lognormal, lognormal_sample]

fig = plt.figure()
ax1 = fig.add_subplot(1,1,1)

# 각 상자그림의 레이블
box_labels = ['normal','normal_sample','lognormal','lognormal_sample']

# 상자 그림을 그린다.
ax1.boxplot(box_plot_data,notch=False,sym='.',vert=True, whis=1.5,\
            showmeans=True, labels=box_labels)

ax1.xaxis.set_ticks_position('bottom')
ax1.yaxis.set_ticks_position('left')
ax1.set_title('Box Plots:Resampling of Two Distributions')
ax1.set_xlabel('Distribution')
ax1.set_ylabel('Value')

plt.savefig('./Output/05_Box_plot.png', dpi=400, bbox_inches='tight')
plt.show()

# notch=False 는 상자 중간에 홈이 파이게 하지 말고 사각형으로 그리게 한다.
# sym='.'는 특이점을 표현할 기호를 기본값인 + 대신 .으로 지정했다.
# vert=True 는 상자를 세로 방향으로 그리게 한다.
# whis = 1.5는 제 1 사분위수 및 제 3 사분위수를 연장한 수염의 길이를 지정한다.
# 예를 들어 Q3+whis*IQR과 같은 식으로 정한다.
# 여기서 IQR은 사분위간 범위(Q3-Q1)을 뜻한다.
# showmeans=True는 평균도 표시할지 여부를 지정한다.
