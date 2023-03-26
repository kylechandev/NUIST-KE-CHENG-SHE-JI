//
// Created by KChankc on 2021/11/18.
//

#include <stdio.h>

#define COUNT 20

int main() {

    // 成凯 201833050025

    double a = 2, b = 1, sum = 0;
    double temp;

    for (int i = 0; i < COUNT; i++) {
        sum += a / b;
        //记录前一项分子
        temp = a;
        //前一项分子与分母之和为后一项分子
        a += b;
        //前一项分子为后一项分母
        b = temp;
    }

    printf("前%d项之和为:sum=%.2f\n", COUNT, sum);
    return 0;
}
