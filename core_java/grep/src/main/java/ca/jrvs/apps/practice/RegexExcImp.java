package ca.jrvs.apps.practice;
import java.util.regex.Pattern;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegexExcImp implements RegexExc{
  /**
   * return true if filename extension is jpg or jpeg (case-insensitive)
   * @param filename
   * @return
   */
  public boolean matchJpeg(String filename){
    return Pattern.matches("(?i).+\\.jpe?g", filename);
  }

  /**
   * return true if ip is valid
   * IP address range is from 0.0.0.0 to 999.999.999.999
   * @param ip
   * @return
   */
  public boolean matchIp(String ip){
    return Pattern.matches("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}", ip);
  }

  /**
   * return true if line is empty (empty, whitespace, tab)
   * @param line
   * @return
   */
  public boolean isEmptyLine(String line){
    return Pattern.matches("\\s*", line);
  }

  public static void main(String[] args) {
    //create logger
    final Logger logger = LoggerFactory.getLogger(RegexExcImp.class);
    BasicConfigurator.configure();

    RegexExcImp sample = new RegexExcImp();

    String filename = "hopper.JpeG";
    boolean isMatchJPEG = sample.matchJpeg(filename);
    logger.info("Match JPEG evaluates to {} for the value {}", isMatchJPEG, filename);

    String ip = "128.444.532.357";
    boolean isMatchIP = sample.matchIp(ip);
    logger.debug("Match IP evaluates to {} for the value {}", isMatchIP, ip);

    String line = "";
    boolean isMatchEmpty = sample.isEmptyLine(line);
    logger.debug("Match Empty Line evaluates to {} for the value", isMatchEmpty, line);
  }

}
