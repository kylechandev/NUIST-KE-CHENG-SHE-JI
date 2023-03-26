//
// Created by KChankc on 2021/11/18.
//

#include <printf.h>
#include <math.h>

int main() {

    // 成凯 201833050025

    int a, b, c;
    for (int i = 100; i <= 999; i++) {
        a = i / 100;
        b = (i / 10) % 10;
        c = i % 10;

        if (pow(a, 3) + pow(b, 3) + pow(c, 3) == i) {
            printf("%d\n", i);
        }
    }
}
