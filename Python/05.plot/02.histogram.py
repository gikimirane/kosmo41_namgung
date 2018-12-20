# -*- coding: utf-8 -*-
"""
Created on Tue Dec 11 14:52:46 2018

@author: kosmo10
"""

#! /usr/bin/env python3

import numpy as np
import matplotlib.pyplot as plt
plt.style.use('ggplot')

# 빈도분포를 그리는 스크립트
mu1, mu2, sigma = 100,130,15

# 난수 생성 함수를 사용하여 정규분포를 따르는 두 개의 변수 x1과 x2를 만든다.
# x1의 평균은 100이고 x2의 평균은 130이므로, 두 분포는 일부분만 겹치게 됨.
x1 = mu1 + sigma*np.random.randn(10000)
x2 = mu2 + sigma*np.random.randn(10000)

# 그림 생성
fig = plt.figure()
# 하위 그래프 추가
ax1 = fig.add_subplot(1,1,1)

# 두 변수에 대한 히스토그램을 만든다.
n, bins, patches = ax1.hist(x1,bins=50,normed=False,color='darkgreen')
n, bins, patches = ax1.hist(x2,bins=50,normed=False,color='orange',alpha=0.5)

ax1.xaxis.set_ticks_position('bottom')
ax1.yaxis.set_ticks_position('left')

plt.xlabel('Bins')
plt.ylabel('Number of Values in Bin')
plt.suptitle('Histograms', fontsize=14, fontweight='bold')
ax1.set_title('Two Frequency Distributions')

plt.savefig('./Output/02_histogram.png',dpi=400,bbox_inches='tight')
plt.show()


# bins=50은 수치를 50개의 구간 bin으로 표시하며
# normed=False는 확률밀도가 아니라 빈도를 표시한다는 뜻이다.
