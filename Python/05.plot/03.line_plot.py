# -*- coding: utf-8 -*-
"""
Created on Wed Dec 12 09:35:17 2018

@author: kosmo10
"""

#! /usr/bin/env python3

from numpy.random import randn
import matplotlib.pyplot as plt

plt.style.use('ggplot')

# 그래프에서 사용할 데이터 임의 생성
plot_data1 = randn(50).cumsum()
plot_data2 = randn(50).cumsum()
plot_data3 = randn(50).cumsum()
plot_data4 = randn(50).cumsum()

# 그림 생성
fig = plt.figure()

# 하위 그래프 추가
ax1 = fig.add_subplot(1,1,1)

# 다른 모양의 선 그래프 4개 생성
ax1.plot(plot_data1, marker=r'o', color=u'blue', linestyle='-', label='Blue Solid')
ax1.plot(plot_data2, marker=r'+', color=u'red', linestyle='--', label='Red Dashed')
ax1.plot(plot_data3, marker=r'*', color=u'green', linestyle='-.', label='Green Dash Dot')
ax1.plot(plot_data4, marker=r's', color=u'orange', linestyle=':', label='Orange Dotted')

ax1.xaxis.set_ticks_position('bottom')
ax1.yaxis.set_ticks_position('left')

ax1.set_title('Line plots : Markers, Colors, and Linestyles')
plt.xlabel('Draw')
plt.ylabel('Random Number')

# 그래프의 범례를 만든다.
plt.legend(loc='best')
plt.savefig('./Output/03_line_plot.png', dpi=400, bbox_inches='tight')
plt.show()
# loc='best' 는 그래프에서 최상의 위치에 자동적으로 범례가 배치된다.
# 특정 위치에 수동으로 지정할 수 있다.