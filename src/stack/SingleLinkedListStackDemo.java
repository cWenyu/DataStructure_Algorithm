package stack;

import java.util.Scanner;

public class SingleLinkedListStackDemo {
    public static void main(String[] args) {
        SingleLinkedListStack singleLinkedListStack = new SingleLinkedListStack();
        boolean bol = true;
        while (bol) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("其他 退出");
            System.out.println("print");
            System.out.println("push");
            System.out.println("pop");
            String key = scanner.next();
            switch (key) {
                case "print":
                    singleLinkedListStack.print();
                    break;
                case "push":
                    System.out.print("enter a number: ");
                    int num = scanner.nextInt();
                    singleLinkedListStack.push(num);
                    break;
                case "pop":
                    try {
                        int res = singleLinkedListStack.pop();
                        System.out.println("弹出的数字： "+res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    bol = false;
                    break;
            }
        }
        System.out.println("程序结束");
    }
}


class SingleLinkedListStack {
    private Node head = new Node(0);

    public void push(int num) {
        Node newNode = new Node(num);
        if (head.next == null) {
            head.next = newNode;
        } else {
            Node temp = head.next;
            head.next = newNode;
            newNode.next = temp;
        }
    }

    public int pop(){
        if(head.next == null){
          throw new RuntimeException("栈满~~~~");
        }
        int data = head.next.data;
        head.next = head.next.next;
        return data;
    }

    public void print(){
        if(head.next == null){
            System.out.println("栈满~~");
            return;
        }
        Node cur = head.next;
        while (true){
            System.out.println(cur.data);
            if(cur.next == null){
                break;
            }
            cur = cur.next;
        }
    }

}

class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                '}';
    }
}
