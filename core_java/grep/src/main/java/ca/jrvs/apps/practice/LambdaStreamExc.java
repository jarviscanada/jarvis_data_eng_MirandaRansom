package ca.jrvs.apps.practice;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public interface LambdaStreamExc {

  /**
   * Create a String stream from array
   * note: arbitrary number of value will be stored in an array
   *
   * @param strings
   * @return
   */
  Stream<String> createStrStream(String ... strings);

  /**
   * Convert all strings to uppercase
   * please use createStrStream
   *
   * @param strings
   * @return
   */
  Stream<String> toUpperCase(String ... strings);

  /**
   * filter strings that contains the pattern
   *
   * @param stringStream
   * @param pattern
   * @return
   */
  Stream<String> filter(Stream<String> stringStream, String pattern);

  /**
   * Create a intStream from an arr[]
   *
   * @param arr
   * @return
   */
  IntStream createIntStream(int[] arr);

  /**
   * Convert a stream to a list
   *
   * @param stream
   * @param <E>
   * @return
   */
  <E> List<E> toList(Stream<E> stream);

  /**
   * Convert an intStream to list
   *
   * @param intStream
   * @return
   */
  List<Integer> toList(IntStream intStream);

  /**
   * Create an IntString range from start to end inclusive
   *
   * @param start
   * @param end
   * @return
   */
  IntStream createIntStream(int start, int end);

  /**
   * Convert an intStream to a doubleStream
   * and compute square root of each element
   *
   * @param intStream
   * @return
   */
  DoubleStream squareRootIntStream(IntStream intStream);

  /**
   * filter all even number and return odd number from an intStream
   *
   * @param intStream
   * @return
   */
  IntStream getOdd(IntStream intStream);

  /**
   * Return a lambda function that print a message with a prefix and suffix
   * Useful to format logs
   *
   * sout:
   * start>Message body<end
   *
   * @param prefix str
   * @param suffix str
   * @return
   */
  Consumer<String> getLambdaPrinter(String prefix, String suffix);

  /**
   * Print each message with a given printer
   * use: 'getLambdaPrinter' method
   *
   * sout:
   * msg:a!
   * msg:b!
   * msg:c!
   *
   * @param messages
   * @param printer
   */
  void printMessages(String[] messages, Consumer<String> printer);

  /**
   * Print all odd number from a intStream
   * use: 'createIntStream' and 'getLambdaPrinter'
   *
   * sout:
   * odd number:1!
   * odd number:2!
   * odd number:3!
   *
   * @param intStream
   * @param printer
   */
  void printOdd(IntStream intStream, Consumer<String> printer);

  /**
   * Square each number from the input
   * use: flatMap (2 implementations?)
   *
   * @param ints
   * @return
   */
  Stream<Integer> flatNestedInt(Stream<List<Integer>> ints);
}
