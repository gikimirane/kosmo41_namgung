# -*- coding: utf-8 -*-
"""
Created on Wed Dec 12 10:00:15 2018

@author: kosmo10
"""

import numpy as np
import matplotlib.pyplot as plt

plt.style.use('ggplot')

# 선형 다항식 및 2차 다항식의 데이터를 임의로 만든다.
x = np.arange(start=1.,stop=15., step=1.)
y_linear = x+5. * np.random.randn(14)
y_quadratic = x**2 + 10. * np.random.randn(14)

# (x, y_linear)와 (x,y_quadratic) 좌표 쌍들에 대해 선형 및 2차 다항식을 만든다.
fn_linear = np.poly1d(np.polyfit(x,y_linear,deg=1))
fn_quadratic = np.poly1d(np.polyfit(x,y_quadratic, deg=2))

# 그림 생성
fig = plt.figure()

# 하위 그래프 추가
ax1 = fig.add_subplot(1,1,1)

# 2개의 회귀선이 있는 산점도를 만든다.
ax1.plot(x,y_linear,'bo',x,y_quadratic,'go',\
         x,fn_linear(x),'b-',x,fn_quadratic(x),'g-',linewidth=2.)
ax1.xaxis.set_ticks_position('bottom')
ax1.yaxis.set_ticks_position('left')

ax1.set_title('Scatter Plots with Best Fit Lines')
plt.xlabel('x')
plt.ylabel('f(x)')

# 실제 데이터 값을 기반으로 축의 범위 정하기
plt.xlim((min(x)-1., max(x)+1))
plt.ylim((min(y_quadratic)-10.,max(y_quadratic)+10.))

plt.savefig('./Output/04_scatter_plot.png', dpi=400, bbox_inches='tight')
plt.show()