package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Fibonacci-Number-Climbing-Stairs-b626feea76a246f6bc49b872b980f4bb
 */
public class FibonacciStairs {
  public static int recursiveFib(int num){
    //base case
    if(num == 1 || num == 2){
      return 1;
    } else {
      return recursiveFib(num-1) + recursiveFib(num-2);
    }
  }

  public static int dynamicFib(int num){
    int[] memo = new int[num];
    memo[0] = 1;
    memo[1] = 1;

    for (int i=2; i<num; i++){
      memo[i] = memo[i-1] + memo[i-2];
    }
      return memo[num-1];
  }


}
