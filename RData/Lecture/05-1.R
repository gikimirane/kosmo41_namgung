#데이터를 파악할 때 사용하는 함수들

# 앞에서부터 6행까지 출력, Default
df_csv_exam1 <- read.csv("../RData/csv_exam.csv")
head(df_csv_exam1)

# 앞에서부터 10행까지 출력
head(df_csv_exam1,10)

# 뒤에서부터 6행까지 출력, Default
tail(df_csv_exam1)
tail(df_csv_exam1,10)

# 데이터 뷰어 창에서 데이터 출력
View(df_csv_exam1)

# 데이터가 몇 행, 몇 열로 구성되어 있는지 알아보기
dim(df_csv_exam1)

# 데이터의 속성 파악하기
str(df_csv_exam1)

# 요약 통계량 산출
summary(df_csv_exam1)
