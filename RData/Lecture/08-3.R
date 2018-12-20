# 산점도 만들기
library(ggplot2)

# 집단간 평균 표 만들기
df_mpg <- mpg %>% 
  group_by(drv) %>% 
  summarise(mean_hwy = mean(hwy))

df_mpg
# 그래프 생성하기
ggplot(data=df_mpg, aes(x=drv, y=mean_hwy))+geom_col()

# 크기 순으로 정렬하기
# - 막대는 기본적으로 범주의 알파벳 순서로 정렬 됨
# 정렬기준 변수 앞에 - 기호를 붙히면 내림차순으로 정렬 됨
ggplot(data=df_mpg,aes(x=reorder(drv,mean_hwy), y=mean_hwy))+geom_col()

ggplot(data=df_mpg,aes(x=reorder(drv,-mean_hwy), y=mean_hwy))+geom_col()

# 빈도 막대 그래프 만들기
# y축 없이 x축만 지정하고 geom_col()대신에 geom_bar()를 사용한다.
ggplot(data=mpg,aes(x=drv))+geom_bar()
