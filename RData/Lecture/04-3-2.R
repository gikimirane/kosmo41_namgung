#외부 데이터 이용하기 #2
#CSV는 별도의 패키지를 설치하지 않고 내장된 함수를 이용한다.

df_csv_exam1 <- read.csv("../RData/csv_exam.csv")
df_csv_exam1

# 문자가 들어있는 파일을 읽어올 때는 StringasFactors = F 를 해야 함
df_csv_exam2 <- read.csv("../RData/csv_exam2.csv",stringsAsFactors = F)
df_csv_exam2

# 데이터 프레임을 csv로 저장하기
write.csv(df_csv_exam1,file="../RData/df_csv_exam_re.csv")

# 데이터 프레임을 R 전용 데이터인 RData file로 저장하기 
save(df_csv_exam1, file="../RData/df_csv_exam_re.rda")

# 데이터 지우기
rm(df_csv_exam1)

load("../RData/df_csv_exam_re.rda")

df_csv_exam1
