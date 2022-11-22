n = int(input('请输入一个大于零的整数：'))
#递归
def fact(n):
    fact1 = 0
    if n == 0:
        fact1 = 1
    else:
        fact1 = n * fact(n - 1)
    return fact1
#非递归
def notf(m):
    num = 1
    if n == 0:
        num = 1
    else:
        for i in range(m):
            num = m * num
            m = m-1
        return num
print("递归方式：{0}!= {1}".format(n, fact(n)))
print("非递归方式：{0}!= {1}".format(n, notf(n)))