//
// Created by KChankc on 2021/11/6.
//
#include "stdio.h"

int main() {

    int a, b, c, max = 0;

    scanf("%d%d%d", &a, &b, &c);

    if (a > max) {
        max = a;
    }
    if (b > max) {
        max = b;
    }
    if (c > max) {
        max = c;
    }

    printf("max: %d", max);

    return 0;
}
