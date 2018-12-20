## 결측치 정제하기
# 결측치 찾기

# 결측치가 포함된 데이터 프레임 생성
df <- data.frame(sex=c("M","F",NA,"M","F"),
                 score=c(5,4,3,4,NA) )
# 결측치 찾기
is.na(df)

table(is.na(df))

#sex 결측치 빈도 출력
table(is.na(df$sex))

#score 결측치 빈도
table(is.na(df$score))

#평균산출 -값이 이상해서 평균이 안나옴
mean(df$score)

## score가 NA인 데이터만 출력
df %>% filter(is.na(score))

# score 결측치 제거
df %>% filter(!is.na(score))

df_nomiss <- df %>% filter(!is.na(score))
# score 평균산출
mean(df_nomiss$score)

#score 합계 산출
sum(df_nomiss$score)

# score,sex 결측치 제거
df_nomiss <- df %>% filter(!is.na(score) & !is.na(sex))
df_nomiss

# 모든 변수에 결측치 없는 데이터를 추출
# 간편하지만, 분석에 필요한 행까지 손실될 수 있음, 보통은 이렇게 하지 않음.
df_nomiss2 <- na.omit(df)

# 함수의 결측치 제외 기능 이용하기
# 결측치 제외하고 평균 산출하기
mean(df$sore,na.rm=T)

# 결측치 제외하고 합계 산출하기
sum(df$score,na.rm=T)

exam <- read.csv("../RData/csv_exam2.csv",fileEncoding = "UTF-8")
exam[c(3,8,15),"math"]<-NA

table(is.na(exam))

# math 평균산출
exam %>% summarise(mean_math =mean(math))

# math 결측치 제외하고 평균 산출
exam %>% summarise(mean_math =mean(math,na.rm=T))

exam %>% summarise(mean_math=mean(math,na.rm=T),
                   sum_math=sum(math,na.rm=T),
                   median_math = median(math,na.rm=T))

# 결측치 대체하기
# 결측치를 제거하는 대신다른 값을 채워넣는 방법
# 결측치를 제외하고 math 평균 산출
mean_math =mean(math,na.rm=T)

# math가 NA면 평균값으로 결측치를 대체 : 55점
exam$math<-ifelse(is.na(exam$math),55,exam$math)
table(is.na(exam$math))
