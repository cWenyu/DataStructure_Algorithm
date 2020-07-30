package sort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort(arr);
    }

    //交换法
    public static void shellSort(int[] arr) {
        int temp = 0;
        /*
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j+gap]) {
                        temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = temp;
                    }
                }
            }
//            System.out.println(Arrays.toString(arr));
        }
*/

        //第一轮

        int gap = arr.length / 2;
        for (int i = gap; i < arr.length; i++) {
            for (int j = i - gap; j >= 0; j -= gap) {
                if (arr[j] > arr[j + gap]) {
                    temp = arr[j + gap];
                    arr[j] = arr[i];
                    arr[j + gap] = temp;
                }
            }
        }

        gap = 5 / 2;
        for (int i = gap; i < arr.length; i++) {
            for (int j = i - gap; j >= 0; j -= gap) {
                System.out.printf("i: %d, j: %d \n", i, j);
                if (arr[j] > arr[i]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
