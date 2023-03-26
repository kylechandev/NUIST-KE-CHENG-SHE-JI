#include<iostream>
#include<algorithm>
using namespace std;
#define N 10000

 
int main()
{
    int n;
	int i;
    int a[N];
    while(cin>>n)
    {
        for( i=1;i<=n;i++)
        cin>>a[i];
        sort(a+1,a+1+n);
        int sum=0;
        int tmp=0;
        for( i=1;i<=n;i++)
        {
            sum=sum+tmp+a[i];
            tmp+=a[i];
        }
        double ans=0;
        ans=(double)sum*1.0/n;
        printf("%.2lf\n",ans);
    }
    return 0;
}