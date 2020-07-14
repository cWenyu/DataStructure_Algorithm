package stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(5);
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
                    arrayStack.print();
                    break;
                case "push":
                    System.out.print("enter a number: ");
                    int num = scanner.nextInt();
                    arrayStack.push(num);
                    break;
                case "pop":
                    try {
                        int res = arrayStack.pop();
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

class ArrayStack {
    private int maxsize;
    private int[] stack;
    private int top = -1;

    public ArrayStack(int maxsize) {
        this.maxsize = maxsize;
        stack = new int[maxsize];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == maxsize - 1;
    }

    //入栈
    public void push(int num) {
        if (isFull()) {
            System.out.println("栈满，无法添加~~~~");
            return;
        }
        top++;
        stack[top] = num;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，无法弹出~~~");
        }

        int temp = stack[top];
        top--;
        return temp;
    }

    //打印
    public void print() {
        if (isEmpty()) {
            System.out.println("栈空~~");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]: %d\n", i, stack[i]);
        }
    }
}
