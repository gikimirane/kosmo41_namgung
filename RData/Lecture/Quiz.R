
df_sale = data.frame(product=c("사과","딸기","수박"),
                     price=c(1800,1500,3000),
                     count=c(24,38,13)
                     )

df_sale

mean(df_sale$price)
mean(df_sale$count)