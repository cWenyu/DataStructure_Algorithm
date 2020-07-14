package queue;

import java.util.Scanner;

public class queue_array {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        Scanner scanner = new Scanner(System.in);
        boolean bool = true;
        while (bool) {
            //menu
            System.out.println("e(exit) 退出程序");
            System.out.println("a(add) 增加数字到队列中");
            System.out.println("g(get) 获取数字");
            System.out.println("d(display) 显示队列");
            System.out.println("p(peek) 查看队列头部数据");
            char key = scanner.next().charAt(0);
            switch (key) {
                case 'e':
                    bool = false;
                    System.out.println("退出程序");
                    break;
                case 'a':
                    System.out.print("请输入数字");
                    int num = scanner.nextInt();
                    queue.add(num);
                    break;
                case 'g':
                    try {
                        System.out.printf("从队列中取出数字：%d \n", queue.get());
                    } catch (Exception e) {
                        //TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'd':
                    try {
                        queue.displayQueue();
                    } catch (Exception e) {
                        //TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'p':
                    try {
                        System.out.println("队列头部： " + queue.peek());
                    } catch (Exception e) {
                        //TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
    }
}

class ArrayQueue {
    private int maxsize;
    private int front = -1; //指向队列首个元素的前一位
    private int rear = -1; //指向队列的尾部元素（最后一个元素）
    private int[] arr;

    public ArrayQueue(int maxsize) {
        this.maxsize = maxsize;
        //初始化array
        this.arr = new int[maxsize];
    }

    public boolean isEmpty() {
        return this.rear == this.front;
    }

    public boolean isFull() {
        return this.rear == maxsize - 1;
    }

    public void add(int num) {
        if (isFull()) {
            System.out.println("队列满，不能添加");
            return;
        }
        this.rear++;
        this.arr[rear] = num;
    }

    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("空队列~");
        }
        this.front++;
        return this.arr[this.front];
    }

    public void displayQueue() {
        for (int i = 0; i < this.arr.length; i++) {
            System.out.printf("第%d个数: %d \n", i, this.arr[i]);
        }
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("空队列");
        }
        return this.arr[front + 1];
    }
}
