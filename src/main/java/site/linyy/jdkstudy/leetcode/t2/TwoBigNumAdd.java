package site.linyy.jdkstudy.leetcode.t2;

import java.math.BigDecimal;
import java.util.Calendar;

public class TwoBigNumAdd {

    // 两个超大数相加，性能比BigDecimal要好很多
    // 目前两个数字位数需要相等，待优化
    // 目前两个数字的构造比较繁琐，待优化

    public static void main(String[] args) {

        System.out.println(Calendar.getInstance().getTimeInMillis());

        BigDecimal aa = new BigDecimal("34633463346334633463");
        BigDecimal bb = new BigDecimal("95749574957495749574");
        System.out.println(aa.add(bb).toPlainString());

        System.out.println(Calendar.getInstance().getTimeInMillis());

        A a = new A(3);
        A b = new A(4);
        A a1 = new A(6);
        A b1 = new A(7);
        A a2 = new A(4);
        A b2 = new A(5);
        A a3 = new A(3);
        A b3 = new A(9);
        A a4 = new A(3);
        A b4 = new A(4);
        A a5 = new A(6);
        A b5 = new A(7);
        A a6 = new A(4);
        A b6 = new A(5);
        A a7 = new A(3);
        A b7 = new A(9);
        A a8 = new A(3);
        A b8 = new A(4);
        A a9 = new A(6);
        A b9 = new A(7);
        A a10 = new A(4);
        A b10 = new A(5);
        A a11 = new A(3);
        A b11 = new A(9);
        A a12 = new A(3);
        A b12 = new A(4);
        A a13 = new A(6);
        A b13 = new A(7);
        A a14 = new A(4);
        A b14 = new A(5);
        A a15 = new A(3);
        A b15 = new A(9);
        A a16 = new A(3);
        A b16 = new A(4);
        A a17 = new A(6);
        A b17 = new A(7);
        A a18 = new A(4);
        A b18 = new A(5);
        A a19 = new A(3);
        A b19 = new A(9);
        a.next = a1;
        b.next = b1;
        a1.next = a2;
        b1.next = b2;
        a2.next = a3;
        b2.next = b3;
        a3.next = a4;
        b3.next = b4;
        a4.next = a5;
        b4.next = b5;
        a5.next = a6;
        b5.next = b6;
        a6.next = a7;
        b6.next = b7;
        a7.next = a8;
        b7.next = b8;
        a8.next = a9;
        b8.next = b9;
        a9.next = a10;
        b9.next = b10;
        a10.next = a11;
        b10.next = b11;
        a11.next = a12;
        b11.next = b12;
        a12.next = a13;
        b12.next = b13;
        a13.next = a14;
        b13.next = b14;
        a14.next = a15;
        b14.next = b15;
        a15.next = a16;
        b15.next = b16;
        a16.next = a17;
        b16.next = b17;
        a17.next = a18;
        b17.next = b18;
        a18.next = a19;
        b18.next = b19;
        A x = a(a,b);
        System.out.println(o(a));
        System.out.println(o(b));
        System.out.println(o(x));
        System.out.println(Calendar.getInstance().getTimeInMillis());
    }

    public static String o(A a){
        StringBuffer x = new StringBuffer();
        while(a.next != null){
            x.append(a.num);
            a  = a.next;
        }
        x.append(a.num);
        return x.reverse().toString();
    }

    public static A a(A a,A b){

        A c = new A();
        A result = c;
        c.num = a.num + b.num;
        boolean flag = false;
        if(c.num > 9){
            flag = true;
            c.num = c.num%10;
        }
        while(a.next != null){
            A d = new A();

            a = a.next;
            b = b.next;

            int x = a.num + b.num + (flag ? 1 : 0);

            if(x > 9){
                flag = true;
                d.num = x%10;
            }else{
                d.num = x;
                flag = false;
            }
            c.next = d;
            c = c.next;
        }
        if(flag) {
            c.next = new A();
            c.next.num = 1;
        }
        return result;
    }

}