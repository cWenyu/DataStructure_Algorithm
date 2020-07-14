package singleLinkedList;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        //增加
//        doubleLinkedList.add(hero1);
//        doubleLinkedList.add(hero2);
//        doubleLinkedList.add(hero3);
//        doubleLinkedList.add(hero4);

        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero4);

        doubleLinkedList.print();

        //删除
        System.out.println("删除");
        doubleLinkedList.delete(2);
        doubleLinkedList.delete(4);
        doubleLinkedList.print();

        //修改
        System.out.println("修改");
        HeroNode2 heroNode5 = new HeroNode2(1, "难受", "香菇");
        doubleLinkedList.print();
    }
}

class DoubleLinkedList {
    HeroNode2 head = new HeroNode2(0, "", "");

    //不考虑编号时候，找到当前最后一个节点，最后一个节点指向新节点
    public void add(HeroNode2 newNode) {
        HeroNode2 cur = head;
        while (true) {
            if (cur.next == null) {
                break;
            }
            cur = cur.next;
        }
        cur.next = newNode;
        newNode.prev = cur;
    }

    public void addByOrder(HeroNode2 newNode) {
        HeroNode2 cur = head;
        boolean flag = false; //用于判断是否重复
        while (true) {
            if (cur.next == null) {
                break; //是个空链表
            }
            if (cur.next.no > newNode.no) {
                break;
            }
            if (cur.next.no == newNode.no) {
                flag = true;
                break;
            }
            cur = cur.next;
        }
        if (flag) {
            System.out.printf("编号%d已存在\n", newNode.no);
        } else {
            if (cur.next != null) {
                cur.next.prev = newNode;
                newNode.next = cur.next;
            }
            cur.next = newNode;
            newNode.prev = cur;
        }
    }

    //删除
    public void delete(int no) {
        if (head.next == null) {
            System.out.println("空链表，无法删除");
        }
        HeroNode2 cur = head.next;
        boolean flag = false; //是否找到应该删除的节点

        while (true) {
            if (cur == null) {
                break;//到头了都没找到
            }
            if (cur.no == no) {
                flag = true;
                break;
            }
            cur = cur.next;
        }

        if (flag) {
            cur.prev.next = cur.next;
            if (cur.next != null) {
                //若不是最后一个节点则要将后一个节点和前一个节点相连接
                cur.next.prev = cur.prev;
            }
        } else {
            System.out.printf("没有找到编号 %d\n", no);
        }
    }

    //修改
    public void update(HeroNode2 newNode) {
        if (head.next == null) {
            System.out.println("是个空链表");
        }

        HeroNode2 cur = head.next;
        boolean flag = false;
        while (true) {
            if (cur.next == null) {
                //到末尾都没找到
                break;
            }
            if (cur.no == newNode.no) {
                flag = true;
                break;
            }
            cur = cur.next;
        }

        if (flag) {
            cur.name = newNode.name;
            cur.nickname = newNode.nickname;
        } else {
            System.out.printf("编号 %d 不存在\n", newNode.no);
        }
    }

    public void print() {
        if (head.next == null) {
            System.out.println("是个空链表");
        }

        HeroNode2 cur = head.next;
        while (cur != null) {
            System.out.println(cur);
            cur = cur.next;
        }
    }

    public boolean isEmpty() {
        if (head.next == null) {
            return true;
        }
        return false;
    }

}

class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 prev; //指向前一个节点

    //初始化节点
    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}