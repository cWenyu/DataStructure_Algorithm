package search;

import java.util.ArrayList;

public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 4, 4, 5, 5, 5, 6, 7, 8, 9, 10, 10};
        ArrayList<Integer> results = insertValueSearch(arr, 0, arr.length - 1, 4);
        System.out.println(results);
    }

    public static ArrayList<Integer> insertValueSearch(int[] arr, int left, int right, int target) {
        System.out.println("N 次");
        //因为arr是有序列表，当target< arr[0] 或者 target > arr[arr.length-1]，target已经不在arr序列中
        if (left > right || target < arr[0] || target > arr[arr.length - 1]) {
            return new ArrayList<Integer>();
        }

        //自适应的mid
        int mid = left + (right - left) * (target - arr[left]) / (arr[right] - arr[left]);

        //1.target > arr[mid],向右递归
        if (target > arr[mid]) {
            System.out.println("target > arr[mid]");
            return insertValueSearch(arr, mid + 1, right, target);
        }
        //2.target < arr[mid],向左递归
        else if (target < arr[mid]) {
            System.out.println("target < arr[mid]");
            return insertValueSearch(arr, left, mid - 1, target);
        }
        //3.target == arr[mid]
        else {
            System.out.println("target = arr[mid]");
            ArrayList<Integer> results = new ArrayList<>();
            results.add(mid);
            int temp = mid + 1;
            //3.1 向左循环
            while (true) {
                //如果temp超过arr.length或者arr[temp] != target时(因为是有序列表)，就可以退出了
                if (temp > arr.length - 1 || arr[temp] != target) {
                    break;
                }
                results.add(temp);
                temp++;
            }
            //3.2 向右循环
            temp = mid - 1;
            while (true) {
                //如果temp超过arr.length或者arr[temp] != target时(因为是有序列表)，就可以退出了
                if (temp < 0 || arr[temp] != target) {
                    break;
                }
                results.add(temp);
                temp--;
            }
            return results;
        }
    }
}
