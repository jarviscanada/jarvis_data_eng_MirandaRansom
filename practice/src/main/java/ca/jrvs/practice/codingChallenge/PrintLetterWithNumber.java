package ca.jrvs.practice.codingChallenge;

public class PrintLetterWithNumber {
  public static String printLetterWithNumber(String string) {
     char[] letterArray = string.toLowerCase().toCharArray();
     StringBuilder build = new StringBuilder();

     for (char letter : letterArray) {
       build.append(letter);
       build.append(letter - 'a' + 1);
     }
     return build.toString();
  }
}
