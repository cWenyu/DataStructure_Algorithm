package stack;

public class Calculator {
    public static void main(String[] args) {
        String expression = "3+2*6-2";
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        String tempNum = "";
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        char chr = ' ';

        while (index < expression.length()) {
            chr = expression.substring(index, index + 1).charAt(0);
            if (operStack.isOperator(chr)) { //判断是不是运算符
                //判断operStack是否为空
                if (operStack.isEmpty()) {
                    operStack.push(chr);
                } else {
                    //1.如果符号栈有操作符，就进行比较,如果当前的操作符的优先级小于或者等于栈中的操作符,就需要从数栈中pop出两个数,
                    //2.在从符号栈中pop出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈
                    if (operStack.priority(chr) <= operStack.priority(operStack.peak())) {
                        //弹出 num1 num2 oper
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        //计算 并压入相应的栈
                        int res = operStack.calculate(num1, num2, oper);
//                        System.out.printf("index %d结果: %d\n", index, res);
                        numStack.push(res);
                        operStack.push(chr);
                    } else {
                        operStack.push(chr);
                    }
                }
            } else { //如果是数字
                //numStack.push(ch - 48); //? "1+3" '1' => 1
                //分析思路
                //1. 当处理多位数时，不能发现是一个数就立即入栈，因为他可能是多位数
                //2. 在处理数，需要向expression的表达式的index 后再看一位,如果是数就进行扫描，如果是符号才入栈
                //3. 因此我们需要定义一个变量 字符串，用于拼接
                tempNum += chr;

                //如果chr是expression的最后一个字符则直接压入
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(tempNum));
                } else {
                    //判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，则tempNum入栈
                    //注意是看后一位，不是index++
                    if (operStack.isOperator(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(tempNum));
                        //重要的!!!!!!, tempNum
                        tempNum = "";
                    }
                }
            }
            index++;
        }

//        System.out.println("numStack");
//        numStack.print();
//        System.out.println("operStack");
//        operStack.print();
        //当表达式扫描完毕，就顺序的从 数栈和符号栈中pop出相应的数和符号，并运行.
        while (true) {
            //operStack为空，运算完毕
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            int res = numStack.calculate(num1, num2, oper);
//            System.out.printf("%d, %d,%d,运算结果%d\n", num1, num2, oper, res);
            numStack.push(res);//入栈
        }

        //结束循环，numStack只剩下最后一个数字，即为运算结果
        int res = numStack.pop();
        System.out.printf("%s = %d", expression, res);
    }
}

class ArrayStack2 {
    private int maxsize;
    private int[] stack;
    private int top = -1;

    public ArrayStack2(int maxsize) {
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

    //判断符号优先级
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    //判断是否为符号
    public boolean isOperator(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算
    public int calculate(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
//                System.out.println("+");
                res = num2 + num1;
                break;
            case '-':
//                System.out.println("-");
                res = num2 - num1;
                break;
            case '*':
//                System.out.println("*");
                res = num2 * num1;
                break;
            case '/':
//                System.out.println("/");
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

    public int peak() {
        return stack[top];
    }

}
