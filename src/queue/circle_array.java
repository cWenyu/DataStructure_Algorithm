package queue;

import java.util.Scanner;

public class circle_array {
    public static void main(String[] args) {
        CircleArray queue = new CircleArray(4);//实际只能存储4个数字，因为有一位是预留空间
        Scanner scanner = new Scanner(System.in);
        boolean bool = true;
        System.out.println("circle array");
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

class CircleArray {
    private int maxsize;
    /* 申明：
     * front指向的是array中首个元素
     * rear指向的是array中最后一个元素的后一位
     * */
    private int front = 0;
    private int rear = 0;
    private int[] arr;

    public CircleArray(int maxsize) {
        this.maxsize = maxsize;
        //初始化array
        this.arr = new int[maxsize];
    }

    public boolean isEmpty() {
        return this.rear == this.front;
    }

    public boolean isFull() {
        return (rear + 1) % maxsize == front;
    }

    public void add(int num) {
        if (isFull()) {
            System.out.println("队列满，不能添加");
            return;
        }
        arr[rear] = num;
        rear = (rear + 1) % maxsize;
    }

    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("空队列~");
        }
        int temp = arr[front];
        System.out.println("模前" + front);
        front = (front + 1) % maxsize;
        System.out.println("front: " + front);
        return temp;
    }

    public void displayQueue() {
        //难点 从front开始遍历，一共要遍历多少个？ front+validateDate（）
        for (int i = front; i < front + validData(); i++) {
            System.out.printf("arr[%d]: %d \n", i, arr[i]);
        }
        System.out.println("有效数字的个数: " + validData());
        System.out.println("头指针位置: " + front);
        System.out.println("尾指针位置: " + rear);
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("空队列");
        }
        return this.arr[front];
    }

    public int validData() {
        return (rear + maxsize - front) % maxsize;
    }
}