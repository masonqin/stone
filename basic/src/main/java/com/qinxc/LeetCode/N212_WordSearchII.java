package com.qinxc.LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qxc
 * @date 2019/1/21.
 */
public class N212_WordSearchII {

    public static void main(String[] args) {

        char[][] board = new char[3][];
        board[0] = new char[]{'A', 'B', 'C', 'E'};
        board[1] = new char[]{'S', 'F', 'C', 'S'};
        board[2] = new char[]{'A', 'D', 'E', 'E'};

        String[] words = new String[]{"oath", "pea", "eat", "rain"};

        List<String> result = new N212_WordSearchII().findWords(board, words);

    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        for (String word : words) {
            if (exist(board, word)) {
                result.add(word);
            }
        }
        return result;
    }

    public boolean exist(char[][] board, String word) {
        Boolean result = false;

        if (board == null) {
            return false;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    result = dfs(board, word, 0, i, j);
                    if (result == true)
                        return true;
                }
            }
        }
        return result;
    }

    public boolean dfs(char[][] board, String word, int wordIndex, int boardx, int boardy) {
        boolean result;
        if (wordIndex == word.length() - 1) {
            return true;
        }
        char temp = board[boardx][boardy];
        board[boardx][boardy] = '0';

        //up
        if (boardx - 1 >= 0 && board[boardx - 1][boardy] == word.charAt(wordIndex + 1)) {
            result = dfs(board, word, wordIndex + 1, boardx - 1, boardy);
            if (result == true) {
                board[boardx][boardy] = temp;
                return true;
            }
        }
        //down
        if (boardx + 1 < board.length && board[boardx + 1][boardy] == word.charAt(wordIndex + 1)) {
            result = dfs(board, word, wordIndex + 1, boardx + 1, boardy);
            if (result == true) {
                board[boardx][boardy] = temp;
                return true;
            }
        }
        //right
        if (boardy + 1 < board[0].length && board[boardx][boardy + 1] == word.charAt(wordIndex + 1)) {
            result = dfs(board, word, wordIndex + 1, boardx, boardy + 1);
            if (result == true) {
                board[boardx][boardy] = temp;
                return true;
            }
        }
        //left
        if (boardy - 1 >= 0 && board[boardx][boardy - 1] == word.charAt(wordIndex + 1)) {
            result = dfs(board, word, wordIndex + 1, boardx, boardy - 1);
            if (result == true) {
                board[boardx][boardy] = temp;
                return true;
            }
        }
        board[boardx][boardy] = temp;
        return false;
    }


}
