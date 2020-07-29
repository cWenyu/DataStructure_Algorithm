package sort;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
//        int[] arr = {1,2,3,4,5};
        int[] arr = {32, 534, 876, 43, 9};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            int minIndex = i;
            boolean findIndex = false;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    minIndex = j;
                    findIndex = true;
                }
            }

            if (minIndex != i && findIndex) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }else{
                //若没找到可交换的位置，或者要交换的位置是当前 i 索引时候，退出循环
                break;
            }
        }

    }
}
