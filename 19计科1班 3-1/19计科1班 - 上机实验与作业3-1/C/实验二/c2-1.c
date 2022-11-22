//
// Created by KChankc on 2021/11/6.
//
#include "stdio.h"

int main() {

    int a, b;

    scanf("%d%d", &a, &b);

    if (b % a == 0) {
        printf("能整除");
    } else {
        printf("不能整除");
    }

    return 0;
}
