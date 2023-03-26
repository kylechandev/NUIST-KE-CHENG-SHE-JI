from scipy.special import factorial
import tabulate


def fac(x):
    return factorial(x, exact=True)


"""
二阶魔方计算方法：固定右上前角，其它7块自由排列，最后一块的状态是确定的
"""
second = 3 ** 7 * fac(7) // 3
print("二阶魔方状态总数", second)
"""
三阶魔方计算方法：固定6个心块，角块自由排列（最后一块的状态是确定的，因此贡献了分母中的3）
边块自由排列，每个边块有2种状态，这些边块有两对制约关系：最后两块的位置是固定的，最后一块的状态是固定的（贡献了分母中的4）
"""
three = 3 ** 8 * fac(8) * fac(12) * 2 ** 12 // 12
print(len(str(three)))
print("三阶魔方状态总数", three, float(three))
"""
三阶魔方第二种计算方法：固定右上角，其余7个角块的状态总数就是二阶魔方状态总数
心块状态总数是24
12个边块有两对制约关系
"""
third = second * 24 * fac(12) * 2 ** 12 / 4
print("三阶魔方状态总数第二种计算方法", third)


def get(n):
    k = n // 2
    # 角块
    corner = second
    # 面块，每个面上同一位置的块有4个，所以6个面一共有24个块，这些块都是可以交换位置的，而同一面上的4个块是完全等价的，所以分母上除以fac(4)**6
    face = (fac(24) // fac(4) ** 6) ** ((k - 1) ** 2)
    # 心块，如果是偶数阶没有心块，心块之间的相对位置是固定的，只有24中情况
    center = 1 if n % 2 == 0 else 24
    # 边块
    edge = fac(24) ** (k - 1)
    # 边中块，边的中点
    edge_center = 1 if n % 2 == 0 else fac(12) * 2 ** 12 // 4
    return corner * face * center * edge * edge_center


print(get(2), second)
print(get(3), three)
table = []
for i in range(2, 10):
    table.append((i, float(get(i))))
print(tabulate.tabulate(table))
