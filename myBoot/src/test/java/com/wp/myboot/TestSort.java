package com.wp.myboot;

public class TestSort {


    public static void main(String[] args) throws Exception {
        int[] str={5,3,2,1,5,4,6,3,2,4,2,3,10};
        test(str);



        for (int i=0;i<str.length;i++){
            System.out.print(str[i]+",");
        }

    }


    public static void test(int[] score) {
        for (int i = 0; i < score.length - 1; i++) {
            for (int j = 0; j < score.length - 1 - i; j++)// j开始等于0，
            {
                if (score[j] < score[j + 1]) {
                    int temp = score[j];
                    score[j] = score[j+1];
                    score[j+1] = temp;
                }
            }

        }
    }
}


