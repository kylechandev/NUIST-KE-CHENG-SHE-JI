//
// Created by KChankc on 2021/12/6.
//

#include <stdio.h>
#include <stdlib.h>

#define NUMBER_COUNT 100

void generateRandomNumber(int*);

void printArray(const int*);

void bubbleSort(int*);

void insertionSort(int*);

// void selectionSort(int*);

void binarySearchSort(int*);

void shellSort(int*);

void heapSort(int*);


int heapSortTransferCount = 0;
int heapSortCompareCount = 0;


int main() {

    // 排序数据
    int arr[NUMBER_COUNT];

    int opt = 0;

    while (opt != 6) {
        // 生成随机数
        generateRandomNumber(arr);
        printf("***********************************\n");
        printf("随机数已生成，请选择操作：\n\n");
        printf("1、冒泡排序\n\n");
        printf("2、直接插入排序\n\n");
        printf("3、二分排序\n\n");
        printf("4、希尔排序\n\n");
        printf("5、堆排序\n\n");
        printf("6、退出程序\n");
        printf("***********************************\n");

        // 获取用户的输入
        scanf("%d", &opt);

        switch (opt) {
            // 冒泡排序
            case 1: {
                // sizeof(arr) / sizeof(arr[0])
                bubbleSort(arr);
                break;
            }
                // 插入排序
            case 2: {
                insertionSort(arr);
                break;
            }
                // 二分查找排序
            case 3: {
                binarySearchSort(arr);
                break;
            }
                // 希尔排序
            case 4: {
                shellSort(arr);
                break;
            }
                // 堆排序
            case 5: {
                heapSortCompareCount = 0;
                heapSortTransferCount = 0;
                heapSort(arr);
                break;
            }
            case 6: {
                printf("程序已退出。\n");
                break;
            }
            default:
                printf("无效命令，请重新输入！\n");
                break;
        }
    }

    return 0;
}

/**
 * 生成随机数
 */
void generateRandomNumber(int* arr) {
    int i = 0;
    while (i < NUMBER_COUNT) {
        // 生成1-800之间的随机数
        *(arr + i) = rand() % 800 + 1;
        i++;
    }
}

/**
 * 遍历数组
 *
 * @param arr 数组
 */
void printArray(const int* arr) {
    for (int i = 0; i < NUMBER_COUNT; ++i) {
        printf("%d ", *(arr + i));
    }

    printf("\n");
}

/**
 * 打印比较次数和交换次数
 *
 * @param compare 比较次数
 * @param transfer 交换次数
 */
void printCompare(int compare, int transfer) {
    printf("比较了%d次，交换了%d次\n", compare, transfer);
}

/**
 * 冒泡排序 - 从小到大排序
 */
void bubbleSort(int* arr) {
    printf("原始数据：\n");
    printArray(arr);

    int compareCount = 0, transferCount = 0;

    int temp;
    // 开始遍历数组
    for (int i = 0; i < NUMBER_COUNT - 1; ++i) {
        for (int j = 0; j < NUMBER_COUNT - 1 - i; ++j) {
            // 开始两两比较
            compareCount++;
            if (arr[j] > arr[j + 1]) {
                // 交换较大的数到后面
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;

                transferCount++;
            }
        }
    }

    printf("冒泡排序后：\n");
    printArray(arr);

    printCompare(compareCount, transferCount);
}

/**
 * 插入排序
 */
void insertionSort(int* arr) {
    printf("原始数据：\n");
    printArray(arr);

    int compareCount = 0, transferCount = 0;

    int temp;
    // 开始遍历数组
    // 从第2个数组开始，依次和前一个数进行比较
    for (int i = 1; i < NUMBER_COUNT; ++i) {
        compareCount++;
        if (arr[i] < arr[i - 1]) {
            // 前一个是大数，需要交换位置
            temp = arr[i];

            // j为temp应该排在的位置
            int j = i;
            while (j > 0 && temp < arr[j - 1]) {
                // 开始依次和前一个数进行比较，将arr[i]放到合适的位置
                // 把前面的大数依次往后挪
                arr[j] = arr[j - 1];
                // j-- 继续向前比较
                j--;

                compareCount++;
            }

            // 将temp放在合适的位置
            arr[j] = temp;

            transferCount++;
        }
    }

    printf("插入排序后：\n");
    printArray(arr);

    printCompare(compareCount, transferCount);
}

