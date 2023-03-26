def min_n(a,b,*c):
    min=a
    if(a<b):
        min=a
    else:
        min=b
    for i in c:
        if(i<min):
            min=i
    return min
print("最小值为：{}".format(min_n(8, 2)))
print("最小值为：{}".format(min_n(16, 1, 7, 4, 15)))