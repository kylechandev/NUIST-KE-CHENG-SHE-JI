salary = float(input('请输入有固定工资的收入的党员的月工资:'))
if salary <= 3000:
	fae = 0.0005 * salary
elif 3000 < salary <= 5000:
	fae = 0.001 * salary
elif 5000 < salary <= 10000:
	fae = 0.015 * salary
else:
	fae = 0.02 * salary
print('月工资={:.1f}, 交纳党费={:.1f}'.format(salary, fae))