#include <stdio.h>

int n;
int c[100][100];
int a[100];
int minprice=99999;
int price=0;

bool ok(int t){
    int i;
    for(i=0;i<t;i++)
        if(a[i]==a[t])
            return false;
    return true;
}
void dfs(int t){
    int i;
    if(t==n){               //为第t个人找工作
        int i;
        if(price<minprice)
            minprice=price;
        return;
    }
    for(i=0;i<n;i++){
        a[t]=i;             //给第t个人分配第i个工作
        price+=c[t][i];
        if(ok(t))
            dfs(t+1);
        price-=c[t][i];
    }
}

int main()
{
    scanf("%d",&n);
    for(int i=0;i<n;i++)
    {
        for(int j=0;j<n;j++)
        {
            scanf("%d",&c[i][j]);
        }
    }

    dfs(0);
    printf("%d\n",minprice);
    return 0;
}