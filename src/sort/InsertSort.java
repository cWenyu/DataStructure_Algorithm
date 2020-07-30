package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class InsertSort {
    public static void main(String[] args) {
//        int[] arr = {32, 1, 31, 90, 80, 131};
//        int[] arr = {1, 2, 3, 4, 5, 6};
//        int[] arr = {1, 2, 4, 3, 5, 6};
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }

        //before
        Date before = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d1 = sdf.format(before);
        insertSort(arr);
        Date after = new Date();
        String d2 = sdf.format(after);
    }

    public static void insertSort(int[] arr) {
        //定义待插入的数
        int insertVal = 0;
        int insertIndex = 1 - 1;
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //判断是否需要插入
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
//            System.out.println(insertIndex + "," + insertVal + "," + Arrays.toString(arr));
        }

        /*
        //1.insertIndex 保证insertVal不越界
        //2.insertVal < arr[insertIndex] 表示insertVal还没找到要插入的位置
        //3.arr[inserIndex]要后移动
        //4.insertIndex--表明如果没有找到，insertVal要与insertIndex的前一位数进行比较
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }

        //遍历完成后说明插入的位置找到，insertIndex+1 (要插入到indexInser的后一个位置)
        System.out.println(Arrays.toString(arr));
        if (insertIndex != 0) {
            arr[insertIndex + 1] = insertVal;
        }

        System.out.println(Arrays.toString(arr));
         */
    }
}
