# -*- coding: utf-8 -*-
"""
Created on Fri Dec  7 09:58:54 2018

@author: kosmo10
"""

# 간단한 계산기, 계산 문제를 맞히는 게임
import random

def make_question():
    a = random.randint(1,40)
    b = random.randint(1,40)
    op = random.randint(1,3)
    
    # 문자열 변수 q에 문제를 만듬
    # 첫번째 수를 q에 저장
    q = str(a)      #a값(정수)를 문자열로 바꾸어 저장
    
    
    # 연산자를 추가
    if(op==1):
        q = q + " + "
    if(op==2):
        q = q + " - "
    if(op==3):
        q = q + " * "
    
    # 두번째 숫자를 q에 저장함
    q = q + str(b)
    
    #만들어진 문제를 돌려줌
    return q

# 정답/오답 횟수 변수 초기화
sc1 =0
sc2 =0

for x in range(5) :
    p = make_question()
    print(p)
    
    ans = input(" = ")
    r = int(ans)
    
    # 컴퓨터가 계산한 결과인 eval(q)의 값과 사용자가 입력한 값 비교
    # eval은 문자열을 계산식으로 바꿔줌
    if eval(p) == r:
        print("정답입니다.")
        sc1 = sc1 + 1
    else :
        print("오답입니다.")
        sc2 = sc2 + 1
        
print("정답 : ",sc1,"오답 :",sc2)

if(sc2 == 0):
    print("모든 문제를 맞췄습니다.")