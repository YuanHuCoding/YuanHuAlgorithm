package me.yuanhu.core.Algorithm.fibonacci;

import java.math.BigInteger;

/**
 * https://blog.csdn.net/javazejian/article/details/53452971
 * 斐波那契数列的实现
 *
 假如动物中有一种特殊的种类，它出生2天后就开始以每天1只的速度繁殖后代。假设第1天，有1只这样的动物（该动物刚出生，
 从第3天开始繁殖后代）。那么到第11天，共有多少只呢？
 我们先来按一般顺序思考，先不要考虑第11天，先从第1天开始，看能不能找出规律：
 【第1天】只有1只动物
 【第2天】只有1只动物，还没有繁殖后代，总量为1
 【第3天】第1天的1只动物，繁殖1个后代，总量为2
 【第4天】第1天的1只动物又繁殖1只，其他还没繁殖，总量为3
 【第5天】第1天和第3天出生的动物又繁殖1个后代，其他没有繁殖，总量为5
 【第n天】.....

 第1天 ------1
 第2天 ------1
 第3天 ------2 = 1 + 1
 第4天 ------3 = 1 + 2
 第5天 ------5 = 2 + 3
 第6天 ------8 = 3 + 5
 第7天 ------13 = 5 + 8
 */
public class Fibonacci  {

    /**
     * 斐波那契数列的实现
     * 0,1,1,2,3,5,8,13,21......
     * @param day
     */
    public long fibonacci(int day){

        if(day==0){ //F(0)=0
            return 0;
        }else if (day==1||day==2){//F(1)=1
            return 1;
        }else {
            return fibonacci(day-1)+fibonacci(day-2); //F(n)=F(n-1)+F(n-2)
        }
    }

    /**
     * 更为简洁的写法
     * @param day
     * @return
     */
    public long fib(int day) {
        return day== 0 ? 0 : (day== 1 || day==2 ? 1 : fib(day - 1) + fib(day - 2));
    }

    //BigInteger可以防止数据异常
    //BigInteger 任意大的整数，原则上是，只要你的计算机的内存足够大，可以有无限位的
    // 递推实现方式（迭代的方式效率高，时间复杂度O(n)）
    public  BigInteger fibonacciN(int n){
        if (n == 1) {
            return new BigInteger("0");
        }
        //f(0)=0;
        BigInteger n1 = new BigInteger("0");
        //f(1)=1;
        BigInteger n2 = new BigInteger("1");
        //记录最终值f(n)
        BigInteger sn = new BigInteger("0");
        for (int i = 0; i < n - 1; i++) {
            sn = n1.add(n2);//相加
            n1 = n2;
            n2 = sn;
        }
        return sn;
    }

    // 与上述相同的递推实现方式 ，使用long返回值，当n过大会造成数据溢出，计算结果可能是一个未知的负数，因此建议使用BigInteger
    public long fibonacciNormal(int n){
        if(n <= 2){
            return 1;
        }
        long n1 = 1, n2 = 1, sn = 0;
        for(int i = 0; i < n - 2; i ++){
            sn = n1 + n2;
            n1 = n2;
            n2 = sn;
        }
        return sn;
    }

    /**
     * 返回斐波那契数第n个值,n从0开始
     * 实现方式，基于数组实现
     * @param n
     * @return
     * @author mhy2011@163.com
     * @since 2015年8月18日上午9:22:15
     */
    public  int getFib3(int n){
        if(n < 0){
            return -1;
        }else if(n == 0){
            return 0;
        }else if (n == 1 || n == 2){
            return 1;
        }else{
            int[] fibAry = new int[n + 1];
            fibAry[0] = 0;
            fibAry[1] = fibAry[2] = 1;
            for(int i = 3; i <= n; i++){
                fibAry[i] = fibAry[i - 1] + fibAry[i - 2];
            }
            return fibAry[n];
        }
    }

    //测试
    public static void main(String[] args){
        Fibonacci fibonacci=new Fibonacci();
        System.out.println("第11天动物数量为:"+ fibonacci.fib(11));
        System.out.println("第11天动物数量为:"+ fibonacci.fibonacci(11));
        System.out.println("第11天动物数量为:"+ fibonacci.fibonacciN(11));
        System.out.println("第11天动物数量为:"+ fibonacci.fibonacciNormal(11));
        System.out.println("第11天动物数量为:"+ fibonacci.getFib3(11));
    }
}