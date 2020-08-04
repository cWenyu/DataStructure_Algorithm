package sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
//        int[] arr = {4, 2, 5, 9, 7, 3};
        int[] arr = {4, 2, 5, 9, 7, 3, 10, 678, 230, 4898, 61, 123, 0};
        int[] temp = new int[arr.length];

        mergeSort(arr, 0, arr.length - 1, temp);

        System.out.println(Arrays.toString(arr));
    }

    /**
     * 归并排序
     *
     * @param arr
     * @param left
     * @param right
     * @param temp
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;

//        System.out.println("before merge left: " + Arrays.toString(Arrays.copyOfRange(arr, left, mid + 1)));
        //向左递归分解
        mergeSort(arr, left, mid, temp);

//        System.out.println("before merge right: " + Arrays.toString(Arrays.copyOfRange(arr, mid+1, right)));
        //向右递归分解
        mergeSort(arr, mid + 1, right, temp);


        merge(arr, left, mid, right, temp);

//        System.out.println("after merge" + Arrays.toString(arr));
    }

    /**
     * 合并
     *
     * @param arr   原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引 （左边有序序列的最后一个元素的索引）
     * @param right 右边有序序列的开始索引
     * @param temp  中转数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;//初始化i,左边有序序列的初始索引
        int j = mid + 1; //j 初始化，右边有序序列的初始索引
        int t = 0; // 初始化temp数组的当前索引

        // 1. 将所有左右两边的有序数据按规则填充到temp中，直到左右两边有一遍处理完毕
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                i++;
            } else {
                temp[t] = arr[j];
                j++;
            }
            t++;
        }

        //2. 将有剩余元素的一遍有序序列依次放入到temp中

        //左边有序序列有剩余
        while (i <= mid) {
            temp[t] = arr[i];
            t++;
            i++;
        }

        //右边有序序列有剩余
        while (j <= right) {
            temp[t] = arr[j];
            t++;
            j++;
        }

        //3. 最后一次将temp数组的元素copy到arr中
        t = 0;
        int tempLeft = left;
//        System.out.println("tepLeft: " + tempLeft + " right: " + right);
        while (tempLeft <= right) {
            //第一次合并时，tempLeft = 0,right = 1
            //第二次合并时，tempLeft = 2,right = 3
            //第3次合并时，tempLeft = 0,right = 3
            //最后一次，tempLeft = 0,right = 7
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }
}
