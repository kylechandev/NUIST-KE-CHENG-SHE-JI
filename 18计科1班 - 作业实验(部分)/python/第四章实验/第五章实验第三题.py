list1 = input()
list2 = []
for i in list1:
    if i not in list2:
        list2.append(i)
print(list2)