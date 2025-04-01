package io.sahil.assignment4;

import java.util.Stack;

/**
 * This class represents the parenthesis problem
 *
 * @author Sahil Saini
 */
public class Parenthesis {

    public static void main(String[] args) {
        Parenthesis parenthesis = new Parenthesis();
        boolean result = parenthesis.doParenthesisMatch("((dsfafa)sdfa)");
        System.out.println(result);
    }

    private boolean doParenthesisMatch(String text) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            switch (c) {
                case '(':
                    stack.push(c);
                    break;
                case ')':
                    if (stack.isEmpty()) {
                        return false;
                    } else {
                        stack.pop();
                    }
                    break;
            }
        }
        if (stack.isEmpty()) {
            return true;
        }
        return false;
    }

}
