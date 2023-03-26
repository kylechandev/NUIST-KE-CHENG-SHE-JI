s = input('请输入字符串：')
sum = 1
for i in s:
	if i == ' ':
		sum += 1
if len(s) == 0:
	sum = 0
print('其中的单词数有：{:}'.format(sum))

# 但是如果只有空格，就。。。
