s = [9, 7, 8, 3, 2, 1, 5, 6]
list1 = []
for i in s:
	if i % 2 == 0:
		a = i**2
		list1.append(a)
	else:
		list1.append(i)
print(list1)