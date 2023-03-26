//
// Created by KChankc on 2021/12/1.
//

#include <stdio.h>

int main() {

    // 201833050025 成凯
    // 将两个字符串连接起来，不要用strcat函数

    char a[10], b[20], c[30];

    gets(a);
    gets(b);

    int i;
    for (i = 0; a[i] != '\0'; ++i) {
        c[i] = a[i];
    }

    int j;
    for (j = 0; b[j] != '\0'; ++j) {
        c[j + i] = b[j];
    }
    c[j + i + 1] = '\0';

    printf("%s", c);

    return 0;
}
