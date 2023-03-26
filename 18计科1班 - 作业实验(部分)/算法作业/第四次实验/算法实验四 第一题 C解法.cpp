#include<iostream>
#include<cstdio>
#include<algorithm>
using namespace std;
bool b[10001];//用一个bool数组标记这个会场是否已经安排上了
struct meeting
{
    int start,end;
}a[10001];
int comp(const meeting &a,const meeting &b)
{
    return a.end<b.end;
}
int cmp(const meeting &a,const meeting &b)
{
    return a.start<b.start;
}
int main ()
{
    int n,ans,counts;
    int flag=0;
    freopen("/Users/ycy/Desktop/input.txt","r",stdin);
    while(scanf("%d",&n)!=EOF)
    {
        flag++;
        memset(a,0,sizeof(a));
        ans=0;
        counts=n;			//假设最开始每场会议都要一个会场
        memset(b,false,sizeof(b));		//最开始每场会议都还没安排
        for(int i=1;i<=n;i++)
            cin>>a[i].start>>a[i].end;
        sort(a+1,a+n+1,cmp);	//根据会议的结束时间进行排序
        for(int i=1;i<=n;i++)
        {
            int p,t;
            if(!b[i])	//如果第i场会议还没安排上，以这场会议作为起点，寻找可以和这场安排一起的有几个
            {
                ans++;
                p=i;
                t=i+1;
                while(t<=n)
                {
                    if(a[p].end<=a[t].start&&!b[t])	//如果找到一个，这个点安排上
                    {
                        counts--;		//找到一个能和这场会议安排在一个会场里的，counts--
                        p=t;			//再开始从这个个点往下走
                        b[t]=true;
                    }
                    t++;
                }
            }
            b[i]=true;
        }
        cout<<ans<<endl;
    }
    return 0;
}