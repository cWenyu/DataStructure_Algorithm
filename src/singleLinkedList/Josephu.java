package singleLinkedList;

public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.add(5);
//        circleSingleLinkedList.print();
        circleSingleLinkedList.countBoy(1, 2, 5);
    }
}

class CircleSingleLinkedList {
    //创建一个first指针，初始化为null
    private Boy first = null;

    public void add(int num) {
        if (num < 1) {
            System.out.println("无效的节点数");
            return;
        }
        //创建num个Boy节点
        Boy curBoy = first;
        for (int i = 1; i <= num; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                boy.setNext(first);
                curBoy = first;
            }
            curBoy.setNext(boy);
            boy.setNext(first);
            curBoy = curBoy.getNext();
        }
    }

    public void print() {
        if (first.getNext() == first) {
            System.out.println("空链表");
            return;
        }
        Boy curBoy = first;
        while (true) {
            System.out.printf("编号 %d 的小孩\n", curBoy.getNo());
            if (curBoy.getNext() == first) {
                //已经到最后一个节点了
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    /**
     * @param k 约定编号为K的人从1开始报数（1<= k <= n）
     * @param m 报数为m的小孩出圈
     * @param n 一共有n个小孩
     */
    public void countBoy(int k, int m, int n) {
        //判断无效参数
        if (first == null || k < 1 || k > n || n < 1) {
            System.out.println("无效的参数");
            return;
        }

        //辅助节点 helper
        Boy helper = first;

        //1.helper 移动到最后一个节点
        while (true){
            if(helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }

//        System.out.println(helper);
        //移动到K的编号
        for (int i = 1; i < k; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
//        System.out.println(first);
//        System.out.println(helper);

        //进行出圈的操作
        while (true) {
            //判断是否结束循环，圈中只有一个节点
            if (helper == first) {
                break;
            }

            //移动m-1次 first & helper
            for (int i = 1; i < m; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }

            //结束for循环, first指向的节点（helper的前一个节点）即为所要出圈的节点
            System.out.printf("出圈的小孩为 %d\n", first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.println("最后的小孩为 " + first.getNo());
    }
}

class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}