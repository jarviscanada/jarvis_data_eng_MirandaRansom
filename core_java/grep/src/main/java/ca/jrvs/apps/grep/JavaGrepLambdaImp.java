package ca.jrvs.apps.grep;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaGrepLambdaImp extends JavaGrepImp{

  public static void main(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
    }

    JavaGrepImp javaGrepLambdaImp = new JavaGrepLambdaImp();
    javaGrepLambdaImp.setRegex(args[0]);
    javaGrepLambdaImp.setRootPath(args[1]);
    javaGrepLambdaImp.setOutFile(args[2]);

    try{
      javaGrepLambdaImp.process();
    } catch (Exception e){
      e.printStackTrace();
    }

  }

  /**
   * Reads a file and returns all lines in the file
   * uses streams
   *
   * @param inputFile file to be read
   * @return lines
   * @throws IllegalArgumentException if a given inputFile is not a file
   */
  @Override
  public List<String> readLines(File inputFile) {
    List lines = new ArrayList<>();
    if(!inputFile.isFile()){
      throw new IllegalArgumentException("File attempting to be read is not a file");
    }

    try{
      FileReader fileReader = new FileReader(inputFile);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      lines = bufferedReader.lines().collect(Collectors.toList());
    }catch (IOException e) {
      e.printStackTrace();
    }
    return lines;
  }

  @Override
  public List<File> listFiles(String rootDir) {
    List<File> filesList = new ArrayList<>();
    File root = new File(rootDir);
    File[] list = root.listFiles();

    Arrays.stream(list).forEach(file -> {
      if(file.isDirectory()){
        List<File> moreFiles = listFiles(file.getAbsolutePath());
        filesList.addAll(moreFiles);
      }
      else if (file.isFile()){
        filesList.add(file);
      }
    });
    return filesList;
  }
}
