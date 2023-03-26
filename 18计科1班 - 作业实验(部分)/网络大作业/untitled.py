import random
list1 = []
list2 = [] 
list3 = []
for i in range(20):
	list1.append(random.randint(0, 99))
print(list1)
for i in range(0, 20, 2):
	list2.append(list1[i])
#print(list2)
list2.sort()
#print(list2)
list2.reverse()
for i in range(0, 20, 2):
	list2.insert(i, 0)
#print(list2)
for i in range(20):
	if i%2 == 0:
		list3.append(list1[i])
	else:
		list3.append(list2[i])	
print(list3)
