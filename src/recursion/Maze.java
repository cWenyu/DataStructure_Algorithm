package recursion;

import java.util.*;

public class Maze {
    public static void main(String[] args) {
        //row 8 colum 7
        int[][] map = new int[8][7];

        //构造map
        // 1 为墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        // 3,1 3,2也是墙
        map[3][1] = 1;
        map[3][2] = 1;

        //走之前
        System.out.println("原始迷宫");
        print(map);
        //走之后
        System.out.println("走完之后");
        recursionSetWay(map, 1, 1);
        print(map);

    }

    public static void print(int[][] map) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
    //1. map 表示地图
    //2. i,j 表示从地图的哪个位置开始出发 (1,1)
    //3. 如果小球能到 map[6][5] 位置，则说明通路找到.
    //4. 约定： 当map[i][j] 为 0 表示该点没有走过 当为 1 表示墙  ； 2 表示通路可以走 ； 3 表示该点已经走过，但是走不通
    //5. 在走迷宫时，需要确定一个策略(方法) 下->右->上->左 , 如果该点走不通，再回溯
    //小球怎么样走和设置的策略有关，策略不同，所走的路径也不同

    /**
     * @param map 地图
     * @param i
     * @param j
     * @return
     */
    public static boolean recursionSetWay(int[][] map, int i, int j) {
        //终点 7,6 为2时候表明已经到达终点
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                //如果当前这个点还没有走过
                //按照策略 下->右->上->左  走
                // 将小求走过的位置map[i][j]标记为2 表示通路
                map[i][j] = 2;
                if (recursionSetWay(map, i + 1, j)) { //向下走
                    return true;
                } else if (recursionSetWay(map, i, j + 1)) { //向右走
                    return true;
                } else if (recursionSetWay(map, i - 1, j)) {//向上走
                    return true;
                } else if (recursionSetWay(map, i, j - 1)) {
                    return true;
                } else {
                    //3 表示该点已经走过，但是走不通
                    map[i][j] = 3;
                    return false;
                }
            } else {
                // 如果map[i][j] != 0 , 可能是 1， 2， 3 不能再走了，return false
                return false;
            }
        }
    }
}
