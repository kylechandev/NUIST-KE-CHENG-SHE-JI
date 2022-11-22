n=int(input("请输入顾客的人数："))
print("输入每个顾客所需的服务时间:",end=' ')
li=list(map(int,input().split()))
li.sort()
time=[0]*100
sum=[0]*100
i,j=0,0
while i<n:
    time[j]+=li[i]
    sum[j]+=time[j]
    i+=1
    j+=1
k,t=0,0
while k<n:
    t+=sum[k]
    k+=1
float(t)
t/=n
print("平均最小等待时间为：%.2f"%t)