package com.qinxc.basic;


public class TestInherit {

    public static void main(String[] args){

        father father = new father();
        father child = new child();
//        System.out.println(father.num);
        father.target();
//        System.out.println(child.num);
        child.target();
    }

}

class father {
    public int num;
    public void target(){
        System.out.println(num);
        System.out.println("father");
    };
}

class child extends father {
    public int num = 2;
}