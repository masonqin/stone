//package com.qinxc.basic;
//
///**
// *
// */
//
//public class Huffman {
//
//    /**
//     * ignore exception
//     * @param cs : characters
//     * @param freqs : frequency of each character
//     */
//    public static void huffman(char[] cs, double[] freqs) {
//        int n = cs.length;
//        MinHeap<Code> heap = new MinHeap<Code>(cs.length);
//        Code[] codes = new Code[n];
//        for (int i = 0; i < n; i++) {
//            Code c = new Code(cs[i], freqs[i]);
//            heap.add(c); // 以最小堆来每次取出频率最小的两个
//            codes[i] = c; // 保存所有的叶子节点
//        }
//        Code c, c1, c2;
//        while (heap.size() > 1) {
//            c1 = heap.removeMin();
//            c2 = heap.removeMin();// 取出两个最小的
//
//            c = new Code('\0', c1.freq + c2.freq);
//            c.left = c1;
//            c.right = c2;
//            c1.parent = c;
//            c2.parent = c;
//            heap.add(c); // 组合成一个新的节点，并放入堆中，继续比较
//
//            System.out.println(c1.val + "+" + c2.val + " : " + c1.freq + "+" + c2.freq + " = " + c.freq);
//        }
//        c = heap.removeMin(); // 二叉树根节点
//
//        StringBuffer sb;
//        for (int i = 0; i < n; i++) {
//            c = codes[i]; // 从每个叶子节点，向上追溯，直到根节点，确定每个字符的编码
//            sb = new StringBuffer();
//            while (c != null) {
//                if (c.parent != null) {
//                    if (c == c.parent.left) {
//                        sb.insert(0, 0); // 如果是左边的，编码取1
//                    } else {
//                        sb.insert(0, 1); // 如果是右边的，编码取1
//                    }
//                }
//                c = c.parent;
//            }
//            System.out.println(codes[i].val + " : " + sb.toString());
//        }
//    }
//    public static void main(String[] args) {
//        char[] cs = { 'a', 'b', 'c', 'd', 'e', 'f' };
//        double[] freqs = { 45, 13, 12, 16, 9, 5 };// %
//        huffman(cs, freqs);
//        // http://zh.wikipedia.org/wiki/%E5%AD%97%E6%AF%8D%E9%A2%91%E7%8E%87
//        char[] cs2 = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
//                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
//                'x', 'y', 'z' };
//        double[] freqs2 = { 8.167, 1.492, 2.782, 4.253, 12.702, 2.228, 2.015,
//                6.094, 6.966, 0.153, 0.772, 4.025, 2.406, 6.749, 7.507, 1.929,
//                0.095, 5.987, 6.327, 9.056, 2.758, 0.978, 2.360, 0.150, 1.974,
//                0.074 };
//        huffman(cs2, freqs2);
//    }
//}
//class Code implements Comparable<Code> {
//
//    public char val;
//
//    public double freq;
//
//    public Code left, right, parent;
//
//    public Code(char val, double freq) {
//        this.val = val;
//        this.freq = freq;
//    }
//
//    @Override
//    public int compareTo(Code c) {
//        double d = freq - c.freq;
//        return d > 0 ? 1 : (d == 0 ? 0 : -1);
//    }
//}
//
