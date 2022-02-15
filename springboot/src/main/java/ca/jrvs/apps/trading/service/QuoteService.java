package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class QuoteService {

  private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);

  private QuoteDao quoteDao;
  private MarketDataDao marketDataDao;

  @Autowired
  public QuoteService(QuoteDao quoteDao, MarketDataDao marketDataDao) {
    this.quoteDao = quoteDao;
    this.marketDataDao = marketDataDao;
  }

  /**
   * Update quote table against IEX source
   * -get all quotes from the db
   * -for each ticker get iexQuote
   * -convert iexQuote to quote entity
   */
  public void updateMarketData() {
    List<Quote> currentTable = findAllQuotes();
    List<Quote> updatedList = new ArrayList<>();

    for(Quote quote: currentTable) {
      IexQuote updatedIexQuote = marketDataDao.findById(quote.getTicker()).orElseThrow(NoSuchElementException::new);
      Quote updatedQuote = buildQuoteFromIexQuote(updatedIexQuote);
      updatedList.add(updatedQuote);
    }
    quoteDao.saveAll(updatedList);
  }

  /**
   * Helper method, Map a IexQuote to a Quote entity
   * Note: 'iexQuote.getLatestPrice == null` if the stock market is closed
   * Make sure to set a default values for number fields()
   */
  protected static Quote buildQuoteFromIexQuote(IexQuote iexQuote){
    Quote quote = new Quote();

    quote.setId(iexQuote.getSymbol());
    quote.setAskPrice(Double.valueOf(iexQuote.getIexAskPrice()));
    quote.setAskSize(iexQuote.getIexAskSize());
    quote.setBidPrice(iexQuote.getIexBidPrice());
    quote.setBidSize(iexQuote.getIexBidSize());
    quote.setLastPrice(iexQuote.getLatestPrice());

    return quote;
  }

  /**
   * Validate (against iex) and save given tickers to quote table
   *
   * -Get iexQuote(s)
   * -Convert each iexQuote to Quote entity
   * -Persist the quote to db
   *
   * @param tickers a list of tickers/symbols
   * @throws IllegalArgumentException if ticker is not found from iex
   */
  public List<Quote> saveQuotes(List<String> tickers){
    List<Quote> quotes = new ArrayList<>();
    for (String ticker : tickers) {
      Quote quote = saveQuote(ticker);
      quotes.add(quote);
    }
    return quotes;
  }

  /**
   * Helper method
   */
  public Quote saveQuote(String ticker) {
    IexQuote iexQuote = marketDataDao.findById(ticker).orElseThrow(NoSuchElementException::new);
    Quote quote = buildQuoteFromIexQuote(iexQuote);
    quoteDao.save(quote);
    return quote;
  }

  /**
   * Find an IexQuote
   *
   * @param ticker id
   * @return IexQuote object
   * @throws IllegalArgumentException if ticket is invalid
   */
  public IexQuote findIexQuoteByTicker(String ticker) {
    return marketDataDao.findById(ticker)
        .orElseThrow(() -> new IllegalArgumentException(ticker + "is invalid"));
  }

  /**
   * Update a given quote to quote table without validation
   * @param quote entity
   */
  public Quote saveQuote(Quote quote) {
    return quoteDao.save(quote);
  }

  /**
   * find all quotes from the quote table
   * @return a list of quotes
   */
  public List<Quote> findAllQuotes() {
    List<Quote> quoteList = new ArrayList<>();
    quoteDao.findAll().forEach(new Consumer<Quote>() {
      @Override
      public void accept(Quote quote) {
        quoteList.add(quote);
      }
    });
    return quoteList;
  }
}
