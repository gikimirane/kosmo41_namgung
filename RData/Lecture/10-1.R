Sys.getlocale()
Sys.getenv("JAVA_HOME")

install.packages("rJava")
install.packages("memoise")
install.packages("KoNLP")
install.packages("stringr")

library(rJava)
library(KoNLP)
library(dplyr)
library(stringr)

useNIADic()

txtData <- readLines("../RData/hiphop.txt")
head(txtData)
tail(txtData)

# 특수문자 제거
txtData <- str_replace_all(txtData,"\\W"," ")

# 명사 추출하기
extractNoun("동해물과 백두산이 마르고 닳도록 하느님이")

# 가사에서 명사 추출하기
nouns<-extractNoun(txtData)

# 추출한 명사 list를 문자열 벡터로 변환, 단어별 빈도표 생성
wordcount <-table(unlist(nouns))
wordcount
tail(wordcount)
df_word <- as.data.frame(wordcount,stringsAsFactors = F)

df_word <-rename(df_word,
                 word=Var1,
                 freq = Freq)

# 두글자 이상 단어 추출
df_word <- filter(df_word,nchar(word)>=2)
df_word


install.packages("wordcloud")
library(wordcloud)
library(RColorBrewer)

pal<-brewer.pal(8,"Dark2")
set.seed(1234)
wordcloud(words=df_word$word,
          freq = df_word$freq,
          min.freq = 2,
          max.words = 200,
          random.order = F,
          rot.per=.1,
          scale=c(4,0.3),
          colors=pal)

