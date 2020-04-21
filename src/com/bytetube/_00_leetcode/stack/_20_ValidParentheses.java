package com.bytetube._00_leetcode.stack;

import java.util.Stack;

public class _20_ValidParentheses {
    public static void main(String[] args) {
        String s = "{[()]}";
        boolean valid = isValid2(s);
        System.out.println(valid);
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {//说明遍历到的是右括号
                if (stack.isEmpty()) return false;
                char left = stack.pop();
                if (left == '(' && c != ')') return false;
                if (left == '[' && c != ']') return false;
                if (left == '{' && c != '}') return false;
            }

        }

        return stack.isEmpty();

    }

    public static boolean isValid2(String s) {
        while (s.contains("()") || s.contains("{}") || s.contains("[]")) {
            s.replace("()", "");
            s.replace("{}", "");
            s.replace("[]", "");
        }

        return s.isEmpty();
    }
}