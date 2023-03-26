//
// Created by KChankc on 2021/12/1.
//

#include <printf.h>

int main() {

    // 201833050025 成凯
    // 求一个3*3的整型矩阵对角线元素之和

    int rect[3][3] = {1, 2, 3,
                      4, 5, 6,
                      7, 8, 9};

    int sum = rect[0][0] + rect[1][1] + rect[2][2] + rect[0][2] + rect[2][0];

    printf("%d", sum);

    return 0;
}
