#include<iostream>
#include<queue>
#include<cmath>
#include<vector>
#include<fstream>
#include<sstream>
using namespace std;
 
//定义一个节点类
struct Node{
	int number;
	vector<int>x;//保存当前解 
}; 
 
//定义一个Queen的类 
class Queen{
	friend int nQueen(int);
	public:
		bool Place(Node q,int n);
		void Research();
		int n;//皇后个数
		int *bestx;//最优解
}; 
 
//判断是否能够放置的函数 
bool Queen::Place(Node q,int n)
{
	for(int j=1;j<n;j++)
	  if((abs(n-j)==abs(q.x[j]-q.x[n]))||(q.x[j]==q.x[n])) return false;
	return true;
}
 
void Queen::Research()
{
	queue<Node>Q;//活节点队列
	Node sign;
	sign.number=-1;
	Q.push(sign);//同层节点尾部标志
	int t=1;//当前节点所处的层
	Node Ew;//当前扩展节点 
	Ew.number=0; 
	//搜索子集空间树
	while(1){	
	   //检查所有的孩子节点 
	   for(int k=1;k<=n;k++){
		//把当前扩展节点的值赋给下一个节点 
		Node q;
	        q.number=t; 
	        q.x.push_back(0);//第一个位置为0 
    	        for(int i=1;i<t;i++) q.x.push_back(Ew.x[i]);
    	        q.x.push_back(k);
		if(Place(q,t))
    	    	Q.push(q);
    	    }	 
		//取下一扩展节点,取出，赋值给Ew 
		Ew=Q.front();
		Q.pop();
		if(Ew.number==-1){
		    //同层节点尾部标记
		    t++;//进入下一层 
		    Q.push(sign);//增加标记
		    //继续往下去下一个节点 
		    Ew=Q.front();
		    Q.pop();
		}		
		if(Ew.number==n){//找到最后一层的节点 
		   for(int i=0;i<=n;i++) bestx[i]=Ew.x[i];
		   break;
		} 
	}
}
 
int nQueen(int n,ofstream &outfile)
{
	Queen X;
	X.n=n;	
	X.bestx=new int[n+1];
	for(int i=0;i<=n;i++) X.bestx[i]=0;
	X.Research();
	for(int i=1;i<=n;i++){
		outfile<<X.bestx[i]<<" ";
	}
}
 
int main(){
	int N;
	ifstream cinfile;
	cinfile.open("/Users/ycy/Desktop/input.txt",ios::in);
	cinfile>>N;
	cinfile.close();
	
	ofstream outfile;
	outfile.open("/Users/ycy/Desktop/output.txt",ios::out);
	
	nQueen(N,outfile);
	
	outfile.close();
	return 0;
}