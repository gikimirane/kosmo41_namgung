summary(mpg)
?mpg

# 데이터에 mpg, x축에 hwy 변수 지정하여 그래프 생성

qplot(data=mpg,x=hwy)
qplot(data=mpg,x=cty)


qplot(data=mpg,x=drv,y=hwy)
# 선그래프
qplot(data=mpg,x=drv,y=hwy,geom="line")
# 박스그래프
qplot(data=mpg,x=drv,y=hwy,geom="boxplot")
# drv 별 색 표현 
qplot(data=mpg,x=drv,y=hwy,geom="boxplot",color=drv)