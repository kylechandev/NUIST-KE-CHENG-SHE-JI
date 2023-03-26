//
// Created by KChankc on 2021/12/1.
//

#include <printf.h>

int main() {

    // 201833050025 成凯
    // 输出以下的杨辉三角形（要求输出10行）

    int a[20][20];

    for (int i = 1; i <= 10; i++) {
        for (int j = 1; j <= 10; j++) {
            if (j == 1) {
                // 第一列为1
                a[i][j] = 1;
            } else if (j > i) {
                // 末尾补0
                a[i][j] = 0;
                // 跳出for，末尾补0，避免打印0
                break;
            } else {
                a[i][j] = a[i - 1][j - 1] + a[i - 1][j];
            }

            printf("%d ", a[i][j]);
        }

        printf("\n");
    }

    return 0;
}
