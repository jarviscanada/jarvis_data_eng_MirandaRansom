package ca.jrvs.apps.trading;

import ca.jrvs.apps.trading.controller.QuoteController;
import ca.jrvs.apps.trading.service.QuoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * For learning purposes, DataSource and Jdbc Template are manually configured
 */
@SpringBootApplication(exclude = {JdbcTemplateAutoConfiguration.class,
    DataSourceAutoConfiguration.class})
public class Application implements CommandLineRunner {

  private Logger logger = LoggerFactory.getLogger(Application.class);

  @Value("${app.init.dailyList:AAPL}")
  private String[] initDailyList;

  @Autowired
  private QuoteService quoteService;

  public static void main(String[] args) throws Exception{
    SpringApplication app = new SpringApplication(Application.class);
    app.run(args);
  }

  @Override
  public void run(String... args) throws Exception {
    initDailyList = args;
    QuoteController controller = new QuoteController(quoteService);
  }
}
