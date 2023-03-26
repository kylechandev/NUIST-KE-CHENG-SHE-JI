//
// Created by KChankc on 2021/12/15.
//

#include "stdio.h"

#define N 10

void input(int*, int);

void print(const int*, int);

void handle(int*, int);

int main() {

    // 输入10个整数,将其中最小的数与第一个数对换, 把最大的数与最后一个数对换
    // 201833050025 成凯

    int arr[N];
    printf("Enter ten nums: ");
    input(arr, N);
    handle(arr, N);
    print(arr, N);

    return 0;
}

/**
 * 输入10个数
 */
void input(int* arr, int len) {
    for (int i = 0; i < len; ++i) {
        scanf("%d", arr + i);
    }
}

/**
 * 输出10个数
 */
void print(const int* arr, int len) {
    for (int i = 0; i < len; ++i) {
        printf("%d ", *(arr + i));
    }
    printf("\n");
}

/**
 * 进行处理
 */
void handle(int* arr, int len) {
    int* max = arr;
    int* min = arr;

    for (int i = 0; i < len; ++i) {
        int num = *(arr + i);
        if (num > *max) {
            max = arr + i;
        }
        if (num < *min) {
            min = arr + i;
        }
    }

    printf("min=%d, max=%d\n", *min, *max);

    int temp;
    temp = *arr;
    *arr = *min;
    *min = temp;

    temp = *(arr + len - 1);
    *(arr + len - 1) = *max;
    *max = temp;
}
