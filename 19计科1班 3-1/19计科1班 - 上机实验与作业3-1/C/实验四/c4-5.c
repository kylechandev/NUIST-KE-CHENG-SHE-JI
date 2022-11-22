//
// Created by KChankc on 2021/12/1.
//

#include <stdio.h>

#define N 5

int main() {

    // 201833050025 成凯
    // 将一个数组的值按逆序重新存放。例如，原来的顺序为8,6,5,4,1。要求改为1,4,5,6,8

    int a[N];

    for (int i = 0; i < N; ++i) {
        scanf("%d", &a[i]);
    }

    int temp;
    for (int i = 0, j = N - 1; i < j; ++i, --j) {
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    for (int i = 0; i < N; ++i) {
        printf("%d ", a[i]);
    }

    return 0;
}
