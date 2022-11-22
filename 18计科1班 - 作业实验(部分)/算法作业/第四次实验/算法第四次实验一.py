
#include<iostream>
#include<algorithm>
using namespace std;
//只要满足当前下一个会议开始的时间是大于当前会议结束的时间即可
int main(){
    int k;
    cin>>k;
    int beg[k+1]={};
    int end[k+1]={};
    for(int i=0;i<k;i++){··
        cin>>beg[i]>>end[i];
    }
    sort(beg,beg+k);//最小开始时间
    sort(end,end+k);//最早结束时间
    int n=0,j=0;
    for(int i=0;i<k;i++){
        if(beg[i]>end[j])//如果能正常进行下个（结束时间小于开始时间）就继续
        j++;
        else //否则需要再开会场
        n++;
    }
    cout<<n<<endl;
}