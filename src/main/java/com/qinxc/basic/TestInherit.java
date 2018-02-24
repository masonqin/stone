package com.qinxc.basic;


public class TestInherit {

    public static void main(String[] args){

        father father = new father();
        father child = new child();
        System.out.println(father.num);
        System.out.println(child.num);
    }

}

class father {
    public int num;
    public String target(){
        return "le monde";
    }

}

class child extends father {
    public int num = 2;
    public String target(){
        return "world";
    }
}