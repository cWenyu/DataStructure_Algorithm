package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//逆波兰计算器（只支持整数部分）
public class PolandNotation {
    public static void main(String[] args) {
        //中缀表达式转后缀表达式
        //1.中缀表达式
        String infixExpression = "30+((3+5)*7)/3-1";
        //2.中缀表达式放入list中
        List<String> infixList = toInfixList(infixExpression);
        System.out.println("中缀表达式list： " + infixList);
        //3.中缀list转后缀list
        List<String> suffixExpressionList = infixListTosuffixExpression(infixList);
        System.out.println("后缀表达式list： " + suffixExpressionList);
        //4.后缀表达式计算结果
        int result = calculator(suffixExpressionList);
        System.out.println(result);

        /*
        //1.后缀表达式
        String suffixExpression = "30 4 + 5 * 6 -";
        //后缀表达式转到ArrayList 中，更容易获取每个元素
        List<String> list = getListString(suffixExpression);
        int result = calculator(list);
        System.out.println(result);
        */
    }

    public static List<String> infixListTosuffixExpression(List<String> list) {
        Stack<String> s1 = new Stack<>(); //用于判断操作符以及小括号
        List<String> s2 = new ArrayList<>(); //代替了stack s2，因为没有弹出操作
        for (String s : list) {
            if (s.matches("\\d+")) {
                //遇到数字时直接加入S2
                s2.add(s);
            } else if (s.equals("(")) {
                //如果是左括号 ( 直接压入 S1
                s1.push(s);
            } else if (s.equals(")")) {
                //如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();
            } else {
                //当item的优先级小于等于s1栈顶运算符, 将s1栈顶的运算符弹出并加入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较
                while (!s1.isEmpty() && getOperatorPriority(s) <= getOperatorPriority(s1.peek())) {
                    s2.add(s1.pop());
                }
                s1.push(s);
            }
        }
        while (s1.size() > 0) {
            s2.add(s1.pop());
        }
        return s2;
    }

    public static int getOperatorPriority(String str) {
        switch (str) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            default:
                return 0;
        }
    }

    public static List<String> toInfixList(String str) {
        List<String> list = new ArrayList<>();
        int index = 0;
        String temp;//用于拼接字符串
        char chr;
        do {
            chr = str.charAt(index);
            if (chr > 57 || chr < 48) {
                list.add("" + chr);
                index++;
            } else {
                temp = "";
                while (index < str.length() && (str.charAt(index) >= 48) && (str.charAt(index) <= 57)) {
                    temp += str.charAt(index);//拼接
                    index++;
                }
                list.add(temp);
            }
        } while (index < str.length());
        return list;
    }

    public static List<String> getListString(String str) {
        List<String> lis = new ArrayList<String>();
        //分割str
        String[] split = str.split(" ");
        //将每个元素写入list中
        for (String s : split) {
            lis.add(s);
        }
        return lis;
    }

    public static int calculator(List<String> list) {
        Stack<String> stack = new Stack<>();
        for (String item : list) {
            if (item.matches("\\d+")) {
                //匹配是否为数字（字符串类型的0-9 +表示匹配到多位数）
                stack.add(item);
            } else {
                //num2 此时是栈顶元素
                //num1 是次顶元素
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                switch (item) {
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num1 - num2;
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        res = num1 / num2;
                        break;
                    default:
                        throw new RuntimeException("错误的运算符： " + item);
                }
                stack.push("" + res);
            }
//            System.out.printf("item: %s, stack %s\n",item, stack);
        }
        return Integer.parseInt(stack.pop());
    }

}