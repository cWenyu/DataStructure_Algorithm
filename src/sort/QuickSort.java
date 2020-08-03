package sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = {4, 2, 5, 9, 7, 3, 10, 678, 230, 4898, 61, 123, 0};
        int[] arr = {3, 4, 6, 7, 2, 7, 2, 8, 0, 9, 1};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

    }

    /**
     * @param arr
     * @param start
     * @param end
     */
    public static void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            int high = end;
            int low = start;
            int basic = arr[start];

            int count = 0;
            //当low小于high时候，进行比较
            while (low < high) {
                //若高位比基数大或者等于基数，将高位--（高位左移）
                while (low < high && arr[high] >= basic) {
                    high--;
                }
                //当arr[hight] < basic的时候跳出循环，即hight指针停止移动，此时arr[hight]则为要替换arr[low]的值
                arr[low] = arr[high];
//            System.out.println("arr[low] = arr[high]： " + Arrays.toString(arr));

                //若低位比基数小或者等于基数的时候，low指针++(低位右移动)，直到找到arr[low]>基数
                while (low < high && arr[low] <= basic) {
                    low++;
                }
                //当退出循环时，arr[low]大于基数，则需要替换到高位去
                arr[high] = arr[low];

//            System.out.println(" arr[high] = arr[low]： " + Arrays.toString(arr));
//            System.out.println(++count + "次： " + Arrays.toString(arr));
            }
            arr[low] = basic;
//            System.out.println("low: " + low + " high:" + high);
//            System.out.println(Arrays.toString(arr));

            //左边递归
            quickSort(arr, start, low);
            //右边递归
            quickSort(arr, low + 1, end);
        }
    }
}
