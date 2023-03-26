//
// Created by KChankc on 2021/12/15.
//

#include <stdio.h>

int mystrlen(char*);

int main() {

    // 写一函数,求一个字符串的长度。在main函数中输入字符串,并输出其长度
    // 201833050025 成凯

    char string[20];

    gets(string);

    printf("len: %d", mystrlen(string));

    return 0;
}

int mystrlen(char* str) {
    int len = 0;

    while (*str != '\0') {
        str++;
        len++;
    }

    return len;
}
