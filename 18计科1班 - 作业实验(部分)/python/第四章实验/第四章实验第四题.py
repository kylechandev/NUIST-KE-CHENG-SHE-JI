import random


random_1 = random.randint(0, 100)
random_2 = random.randint(0, 100)
random_3 = random.randint(0, 100)
list = [random_1, random_2, random_3]
print('原始值:a={:}, b={:}, c={:}'.format(random_1, random_2, random_3))
maxnum = max(list)
minnum = min(list)
midnum = random_1 + random_2 + random_3 - maxnum - minnum
print('升序排:a={:}, b={:}, c={:}'.format(minnum, midnum, maxnum))
print('降序排:a={:}, b={:}, c={:}'.format(maxnum, midnum, minnum))