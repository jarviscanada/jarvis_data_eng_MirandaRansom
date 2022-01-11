package ca.jrvs.apps.practice;

import ca.jrvs.apps.grep.JavaGrepImp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LambdaStreamExcImp implements LambdaStreamExc{

  /**
   * Create a String stream from array note: arbitrary number of value will be stored in an array
   *
   * @param strings
   * @return
   */
  @Override
  public Stream<String> createStrStream(String... strings) {
    return Stream.of(strings);
  }

  /**
   * Convert all strings to uppercase please use createStrStream
   *
   * @param strings
   * @return
   */
  @Override
  public Stream<String> toUpperCase(String... strings) {
    return Stream.of(strings).map(String::toUpperCase);
  }

  /**
   * filter strings that contains the pattern
   *
   * @param stringStream
   * @param pattern
   * @return
   */
  @Override
  public Stream<String> filter(Stream<String> stringStream, String pattern) {
    return stringStream.filter(string -> Pattern.matches(pattern, string));
  }

  /**
   * Create a intStream from an arr[]
   *
   * @param arr
   * @return
   */
  @Override
  public IntStream createIntStream(int[] arr) {
    return IntStream.of(arr);
  }

  /**
   * Convert a stream to a list
   *
   * @param stream
   * @return
   */
  @Override
  public <E> List<E> toList(Stream<E> stream) {
    return stream.collect(Collectors.toList());
  }

  /**
   * Convert an intStream to list
   *
   * @param intStream
   * @return
   */
  @Override
  public List<Integer> toList(IntStream intStream) {
    return intStream.boxed().collect(Collectors.toList());
  }

  /**
   * Create an IntString range from start to end inclusive
   *
   * @param start
   * @param end
   * @return
   */
  @Override
  public IntStream createIntStream(int start, int end) {
    return IntStream.of(start, end);
  }

  /**
   * Convert an intStream to a doubleStream and compute square root of each element
   *
   * @param intStream
   * @return
   */
  @Override
  public DoubleStream squareRootIntStream(IntStream intStream) {
    return intStream.mapToDouble(num -> Math.sqrt(num) );
  }

  /**
   * filter all even number and return odd number from an intStream
   *
   * @param intStream
   * @return
   */
  @Override
  public IntStream getOdd(IntStream intStream) {
    return intStream.filter(num -> num % 2 == 1);
  }

  /**
   * Return a lambda function that print a message with a prefix and suffix Useful to format logs
   * <p>
   * sout: start>Message body<end
   *
   * @param prefix str
   * @param suffix str
   * @return
   */
  @Override
  public Consumer<String> getLambdaPrinter(String prefix, String suffix) {

    Consumer<String> lambdaPrinter = message -> {
      System.out.println(prefix.concat(message).concat(suffix));
    };

    return lambdaPrinter;
  }

  /**
   * Print each message with a given printer use: 'getLambdaPrinter' method
   * <p>
   * sout: msg:a! msg:b! msg:c!
   *
   * @param messages
   * @param printer
   */
  @Override
  public void printMessages(String[] messages, Consumer<String> printer) {
      for(String message : messages){
        printer.accept(message);
      }
  }

  /**
   * Print all odd number from a intStream use: 'createIntStream' and 'getLambdaPrinter'
   * <p>
   * sout: odd number:1! odd number:2! odd number:3!
   *
   * @param intStream
   * @param printer
   */
  @Override
  public void printOdd(IntStream intStream, Consumer<String> printer) {
      intStream.filter(num -> num % 2 == 1).forEach(num -> printer.accept(String.valueOf(num)));
  }

  /**
   * Square each number from the input use: flatMap
   *
   * @param ints
   * @return
   */
  @Override
  public Stream<Integer> flatNestedInt(Stream<List<Integer>> ints) {
    return ints.flatMap(Collection::stream).map(num -> num * num);
  }

  public static void main(String[] args) {
    final Logger logger = LoggerFactory.getLogger(JavaGrepImp.class);
    BasicConfigurator.configure();
    LambdaStreamExcImp streamImp = new LambdaStreamExcImp();

    logger.info("createStrStream returns {}", streamImp.createStrStream("str1", "str2", "str3"));
    logger.info("toUpperCase returns {}", streamImp
        .toUpperCase("str1", "str2", "str3")
        .collect(Collectors.toList()));
    logger.info("filter returns {}", streamImp.filter(Stream.of("stream", "stream", "lst"), "stream")
        .collect(Collectors.toList()));
    logger.info("createIntStream returns {}", streamImp.createIntStream(new int[] {1, 3, 5, 7, 8}));
    logger.info("toList returns {}", streamImp.toList(Stream.of("here", "there", "everywhere")));
    logger.info("toList from intStream returns {}", streamImp.toList(IntStream.of(1, 2, 3, 4, 5)));
    logger.info("createIntStream returns {}", streamImp.createIntStream(1, 10));
    logger.info("sqaureRootIntStream returns {}", streamImp.squareRootIntStream(IntStream.of(4, 8, 100))
        .boxed()
        .collect(Collectors.toList()));
    logger.info("getOdd returns {}", streamImp.getOdd(IntStream.of(1, 2, 3, 4, 5, 6, 7, 8,9, 10))
        .boxed()
        .collect(Collectors.toList()));
    logger.info("getLambdaPrinter returns -not implemented-");
    logger.info("printMessages outputs");
    streamImp.printMessages(new String[]{"message 1", "message 2"},
        streamImp.getLambdaPrinter("msg: ", "!"));
    logger.info("printOdd returns outputs");
    streamImp.printOdd(IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), streamImp.getLambdaPrinter("Odd number: ", "!"));
    ArrayList<Integer> list1 = new ArrayList<>();
    list1.add(2);
    ArrayList<Integer> list2 = new ArrayList<>();
    list2.add(4);
    logger.info("flatNestedInt returns {}", streamImp.flatNestedInt(Stream.of(list1, list2)).
        collect(Collectors.toList()));
  }
}
