package ca.jrvs.apps.grep;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
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
    ArrayList<String> matchedLines = new ArrayList<>();

    for (File file : listFiles(this.getRootPath())) {
      for (String line : readLines(file)) {
        if (containsPattern(line)) {
          matchedLines.add(line);
        }
      }
    }
    writeToFile(matchedLines);
  }

  /**
   * Traverse a given directory and return all files
   *
   * @param rootDir input directory
   * @return files under the rootDir
   */
  @Override
  public List<File> listFiles(String rootDir) {
    List<File> filesList = new ArrayList<>();
    File root = new File(rootDir);
    File[] list = root.listFiles();

    assert list != null;
    for (File file : list){
      if(file.isDirectory()){
        List<File> moreFiles = listFiles(file.getAbsolutePath());
        filesList.addAll(moreFiles); //append files from recursive call to original list
      }
      else if(file.isFile()){
        filesList.add(file);
      }
    }
    return filesList;
  }

  /**
   * Read a file and return all the lines
   * FileReader: used to read from files
   * BufferedReader: used to read line by line
   * Character encoding: UTF-8 standard encoding
   *
   * @param inputFile file to be read
   * @return lines
   * @throws IllegalArgumentException if a given inputFile is not a file
   */
  @Override
  public List<String> readLines(File inputFile) {
    ArrayList<String> lines = new ArrayList<>();

    try {
      FileReader fileReader = new FileReader(inputFile);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String line;
      while((line = bufferedReader.readLine()) != null){
        lines.add(line);
      }
    } catch (IOException e) {
      throw new RuntimeException("Unable to read file", e);
    }
    return lines;
  }

  /**
   * check if a line contains the regex pattern (passed by user)
   * case-sensitive
   * @param line input string
   * @return true if there is a match
   */
  @Override
  public boolean containsPattern(String line) {
    Pattern regex = Pattern.compile(this.getRegex());
    Matcher matcher = regex.matcher(line);
    return matcher.find();  //use find to avoid default anchoring behaviour from matcher
  }

  /**
   * Writes lines to a file
   *
   * @param lines matches line
   * @throws IOException if write failed
   */
  @Override
  public void writeToFile(List<String> lines) throws IOException {
    FileWriter out = new FileWriter(this.getOutFile());
    for (String line : lines){
      out.write(line + "\n");
    }
    out.close();
  }

  @Override
  public String getRootPath() {
    return this.rootPath;
  }

  @Override
  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }

  @Override
  public String getRegex() {
    return this.regex;
  }

  @Override
  public void setRegex(String regex) {
    this.regex = regex;
  }

  @Override
  public String getOutFile() {
    return this.outFile;
  }

  @Override
  public void setOutFile(String outFile) {
    this.outFile = outFile;
  }
}
