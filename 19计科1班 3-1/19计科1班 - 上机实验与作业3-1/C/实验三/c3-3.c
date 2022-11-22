//
// Created by KChankc on 2021/11/18.
//

#include <printf.h>

int main() {

    // 成凯 201833050025

    int p, r, n, m, temp;
    scanf("%d,%d,", &n, &m);

    if (n < m) {
        temp = n;
        n = m;
        m = temp;
    }

    p = n * m;
    while (m != 0) {
        r = n % m;
        n = m;
        m = r;
    }
    printf("最大公约数: %d\n", n);
    printf("最小公倍数: %d\n", p / n);

    return 0;
}
