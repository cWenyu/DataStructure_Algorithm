package sort;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * @param arr 待排序的数组
     */
    public static void radixSort(int[] arr) {
        //定义一个二维数组，为10个桶,每一个桶的大小为arr.length防止放入元素的时候溢出
        int[][] buckets = new int[10][arr.length];

        //定义一个一位数组，用于存储每个桶中存入元素的个数 eg:bucketCount[0]存入的是桶的下标为0的桶中元素的个数
        int[] bucketCount = new int[10];

        //处理arr,处理位数
        //找出最大数的位数
        int max = 0;
        for (int ele : arr) {
            if (max <= ele) max = ele;
        }

        int len = (max + "").length();
        for (int i = 0, n = 1; i < len; i++, n *= 10) {
            for (int ele : arr) {
                int bucketIndex = ele / n % 10;
                buckets[bucketIndex][bucketCount[bucketIndex]] = ele;
                bucketCount[bucketIndex] += 1;
            }

//            System.out.println(Arrays.toString(bucketCount));

            //按桶的顺序依次取出数据放入原来的数组中
            int index = 0;
            for (int j = 0; j < bucketCount.length; j++) {
                if (bucketCount[j] != 0) {
                    for (int k = 0; k < bucketCount[j]; k++) {
                        arr[index] = buckets[j][k];
                        index++;
                    }
                }
                //取出数据后将bucketCount重置
                bucketCount[j] = 0;
            }
//            System.out.println(Arrays.toString(arr));
        }
//        System.out.println(Arrays.toString(arr));
    }

    public static void separate(int[] arr) {
        //第一次
        //定义一个二维数组，为10个桶,每一个桶的大小为arr.length防止放入元素的时候溢出
        int[][] buckets = new int[10][arr.length];

        //定义一个一位数组，用于存储每个桶中存入元素的个数 eg:bucketCount[0]存入的是桶的下标为0的桶中元素的个数
        int[] bucketCount = new int[10];

        //第一次处理arr,处理十位数
        for (int ele : arr) {
            int bucketIndex = ele / 1 % 10;
            buckets[bucketIndex][bucketCount[bucketIndex]] = ele;
            bucketCount[bucketIndex] += 1;
        }

        System.out.println(Arrays.toString(bucketCount));

        //按桶的顺序依次取出数据放入原来的数组中
        int index = 0;
        for (int i = 0; i < bucketCount.length; i++) {
            if (bucketCount[i] != 0) {
                for (int j = 0; j < bucketCount[i]; j++) {
                    arr[index] = buckets[i][j];
                    index++;
                }
            }
        }
        System.out.println(Arrays.toString(arr));

    }
}