/**
 * 二分查找排序
 */
void binarySearchSort(int* arr) {
    printf("原始数据：\n");
    printArray(arr);

    int compareCount = 0, transferCount = 0;

    int key, mid;
    for (int i = 0; i < NUMBER_COUNT; ++i) {
        // key，利用二分查找找到key需要插入的位置
        key = arr[i];

        // 取i之前的数组
        int low = 0, high = i - 1;

        while (low <= high) {
            // 计算mid
            mid = (high - low) / 2 + low; // 加上low，否则 在二分右侧查询时 位置就错了

            // 1 2 3 4 5

            if (key > arr[mid]) {
                // key值在mid后，再对右半边[大值区域]进行搜索
                low = mid + 1;
            } else {
                // key值在mid前，再对左半边[小值区域]进行搜索
                high = mid - 1;
            }
            compareCount++;
        }

        for (int j = i - 1; j >= low; --j) {
            // 将 low~i-1 之前的元素进行后移（i的位置就是要重新插入到合适位置的元素）
            arr[j + 1] = arr[j];
            transferCount++;
        }

        // 将key插入到它合适的序列位置
        arr[low] = key;
        transferCount++;
    }

    printf("二分查找排序后：\n");
    printArray(arr);

    printCompare(compareCount, transferCount);
}

/**
 * 希尔排序 - 插入排序的一种，又称"缩小增量排序" - 不稳定
 */
void shellSort(int* arr) {
    printf("原始数据：\n");
    printArray(arr);

    int compareCount = 0, transferCount = 0;

    int temp, j;
    // 增量每次都除2
    for (int step = NUMBER_COUNT / 2; step > 0; step /= 2) { // step/=2 缩小增量
        // 从增量那组开始进行插入排序，直至完成
        for (int i = step; i < NUMBER_COUNT; ++i) {
            j = i;
            temp = arr[j];

            // j-step 就是代表与它[同组隔壁]的元素
            // arr[j-step]>temp
            while (j - step >= 0 && arr[j - step] > temp) {
                arr[j] = arr[j - step];
                j -= step;

                transferCount++;
            }
            arr[j] = temp;

            compareCount++;
            transferCount++;
        }
    }

    printf("希尔排序后：\n");
    printArray(arr);

    printCompare(compareCount, transferCount);
}

/**
 * 交换a b的值
 */
void swap(int* a, int* b) {
    int temp = *a;
    *a = *b;
    *b = temp;

    heapSortTransferCount++;
}

void maxHeapify(int* arr, int start, int end) {
    // 建立父节点指标和子节点指标
    int dad = start;
    int son = dad * 2 + 1;
    while (son <= end) {
        // 若子节点指标在范围内才做比较
        if (son + 1 <= end && arr[son] < arr[son + 1]) {
            // 先比较两个子节点大小，选择最大的
            son++;
        }
        if (arr[dad] > arr[son]) {
            // 如果父节点大于子节点代表调整完毕，直接跳出函数
            return;
        } else {
            // 否则交换父子内容再继续子节点和孙节点比较
            swap(arr + dad, arr + son);
            dad = son;
            son = dad * 2 + 1;
        }

        heapSortCompareCount++;
    }
}

/**
 * 堆排序
 */
void heapSort(int* arr) {
    printf("原始数据：\n");
    printArray(arr);

    int i;
    // 初始化，i从最后一个父节点开始调整
    for (i = NUMBER_COUNT / 2 - 1; i >= 0; --i) {
        maxHeapify(arr, i, NUMBER_COUNT - 1);
    }
    // 先将第一个元素和已排好元素前一位做交换，再重新调整，直到排序完毕
    for (i = NUMBER_COUNT - 1; i > 0; --i) {
        swap(arr + 0, arr + i);
        maxHeapify(arr, 0, i - 1);
    }

    printf("堆排序后：\n");
    printArray(arr);

    printCompare(heapSortCompareCount, heapSortTransferCount);
}
