package com.phodal.learing;

public class Main {

    public static void main(String[] args) {
        int result_add=new Cal().add(1,2);
        int result_sub=new Cal2().sub(2,1);
        System.out.println("Hello,s");
        mprint(result_add);
        mprint(result_sub);
    }

    private static void mprint(int result_sub) {
        System.out.println(result_sub);
    }

}