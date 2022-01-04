package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaGrepImp implements JavaGrep{
  final Logger logger = LoggerFactory.getLogger(JavaGrepImp.class);

  private String regex;
  private String rootPath;
  private String outFile;

  public static void main(String[] args) {
    BasicConfigurator.configure();
    if (args.length != 3){
      throw new IllegalArgumentException("USEAGE: JavaGrep regex rootPath outFile");
    }

    //capture arguments and set properties
    JavaGrepImp javaGrepImp = new JavaGrepImp();
    javaGrepImp.setRegex(args[0]);
    javaGrepImp.setRootPath(args[1]);
    javaGrepImp.setOutFile(args[2]);

    try{
      javaGrepImp.process();
    } catch (Exception ex){
      javaGrepImp.logger.error("Error: Unable to process", ex);
    }
  }

  /**
   * Top level search workflow
   *
   * @throws IOException
   */
  @Override
  public void process() throws IOException {
    logger.info("Processing...");
  }

  /**
   * Traverse a given directory and return all files
   *
   * @param rootDir input directory
   * @return files under the rootDir
   */
  @Override
  public List<File> listFiles(String rootDir) {
    return null;
  }

  /**
   * Read a file and return all the lines Explain: FileReader, BufferedReader and character
   * encoding
   *
   * @param inputFile file to be read
   * @return lines
   * @throws IllegalArgumentException if a given inputFile is not a file
   */
  @Override
  public List<String> readLines(File inputFile) {
    return null;
  }

  /**
   * check if a line contains the regex pattern (passed by user)
   *
   * @param line input string
   * @return true if there is a match
   */
  @Override
  public boolean containsPattern(String line) {
    return false;
  }

  /**
   * Writes lines to a file Explore: FileOutputStream, OutputStreamWriter and BufferedWriter
   *
   * @param lines matches line
   * @throws IOException if write failed
   */
  @Override
  public void writeToFile(List<String> lines) throws IOException {

  }

  @Override
  public String setRootPath() {
    return null;
  }

  @Override
  public void setRootPath(String rootPath) {

  }

  @Override
  public String getRegex() {
    return null;
  }

  @Override
  public void setRegex(String regex) {

  }

  @Override
  public String getOutFile() {
    return null;
  }

  @Override
  public void setOutFile(String outFile) {

  }
}
