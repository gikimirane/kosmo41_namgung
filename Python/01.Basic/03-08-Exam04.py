# -*- coding: utf-8 -*-
"""
Created on Fri Dec  7 10:30:17 2018

@author: kosmo10
"""

#타자 게임

import time
import random


def getWord():
    x = ["snake","wolf","dog","cat","bird"]
    return random.choice(x)


print("준비되면 엔터를 입력하세요")
st = time.time()

count=0
count1=0
while True:
    if count==5 :
        break
    qus = getWord() 
    print("문제:",qus)
    ans = input("")
    
    if ans == qus :
        print("정답")
        count=count+1
    else :
        print("오타")
        count1 = count1+1
et = time.time()

ti = et-st

print("소요시간 : ",format(ti,".2f"),"초",", 정답 수 :",count,"오답 수 :",count1)
