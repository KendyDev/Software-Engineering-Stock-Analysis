import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

/*
 * StockList is used to store the entire data set pulled from the Yahoo Finance API
 * in order to store it for offline purposes. It incorporates the StockData Class
 */

public class StockList implements Serializable {
	
	/*
	 *	Declaration of Variables 
	 */
	private static final long serialVersionUID = -6741173894122902476L;
	private Date createdOn;
	private Date startDate;
	private Date endDate;
	private String recommendation;
	private Interval intervalType;
	private ArrayList<StockData> stockList;
	private String symbol;
	
	/*
	 * Constructor
	 */
	public StockList(String symbol, Date createdOn, Date startDate, Date endDate, Interval intervalType) {
		stockList = new ArrayList<StockData>();
		this.createdOn =createdOn;
		this.startDate =startDate;
		this.endDate =endDate;
		this.intervalType = intervalType;
		this.symbol = symbol;
		recommendation = "";
	}
	
	/*
	 * Add StockData to List of StockData (stockList)
	 */
	public void addToStockList(HistoricalQuote obj){
		StockData data = new StockData(obj.getSymbol(), obj.getHigh().doubleValue(), obj.getLow().doubleValue(), obj.getClose().doubleValue(), obj.getOpen().doubleValue(), obj.getVolume().longValue(), obj.getDate().getTime());
		stockList.add(data);
	}

	/*
	 * Getter/Setters below
	 */
	public Date getCreatedOn() {
		return createdOn;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public Interval getIntervalType() {
		return intervalType;
	}

	public ArrayList<StockData> getStockList() {
		return stockList;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}
	
	
	
}
