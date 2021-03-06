package sort;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ShellSortDell {
    public static void main(String[] args) {
//        int[] arr = {4, 2, 5, 9, 7, 3, 10, 678, 230, 4898, 61, 123, 0};
        int[] arr = {4, 2, 5, 9, 7, 3};
//        shellSort(arr);
        shellSort2(arr);
//        System.out.println(Arrays.toString(arr));
    }

    public static void shellSort(int[] arr) {
        //交换法
        //gap 步长
        int temp = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }

    public static void shellSort2(int[] arr) {
        //移位法
        int j = 0;
        int temp = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                j = i;
                temp = arr[j]; //将最初的arr[j]记录为temp
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        // 在组内挨个移动，判断是否比temp大，
                        // 若比temp大，将arr[j] 替换为 arr[j-gap]
                        // 直至将该组所有数都比temp大
                        arr[j] = arr[j - gap];
                        j -= gap;
                        System.out.println("before:" + Arrays.toString(arr));
                    }
                    //当退出while后，j就为temp插入的位置
                    arr[j] = temp;
                    System.out.println("after: " + Arrays.toString(arr));
                }
            }
            System.out.println("I轮： " + Arrays.toString(arr));
        }
    }
}
