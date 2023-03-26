//
// Created by KChankc on 2021/11/18.
//

#include <stdio.h>

int main() {

    // 成凯 201833050025

    //总高度
    double totalH = 100.0;
    //小球经历的米数
    double totalSum = 0.0;

    for (int i = 0; i < 10; i++) {
        totalSum += totalH;
        totalH /= 2;
        totalSum += totalH;
    }
    //不需要计算第10次的反弹高度，所以减去
    totalSum -= totalH;
    printf("小球总共经历%.2f米, 第10次反弹%.2f米\n", totalSum, totalH);

    return 0;
}
