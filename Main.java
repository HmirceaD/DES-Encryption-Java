package com.mirceadan.stacker;

public class Main {

    public static void main(String[] args) {
        DES des = new DES();

        System.out.println(des.encryptMessage("0123456789ABCDEF", "133457799BBCDFF1"));

        //T-DES

        System.out.println(des.tDes("0123456789ABCDEF", "133457799BBCDFF1", "224452239DDCDAA5", "111133339AABBCC1"));
    }
}
