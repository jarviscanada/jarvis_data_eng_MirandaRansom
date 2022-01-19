package ca.jrvs.practice.codingChallenge;

import java.text.CharacterIterator;
import java.util.Arrays;
import java.util.stream.Stream;
import javax.swing.text.html.HTMLDocument.Iterator;

/**
 * ticket: https://www.notion.so/jarvisdev/String-to-Integer-atoi-1d137a35c9bb4613ae89761ff0a3fe13
 */
public class Atoi {

  public static int atoiJavaLibrary(String num){
    return Integer.parseInt(num);
  }

  public static int atoiManual(String num){
    int sign = 1;
    int result = 0;
    int index = 0;
    int n = num.length();

    //strip any whitespace from start of string
    while (index < n && Character.isWhitespace(num.charAt(index))){
      index++;
    }

    //set sign modifier accordingly
    if (index < n && num.charAt(index) == '-'){
      sign = -1;
    } else if (index < n && num.charAt(index) == '+'){
      sign = 1;
      index++;
    }

    //consume digits
    while (index < n && Character.isDigit(num.charAt(index))){
      int digit = num.charAt(index) - '0';

      result = 10 * result + digit;
      index++;
    }
    return sign * result;
  }
}
