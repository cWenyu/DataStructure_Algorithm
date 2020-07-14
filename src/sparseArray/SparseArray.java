package sparseArray;

public class SparseArray {
    public static void main(String[] args) {
        //原始2D-array
        int[][] chessArray = new int[11][11];
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;

        System.out.println("original cheese array");
        for (int[] row : chessArray) {
            for (int num : row) {
                System.out.printf("%d\t", num);
            }
            System.out.println();
        }

        //得到有效数据的总个数
        int sum = 0;
        for (int[] row : chessArray) {
            for (int num : row) {
                if (num != 0) {
                    sum++;
                }
            }
        }

        System.out.println("sum: " + sum);
        //构造稀疏函数
        int[][] sparsearray = new int[sum + 1][3];
        //row
        sparsearray[0][0] = 11;
        //col
        sparsearray[0][1] = 11;
        //sum
        sparsearray[0][2] = sum;

        //将有效数据写入稀疏数组
        int count = 1;
        for (int i = 1; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArray[i][j] != 0) {
                    sparsearray[count][0] = i;
                    sparsearray[count][1] = j;
                    sparsearray[count][2] = chessArray[i][j];
                    count++;
                }

            }
        }

        //输出稀疏数组
        System.out.println("sparse Array");
        System.out.printf("row\tcol\tvalue\n");
        for (int[] row : sparsearray) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //还原2D-array
        int row = sparsearray[0][0];
        int col = sparsearray[0][1];
        int[][] chessArray1 = new int[row][col];
        for (int i = 1; i < sum+1; i++) {
            chessArray1[sparsearray[i][0]][sparsearray[i][1]] = sparsearray[i][2];
        }

        System.out.println("还原后： ");
        for (int[] row1 : chessArray1) {
            for (int num : row1) {
                System.out.printf("%d\t", num);
            }
            System.out.println();
        }

    }
}
