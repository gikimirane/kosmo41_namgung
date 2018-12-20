library(dplyr)
library(ggplot2)

# 이상치 제거하기
outlier <- data.frame(sex=c(1,2,1,3,2,1),
                      score=c(5,4,3,4,2,6)
                      )
outlier

# 결측 확인하기
table(outlier$sex)
table(outlier$score)

# 이상치 값을 결측 처리하기 : sex가 3이면 na 할당
outlier$sex = ifelse(outlier$sex==3,NA,outlier$sex)

# 이상치 값을 결측 처리하기 : score가 5보다 크면 na 할당
outlier$score = ifelse(outlier$score>5,NA,outlier$score)

# 결측치 제거하여 최종적으로 이상치 정제하기
outlier %>% 
  filter(!is.na(sex) & !is.na(score)) %>% 
  group_by(sex) %>% 
  summarise(mean_score=mean(score))
