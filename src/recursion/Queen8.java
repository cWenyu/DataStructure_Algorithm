package recursion;

public class Queen8 {
    /*max代表了8个皇后 8*8的棋盘
    arr存储存放的结果
    arr = {0 , 4, 7, 5, 2, 6, 1, 3}
    arr的含义：
    第1个皇后放置于棋盘的第1行第1列，（元素下标为0，值为0）
    第2个皇后放置于棋盘的第2行第5列，（元素下标为1，值为4）
    第3个皇后放置于棋盘的第3行第8列，（元素下标为2，值为7）
    ...
    */
    int max = 8;
    int[] arr = new int[max];
    static int count = 0;
    public static void main(String[] args) {
        Queen8 quee = new Queen8();
        quee.check(0);
        System.out.println("count: "+count);
    }

    /**
     * @param n 是第几位皇后
     */
    private void check(int n) {
        //check第n个皇后 如果n ==max 所有皇后check完，结束
        if (n == max) {
            print();
            return;
        }

        //用一个for循环一次检验每一个皇后
        for (int i = 0; i < max; i++) {
            //1.现将这个皇后放入第n行的第一列
            arr[n] = i; //第一次在第一列 i = 0; 第二次i++，在第一列 i = 1;....
            //判断当放置第n个皇后到i列时，是否冲突
            if (judge(n)) {
                //如果不冲突 接着放n+1个皇后,即开始递归
                check(n + 1);
            }
            //如果冲突了，继续for循环，即为 arr[n] = i+1;
        }
    }

    //判断是否冲突，冲突返回false，不冲突返回true
    //每一个皇后和之前的n-1位皇后进行判断，是否冲突
    //冲突的条件：1.处在同一列  arr[i] == arr[n] 此时n为第n个皇后，同时也是第n个皇后所处行的位置
    //2.处在对角线 Math.abs(n-i) == Math.abs(arr[n]-arr[i])， 如果看成一个正方形（边都相等，则在对角线）
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        return true;
    }

    //打印每一个成功的步骤
    private void print() {
        count++;
        for (int i : arr) {
            System.out.printf("%d ", i);
        }
        System.out.println();
    }
}
