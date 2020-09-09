package search;

import java.util.ArrayList;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 89, 89, 89, 1000, 1234};
        ArrayList<Integer> integers = binarySearch1(arr, 0, arr.length - 1, 2);
        System.out.println("resIndexList=" + integers);
    }

    /**
     * @param arr    目标有序数组
     * @param left   起始位置
     * @param right  结束位置
     * @param target 目标数
     * @return 符合target的下标值（int）
     */
    public static int binarySearch(int[] arr, int left, int right, int target) {
        //递归结束的条件
        if (left > right) {
            return -1;
        }

        //寻找中间下标
        int mid = (right + left) / 2;

        //1. target > arr[mid] 向右递归，继续查询
        if (target > arr[mid]) {
            return binarySearch(arr, mid + 1, right, target);
        }
        //2.target < arr[mid] 向左递归，继续查询
        else if (target < arr[mid]) {
            return binarySearch(arr, left, mid - 1, target);
        }
        //3.target = arr[mid] 直接返回mid
        else {
            return mid;
        }
    }

    /**
     * @param arr    目标有序数组
     * @param left   起始位置
     * @param right  结束位置
     * @param target 目标数
     * @return 所有符合target的下标值（ArrayList<Integer>）
     */
    public static ArrayList<Integer> binarySearch1(int[] arr, int left, int right, int target) {
        //递归结束的条件
        if (left > right) {
            return new ArrayList<Integer>();
        }

        //寻找中间下标
        int mid = (right + left) / 2;

        //1. target > arr[mid] 向右递归，继续查询
        if (target > arr[mid]) {
            return binarySearch1(arr, mid + 1, right, target);
        }
        //2.target < arr[mid] 向左递归，继续查询
        else if (target < arr[mid]) {
            return binarySearch1(arr, left, mid - 1, target);
        }
        //3.target = arr[mid] 直接返回mid
        else {
            //分别扫描mid左边和右边，将符合target的下标值存入ArrayList中
            ArrayList<Integer> results = new ArrayList<>();
            results.add(mid);
            int temp = mid - 1;
            //3.1向左扫描
            while (true) {
                if (temp < 0 || arr[temp] != target) {
                    //退出
                    break;
                }
                //否则，就temp 放入到 results
                results.add(temp);
                //temp左移
                temp -= 1;
            }
            //3.2向右扫描
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != target) {
                    break;
                }

                results.add(temp);
                temp++;
            }
            return results;
        }
    }
}
