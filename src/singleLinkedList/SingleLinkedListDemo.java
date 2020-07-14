package singleLinkedList;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);

        singleLinkedList.addByOrder(hero2);

//        singleLinkedList.addByOrder(hero3);
//        singleLinkedList.addByOrder(hero4);

        //before update
//        System.out.println("before update");
//        singleLinkedList.print();
//
//        HeroNode hero5 = new HeroNode(3, "老吴", "智多星！！");
//        singleLinkedList.update(hero5);
//        System.out.println("after update");
//        singleLinkedList.print();

        //删除
//        singleLinkedList.delete(1);
//        singleLinkedList.delete(4);
//        singleLinkedList.delete(2);
//        singleLinkedList.delete(5);
//        System.out.println("after delete");
//        singleLinkedList.print();

        // 获取到单链表的节点的个数(不计头结点)
//        int length = getLength(singleLinkedList.getHead());
//        System.out.println("单链表的节点的个数: " + length);
//
//        // 获取到单链表的节点的个数(不计头结点)
//        int index = 4;
//        HeroNode result = getIndexNode(singleLinkedList.getHead(), index);
//        System.out.printf("倒数第 %d 个节点是 %s \n", index, result);
//
//        System.out.println("翻转单向链表");
//        reverseLinkedList(singleLinkedList.getHead());
//        singleLinkedList.print();
//
//        System.out.println("逆序打印单向链表，不破坏原有数据结构");
//        reversePrint(singleLinkedList.getHead());

        //合并两个有序单向链表
        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        singleLinkedList1.addByOrder(hero3);
        singleLinkedList1.addByOrder(hero4);
        singleLinkedList1.addByOrder(hero1);

        HeroNode resultNode = mergeTwoOrdered(singleLinkedList.getHead().next, singleLinkedList1.getHead().next);

        while (resultNode != null) {
            System.out.println(resultNode);
            resultNode = resultNode.next;
        }


    }

    //合并两个有序的单链表，合并之后的链表依然有序
    public static HeroNode mergeTwoOrdered(HeroNode la, HeroNode lb) {
        if (la == null) {
            return lb;
        }
        if (lb == null) {
            return la;
        }

        if (la.no < lb.no) {
            la.next = mergeTwoOrdered(la.next, lb);
//            la.next = mergeTwoOrdered( lb,la.next); 和顺序无关
            return la;
        } else {
            lb.next = mergeTwoOrdered(la, lb.next);
            return lb;
        }

    }


    //逆序打印 不破坏原有单向链表的结构
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        while (cur != null) {
            stack.add(cur);
            cur = cur.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    //将单链表反转
    public static void reverseLinkedList(HeroNode head) {
        if (head.next == null || head.next.next == null) {
            return;
        }
        HeroNode cur = head.next;
        HeroNode newHead = new HeroNode(0, "", "");
        HeroNode next = null; //current 节点的下一个节点
        while (cur != null) {
            next = cur.next;
            cur.next = newHead.next;
            newHead.next = cur;
            cur = next;
        }
        head.next = newHead.next;
    }


    //查找单链表中的倒数第k个结点 【新浪面试题】
    //思路
    //1. 编写一个方法，接收head节点，同时接收一个index
    //2. index 表示是倒数第index个节点
    //3. 先把链表从头到尾遍历，得到链表的总的长度 getLength
    //4. 得到size 后，我们从链表的第一个开始遍历 (size-index)个，就可以得到
    //5. 如果找到了，则返回该节点，否则返回nulll

    public static HeroNode getIndexNode(HeroNode head, int k) {
        int length = getLength(head);
        if (head.next == null || k <= 0 || k > length) {
            return null;
        }
        //定义指针
        HeroNode cursor = head.next;
        for (int i = 0; i < length - k; i++) {
            cursor = cursor.next;
        }
        return cursor;
    }

    //方法：获取到单链表的节点的个数(如果是带头结点的链表，需求不统计头节点)

    /**
     * @param head 链表的头节点
     * @return 返回的就是有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int count = 0;
        HeroNode cursor = head.next;
        while (cursor != null) {
            count++;
            cursor = cursor.next;
        }
        return count;
    }
}


class SingleLinkedList {
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return this.head;
    }

    //不考虑编号时候，找到当前最后一个节点，最后一个节点指向新节点
    public void add(HeroNode heroNode) {
        //因为head节点不能动，因此我们需要一个辅助遍历 temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true) {
            //找到链表的最后
            if (temp.next == null) {//
                break;
            }
            //如果没有找到最后, 将将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //将最后这个节点的next 指向 新的节点
        temp.next = heroNode;
    }

    /* 需求
     * 第二种方式在添加英雄时，根据排名将英雄插入到指定位置
     * (如果有这个排名，则添加失败，并给出提示)
     * */
    public void addByOrder(HeroNode node) {
        //遍历找出应该插入的位置
        HeroNode cursor = head;
        boolean flag = false;//用于判断节点是否存在
        while (true) {
            if (cursor.next == null) {
                break;
            }
            if (cursor.next.no > node.no) {
                break;
            } else if (cursor.next.no == node.no) {
                flag = true;
                break;
            }
            cursor = cursor.next;
        }
        if (flag) {
            System.out.printf("节点已存在 编号： %d\n", node.no);
        } else {
            node.next = cursor.next;
            cursor.next = node;
        }
    }

    public void update(HeroNode updateNode) {
        HeroNode cursor = head;
        boolean flag = false; // 判断是否找到要更新的节点
        while (true) {
            if (cursor.next == null) {
                break;
            }
            if (cursor.no == updateNode.no) {
                flag = true;
                break;
            }
            cursor = cursor.next;
        }
        cursor.name = updateNode.name;
        cursor.nickName = updateNode.nickName;
    }

    public void delete(int no) {
        HeroNode cursor = head;
        boolean flag = false; // 用于判断是否找到了该节点的前一个节点
        while (true) {
            if (cursor.next == null) {
                break;
            }
            if (cursor.next.no == no) {
                flag = true;
                break;
            }
            cursor = cursor.next;
        }
        if (flag) {
            cursor.next = cursor.next.next;
        } else {
            System.out.printf("编号 %d 不存在\n", no);
        }
    }

    //遍历打印
    public void print() {
        //1.如果是空链表
        if (head.next == null) {
            System.out.println("是个空链表");
            return;
        }
        //2.非空链表
        HeroNode cursor = head.next;
        while (true) {
            if (cursor.next == null) {
                System.out.println(cursor);
                break;
            }
            System.out.println(cursor);
            cursor = cursor.next;
        }
    }
}

class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
