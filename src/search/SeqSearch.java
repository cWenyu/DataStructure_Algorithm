package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234, 1};
        ArrayList arrayList = sequenceSearch(arr, 1);
        System.out.println(arrayList);
    }

    public static ArrayList<Integer> sequenceSearch(int[] arr, int target) {
        ArrayList<Integer> results = new ArrayList<>();
        //遍历循环,将符合target的元素的下标加入results中
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                results.add(i);
            }
        }
        return results;
    }
}
