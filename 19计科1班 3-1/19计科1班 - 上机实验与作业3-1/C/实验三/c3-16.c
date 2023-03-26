//
// Created by KChankc on 2021/11/18.
//

#include <stdio.h>

int main() {

    // 成凯 201833050025

    for (int i = 0; i < 4; ++i) {
        for (int j = 1; j <= 4 - i - 1; ++j) {
            printf(" ");
        }
        for (int j = 0; j < 2 * i + 1; ++j) {
            printf("*");
        }
        printf("\n");
    }

    for (int i = 0; i < 3; ++i) {
        for (int j = 0; j < i + 1; ++j) {
            printf(" ");
        }
        for (int j = 0; j < 7 - 2 * (i + 1); ++j) {
            printf("*");
        }
        printf("\n");
    }


    return 0;
}
