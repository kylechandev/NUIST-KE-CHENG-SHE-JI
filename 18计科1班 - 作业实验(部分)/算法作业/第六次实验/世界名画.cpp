#include<iostream>
#include<fstream>
#include<queue>

using namespace std;

class Solve;

class Node   //Node节点，用来存放搜索树的节点
{
    friend class Solve;
private:
    int i;    //当前要放置的新位置 横坐标：i 纵坐标：j
    int j;
    int robotNums;    //当前节点已经放置的警卫机器人数目
    int beenMonitored;   //当前已经被监视的房间数
    int** x;    //当前放置警卫的地方   0表示没有，1表示放置了
    int** y;    //当前已经被监视的地方   0表示没有，1表示已经监视了
    int m;      //行
    int n;      //列

public:
    Node();   //构造函数
    Node(int m, int n);   //构造函数,m、n是行列数
    Node(const Node& a);  //这个函数是用于heap的push，push会调用复制构造函数，因此必须自定义一个
    friend bool operator<(const Node& a, const Node& b);   //重载<，用于优先队列的使用
    Node& operator=(const Node& a)   //赋值运算符，懒得换位置了
    {
        if (x || y)
        {
            for (int i = 0; i < m + 2; ++i)
            {
                if (x)
                {
                    delete[] x[i];
                }
                if (y)
                {
                    delete[] y[i];
                }
            }
            delete[] x;
            delete[] y;
            x = NULL;
            y = NULL;
        }

        i = a.i;
        j = a.j;
        robotNums = a.robotNums;
        beenMonitored = a.beenMonitored;
        m = a.m;
        n = a.n;
        x = new int* [m + 2];
        y = new int* [m + 2];
        for (int i = 0; i < m + 2; ++i)
        {
            x[i] = new int[n + 2];
            y[i] = new int[n + 2];
            for (int j = 0; j < n + 2; ++j)
            {
                x[i][j] = a.x[i][j];
                y[i][j] = a.y[i][j];
            }
        }
        return *this;
    }
    ~Node();   //用到了new，因此析构函数要重载，避免内存泄露

};



class Solve     //解决问题的类
{
private:
    priority_queue<Node> heap;    //优先队列heap
    int ans;     //答案所需要的警卫数目
    int m;       //行列数
    int n;
    int** result;  //答案警卫的排列顺序

public:
    Solve();
    Solve(int m, int n);
    void run(ofstream& fcout);    //进行整个计算+输出
    void get_min();               //运用分支限界法，寻找最小值
    void print(ofstream& fcout);  //打印警卫位置和数目
    void copy(int** x, int** y);  //将一个二维数组赋值给另一个
    void change(Node& tmp, int i, int j);   //生成子节点，同时将其添加到heap中
};




int main()
{
    ifstream fcin;
    fcin.open("/user/ycy/Desktop/input.txt");
    if (!fcin.is_open())
    {
        cout << "文件 input.txt 未能打开" << endl;
        return -1;
    }

    int m, n;
    fcin >> m >> n;
    fcin.close();
    Solve solve(m, n);

    ofstream fcout;
    fcout.open("/user/ycy/Desktop/output.txt");
    if (!fcout.is_open())
    {
        cout << "文件 output.txt 未能打开" << endl;
        return -1;
    }
    solve.run(fcout);
    fcout.close();

    return 0;
}
