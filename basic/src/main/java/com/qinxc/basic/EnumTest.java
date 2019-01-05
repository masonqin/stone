package com.qinxc.basic;

/**
 * Created by qxc on 2018/6/20.
 */
public class EnumTest {

    enum Color{

        GREEN("绿色",1),YELLOW("黄色",2),RED("红色",3);

        private String name;
        private int index;

        private Color(String name, int index) {
            this.name = name;
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }

    public static void main(String[] args) {
        Color color = Color.GREEN;
        System.out.println(color.getName());
    }






}
