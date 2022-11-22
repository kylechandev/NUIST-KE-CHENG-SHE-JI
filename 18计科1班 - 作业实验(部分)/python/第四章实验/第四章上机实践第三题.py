import math
import numpy

sideA = float(input('请输入直角三角形的直角边A：'))
sideB = float(input('请输入直角三角形的直角边B：'))
sideC = math.sqrt(sideA**2 + sideB**2)
print('直角三角形三分分别是：A={:},B={:},C={:}'.format(sideA, sideB, sideC))
circ = sideA + sideB + sideC
area = 0.5 * sideA * sideB
deg1 = numpy.arccos(sideA / sideC)
deg2 = numpy.arccos(sideB / sideC)
deg_hudu1 = deg1 * 180 / math.pi
deg_hudu2 = deg2 * 180 / math.pi
print('三角形两个锐角的度数分别为：{:.1f}和{:.1f}'.format(deg_hudu1, deg_hudu2))