install.packages("readxl")
library(readxl)


df_excel<-read_excel("D:\\yh9189\\Study\\RData\\RData\\excel_exam.xlsx")
df_excel2<-read_excel("D:/yh9189/Study/RData/RData/excel_exam.xlsx")
df_excel2
df_excel

#첫번째 행이 변수명이 아닐 때
df_excel_novar2 <- read_excel("../RData/excel_exam_novar.xlsx",col_names = F)   #상대 경로로 함
df_excel_novar2

# 엑셀파일에 시트가 여러개 있을 때
df_excel_sheet <- read_excel("../Rdata/excel_exam_sheet.xlsx",sheet=3)
df_excel_sheet

# 섞어서 쓸 때
df_exam <- read_excel("../Rdata/excel_exam_sheet.xlsx",col_names = F,sheet=3)
df_exam

