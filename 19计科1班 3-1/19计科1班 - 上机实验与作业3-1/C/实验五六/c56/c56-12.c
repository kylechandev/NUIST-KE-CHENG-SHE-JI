//
// Created by KChankc on 2021/12/15.
//

#include <stdlib.h>
#include <string.h>
#include "stdio.h"

#define N 10

void sort(char* [], int);

int main() {

    // 在主函数中输入10个等长的字符串。用另一函数对它们排序。然后在主函数输出这10个已排好序的字符串
    // 用指针数组处理上一题目,字符串不等长
    // 201833050025 成凯

    char* string[N];
    printf("Enter ten strings:\n");
    for (int i = 0; i < N; ++i) {
        string[i] = (char*) malloc(20);
        gets(string[i]);
    }

    sort(string, N);

    for (int i = 0; i < N; ++i) {
        printf("%s\n", string[i]);
    }

    return 0;
}

void sort(char* string[], int len) {
    char* temp;

    for (int i = 0; i < len; ++i) {
        for (int j = i; j < len; ++j) {
            if (strcmp(string[i], string[j]) > 0) {
                temp = string[i];
                string[i] = string[j];
                string[j] = temp;
            }
        }
    }
}
