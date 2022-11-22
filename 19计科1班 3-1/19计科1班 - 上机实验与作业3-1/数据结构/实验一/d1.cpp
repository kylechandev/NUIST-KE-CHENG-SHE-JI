#include  "stdio.h"

#define N 4
#define M 7

void merge(int a[N], int b[M], int c[N + M]);

int main() {
    int i;
    int c[11], a[4] = {3, 5, 8, 11}, b[7] = {2, 6, 8, 9, 11, 15, 20};

    merge(a, b, c);

    printf("合并后\n");
    for (i = 0; i < N + M; i++)
        printf("%4d", c[i]);

    printf("\n");
}

void merge(int a[N], int b[M], int c[N + M]) {
    int i, j, k;
    i = j = k = 0;
    while (i < N && j < M) {
        if (a[i] <= b[j]) {
            c[k++] = a[i++];
        } else {
            c[k++] = b[j++];
        }
    }

    while (i < N)
        c[k++] = a[i++];

    while (j < M)
        c[k++] = b[j++];
}
