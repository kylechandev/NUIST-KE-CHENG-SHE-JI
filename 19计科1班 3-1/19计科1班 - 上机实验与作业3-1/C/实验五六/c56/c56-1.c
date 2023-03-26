//
// Created by KChankc on 2021/12/15.
//

#include <stdio.h>

int swap(int*, int*);

int main() {

    // 输入3个整数，要求按由小到大的顺序输出
    // 201833050025 成凯

    int a, b, c;
    printf("Enter three numbers:");
    scanf("%d %d %d", &a, &b, &c);

    if (a > b) {
        swap(&a, &b);
    }
    if (a > c) {
        swap(&a, &c);
    }
    if (b > c) {
        swap(&b, &c);
    }

    printf("%d %d %d\n", a, b, c);

    return 0;
}

int swap(int* a, int* b) {
    int temp = *a;
    *a = *b;
    *b = temp;

    // 返回较大值
    return *a > *b ? *a : *b;
}
