package ca.jrvs.practice.codingChallenge;

import java.util.Stack;

/**
 * ticket: https://www.notion.so/jarvisdev/Valid-Parentheses-4b91cccadb6f4c6ca322618594d44e6b
 *
 * time complexity O(n)
 */
public class ValidParentheses {
  public boolean isValid (String pattern) {
    char[] patternArray = pattern.toCharArray();
    Stack<Character> stack = new Stack<>();

    for (Character c : patternArray) {
      switch (c) {
        case '(' :
        case '{' :
        case '[' :
                    stack.push(c);
                    break;
        case ')' :
                    if (!stack.isEmpty() && stack.peek().equals('(')) {
                      stack.pop();
                    } else return false;
                    break;
        case '}' :
                    if (!stack.isEmpty() && stack.peek().equals('{')) {
                      stack.pop();
                    } else return false;
                    break;
        case ']' :
                    if (!stack.isEmpty() && stack.peek().equals('[')) {
                      stack.pop();
                    } else return false;
                    break;
      }
    }

    if (!stack.isEmpty()) {
      return false;
    }
    return true;
  }

}
