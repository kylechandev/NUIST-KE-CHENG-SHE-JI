//
// Created by KChankc on 2021/12/15.
//

#include <stdlib.h>
#include <stdio.h>

// 课程数
#define COURSE_COUNT 5
// 学生数
#define STU_COUNT 4

float avg(int (*)[COURSE_COUNT], int);

void fail(int (*)[COURSE_COUNT], int);

void excellent(int (*)[COURSE_COUNT], int);

int main() {

    // 201833050025 成凯

    int (* arr)[COURSE_COUNT];
    arr = (int (*)[COURSE_COUNT]) malloc(sizeof(int*) * COURSE_COUNT);

    // 成绩信息
    printf("输入学生成绩：\n");
    for (int i = 0; i < STU_COUNT; i++) {
        for (int j = 0; j < COURSE_COUNT; j++) {
            scanf("%d", *(arr + i) + j);
        }
    }

    printf("第一门课平均分：%.2f\n", avg(arr, STU_COUNT));
    fail(arr, STU_COUNT);
    excellent(arr, STU_COUNT);

    return 0;
}

/**
 * 求第一门课的平均分
 *
 * @param arr 指向元素的地址
 * @param stuCount 学生人数
 */
float avg(int (* arr)[COURSE_COUNT], int stuCount) {
    float sum = 0;
    for (int i = 0; i < stuCount; ++i) {
        sum += (float) *(arr[i]);
    }

    return sum / (float) stuCount;
}

/**
 * 找出有两门以上课程不及格的学生,输出他们的学号和全部课程成绩及平均成绩
 *
 * @param stuCount 学生人数
 */
void fail(int (* arr)[COURSE_COUNT], int stuCount) {
    printf("不及格学生：\n");

    for (int i = 0; i < stuCount; i++) {
        int failC = 0;

        // 找不及格课程的数量
        for (int j = 0; j < COURSE_COUNT; j++) {
            if (arr[i][j] < 60) {
                failC++;
            }
        }

        if (failC <= 2) {
            continue;
        }

        printf("学号：%d ", i + 1);
        printf("全部课程成绩: ");
        int sum = 0;
        for (int j = 0; j < COURSE_COUNT; j++) {
            sum += arr[i][j];
            printf("%d ", arr[i][j]);
        }

        printf("平均分：%d ", sum / COURSE_COUNT);
        printf("\n");
    }
}

/**
 * 找出平均成绩在90分以上或全部课程成绩在85分以上的学生
 *
 * @param stuCount 学生人数
 */
void excellent(int (* arr)[COURSE_COUNT], int stuCount) {
    printf("优秀学生：\n");

    for (int i = 0; i < stuCount; i++) {
        int sum = 0, count = 0;
        for (int j = 0; j < COURSE_COUNT; j++) {
            sum += arr[i][j];
            if (arr[i][j] > 85) {
                count++;
            }
        }

        if ((sum / COURSE_COUNT) > 90 || count == COURSE_COUNT) {
            printf("学号：%d\n", i + 1);
        }
    }
}
