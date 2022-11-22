//
// Created by KChankc on 2021/12/1.
//

#include <printf.h>

typedef struct {
    char *stuId;
    double scoreChinese;
    double scoreMath;
    double scoreEnglish;
} StuCourse;

void populateEntity(
        StuCourse *stuCourse,
        char stuId[4],
        double scoreChinese,
        double scoreMath,
        double scoreEnglish
) {
    stuCourse->stuId = stuId;
    stuCourse->scoreChinese = scoreChinese;
    stuCourse->scoreMath = scoreMath;
    stuCourse->scoreEnglish = scoreEnglish;
}

double calculateAverageScore(StuCourse *stuCourse) {
    return (stuCourse->scoreChinese + stuCourse->scoreMath + stuCourse->scoreEnglish) / 3;
}

int main() {

    // 201833050025 成凯

    StuCourse stu1;
    StuCourse stu2;
    StuCourse stu3;
    StuCourse stu4;
    StuCourse stu5;

    populateEntity(&stu1, "001", 82.5, 90, 78.5);
    populateEntity(&stu2, "002", 75, 88, 92.5);
    populateEntity(&stu3, "003", 95, 65.5, 70);
    populateEntity(&stu4, "004", 81, 72.5, 86.5);
    populateEntity(&stu5, "005", 89, 98, 66);

    StuCourse allStuCourse[5] = {stu1, stu2, stu3, stu4, stu5};

    for (int i = 0; i < 5; ++i) {
        printf("%s 平均分：%.2f\n", allStuCourse[i].stuId, calculateAverageScore(&allStuCourse[i]));
    }

    StuCourse maxChinese = allStuCourse[0];
    StuCourse maxMath = allStuCourse[0];
    StuCourse maxEnglish = allStuCourse[0];
    for (int i = 0; i < 5; ++i) {
        if (allStuCourse[i].scoreChinese > maxChinese.scoreChinese) {
            maxChinese = allStuCourse[i];
        }
        if (allStuCourse[i].scoreMath > maxMath.scoreMath) {
            maxMath = allStuCourse[i];
        }
        if (allStuCourse[i].scoreEnglish > maxEnglish.scoreEnglish) {
            maxEnglish = allStuCourse[i];
        }
    }
    printf("%s语文最高分：%.2f\n", maxChinese.stuId, maxChinese.scoreChinese);
    printf("%s数学最高分：%.2f\n", maxMath.stuId, maxMath.scoreMath);
    printf("%s英语最高分：%.2f\n", maxEnglish.stuId, maxEnglish.scoreEnglish);

    return 0;
}
