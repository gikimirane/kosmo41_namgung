# 산점도 만들기
library(ggplot2)
mpg <- as.data.frame(ggplot2::mpg)

# x축은 displ, y축 hwy로 지정해 배경 생성
ggplot(data=mpg, aes(x=displ, y=hwy))

# 배경에 산점도 추가
ggplot(data=mpg,aes(x=displ,y=hwy))+geom_point()

# x축 범위를 3~6으로 지정
ggplot(data=mpg,aes(x=displ,y=hwy))+geom_point()+xlim(3,6)

# y축 범위를 10~30으로 지정
ggplot(data=mpg,aes(x=displ,y=hwy))+
  geom_point()+
  xlim(3,6)+
  ylim(10,30)

