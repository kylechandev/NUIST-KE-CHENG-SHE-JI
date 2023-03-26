//
// Created by KChankc on 2021/11/6.
//

#include <stdio.h>

void byif() {

    double bonus, bon1, bon2, bon4, bon6, bon10;

    bon1 = 100000 * 0.1;
    bon2 = bon1 + 100000 * 0.075;
    bon4 = bon2 + 100000 * 0.05;
    bon6 = bon4 + 100000 * 0.03;
    bon10 = bon6 + 400000 * 0.015;

    int i;
    scanf("%d", &i);

    if (i <= 100000) {
        bonus = i * 0.1;
    } else if (i <= 200000) {
        bonus = bon1 + (i - 100000) * 0.075;
    } else if (i <= 400000) {
        bonus = bon2 + (i - 200000) * 0.05;
    } else if (i <= 600000) {
        bonus = bon4 + (i - 400000) * 0.03;
    } else if (i <= 1000000) {
        bonus = bon6 + (i - 600000) * 0.015;
    } else {
        bonus = bon10 + (i - 1000000) * 0.01;
    }

    printf("奖金: %f\n", bonus);
}

void byswitch() {

    double bonus, bon1, bon2, bon4, bon6, bon10;

    bon1 = 100000 * 0.1;
    bon2 = bon1 + 100000 * 0.075;
    bon4 = bon2 + 200000 * 0.05;
    bon6 = bon4 + 200000 * 0.03;
    bon10 = bon6 + 400000 * 0.015;

    int i;
    scanf("%d", &i);

    int branch = i / 100000;
    if (branch > 10) branch = 10;

    switch (branch) {
        case 0:
            bonus = i * 0.1;
            break;
        case 1:
            bonus = bon1 + (i - 100000) * 0.075;
            break;
        case 2:
        case 3:
            bonus = bon2 + (i - 200000) * 0.05;
            break;
        case 4:
        case 5:
            bonus = bon4 + (i - 400000) * 0.03;
            break;
        case 6:
        case 7:
        case 8:
        case 9:
            bonus = bon6 + (i - 600000) * 0.015;
            break;
        case 10:
            bonus = bon10 + (i - 1000000) * 0.01;
            break;
        default:
            bonus = 0;
            break;
    }
    printf("奖金: %f\n", bonus);
}

int main() {

    byif();
    byswitch();

    return 0;
}
