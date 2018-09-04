package com.wp.myboot;

public class Test {

    public static void main(String[] args) {


        System.out.println("-------");


        for (int i = 1; i < 10; i++) {
            try {
                System.out.println("---" + i + "----");
                if (i == 5) {
                    int a = 1;
                    int b = 0;
                    int c = a / b;
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("发生异常");
                throw new RuntimeException();
            }

        }

    }

}
