package kylec.me.java;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by KyleChan on 2021/11/19 - 9:05 上午
 */
public class Sort {

    private static final int NUMBER_COUNT = 100;

    private static int quickCount = 0;
    private static int quickTransferCount = 0;

    public static void main(String[] args) {
        // 随机数
        int[] arr;

        Scanner scanner = new Scanner(System.in);

        boolean flag = true;
        while (flag) {
            arr = createRandomNumber();
            System.out.println();
            System.out.println("************* 201833050025 成凯 *************");
            System.out.println("已生成新的随机数，请选择操作：");
            System.out.println("1: 直接插入排序");
            System.out.println("2: 希尔排序");
            System.out.println("3: 冒泡排序");
            System.out.println("4: 快速排序");
            System.out.println("5: 二分排序");
            System.out.println("其他键: 退出程序");
            System.out.println("************* 201833050025 成凯 *************");

            int input = scanner.nextInt();
            switch (input) {
                case 1:
                    insertionSort(arr);
                    break;
                case 2:
                    shellSort(arr);
                    break;
                case 3:
                    bubbleSort(arr);
                    break;
                case 4:
                    long start = System.currentTimeMillis();
                    quickSort(arr, 0, arr.length - 1);

                    printArray(arr);
                    printCost(quickCount, quickTransferCount, start);

                    quickCount = 0;
                    quickTransferCount = 0;
                    break;
                case 5:
                    binaryInsertSort(arr);
                    break;
                default:
                    flag = false;
                    break;
            }
        }
    }

    /**
     * 生成随机整数
     */
    public static int[] createRandomNumber() {
        int[] arr = new int[NUMBER_COUNT];

        Random random = new Random();
        for (int i = 0; i < NUMBER_COUNT; i++) {
            arr[i] = random.nextInt(1000) + 1;
        }

        return arr;
    }

    private static void printCost(int count, int transferCount, long startTime) {
        System.out.println("比较了" + count + "次，交换了" + transferCount + "次，耗时" + (System.currentTimeMillis() - startTime) + "ms");
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
            if (i % 20 == 0 && i != 0) {
                System.out.println();
            }
        }
        System.out.println();

        // 重新生成随机数
        Random random = new Random();
        for (int i = 0; i < NUMBER_COUNT; i++) {
            arr[i] = random.nextInt(1000) + 1;
        }
    }

    /**
     * 直接插入排序
     */
    public static void insertionSort(int[] arr) {
        int count = 0;
        int transferCount = 0;

        long start = System.currentTimeMillis();

        // 从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
        for (int i = 1; i < arr.length; i++) {
            // 记录要插入的数据
            int temp = arr[i];
            // 从已经排序的序列最右边的开始比较，找到比其小的数
            int index = i - 1;
            while (index >= 0 && temp < arr[index]) {
                arr[index + 1] = arr[index];
                // 存在比其小的数，插入
                arr[index] = temp;
                index--;
                transferCount++;
                count++;
            }
            count++;
        }

        printCost(count, transferCount, start);
        printArray(arr);
    }

    /**
     * 冒泡排序
     */
    private static void bubbleSort(int[] arr) {
        int count = 0;
        int transferCount = 0;

        long start = System.currentTimeMillis();

        int len = arr.length;

        if (len == 0 || len == 1) {
            return;
        }

        for (int i = 0; i < len; i++) {
            // 初始位置
            for (int j = 0, subLen = len - 1 - i; j < subLen; j++) {
                count++;
                if (arr[j + 1] < arr[j]) {
                    // 比较完成，交换数字
                    int tmp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = tmp;
                    transferCount++;
                }
            }
        }

        printCost(count, transferCount, start);
        printArray(arr);
    }

    /**
     * 希尔排序
     */
    public static void shellSort(int[] arr) {
        int count = 0;
        int transferCount = 0;

        long start = System.currentTimeMillis();

        for (int gap = arr.length; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                count++;
                int secondV = arr[i];
                int index = i - gap;
                while (index >= 0 && secondV < arr[index]) {
                    // 数组arr在index+gap位置的等于在index位置的元素
                    arr[index + gap] = arr[index];
                    // 将要插入的值替换进数组在index位置的元素
                    arr[index] = secondV;
                    // 计算增量
                    index -= gap;
                    transferCount++;
                }
            }
        }

        printCost(count, transferCount, start);
        printArray(arr);
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        quickTransferCount++;
    }

    /**
     * 返回比较元素的位置
     */
    public static int split(int[] a, int low, int high) {
        // i指向比较元素的期望位置
        int i = low;
        // 将该组的第一个元素作为比较元素
        int x = a[low];
        // 从第二个元素开始，若当前元素大于比较元素，将其跳过
        for (int j = low + 1; j <= high; j++) {
            quickCount++;
            // 若找到了小于比较元素的元素，将其与前面较大的元素进行交换
            if (a[j] <= x) {
                i++;
                if (i != j) {
                    swap(a, i, j);
                }
            }
        }

        // 将比较元素交换到正确的位置上
        swap(a, i, low);

        return i;
    }

    /**
     * 快速排序
     */
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // 划分并获取比较元素的位置
            int i = split(arr, low, high);
            // 对比较元素左边的数组进行排序
            quickSort(arr, low, i - 1);
            // 对比较元素右边的数字进行排序
            quickSort(arr, i + 1, high);
        }
    }

    /**
     * 二分排序
     */
    public static void binaryInsertSort(int[] arr) {
        int count = 0;
        int transcount = 0;

        long start = System.currentTimeMillis();

        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int low = 0, high = i - 1;
            int mid;
            while (low <= high) {
                // 取一个中间值
                mid = low + (high - low) / 2;
                if (arr[mid] > temp) {
                    // 小数，再取前一个数
                    high = mid - 1;
                } else {
                    // 大数，再取后一个数
                    low = mid + 1;
                }
            }

            for (int j = i - 1; j >= low; j--) {
                arr[j + 1] = arr[j];
                count++;
            }
            arr[low] = temp;
            transcount++;
        }

        printCost(count, transcount, start);
        printArray(arr);
    }
}
