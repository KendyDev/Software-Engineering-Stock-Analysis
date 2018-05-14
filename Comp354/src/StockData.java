import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;
/**
 * Created by Kendy on 2016-10-16.
 * Adjusted by Mandeep on 2016-11-27
 * 
 * This class is used to store a single Stock Data Point
 */
public class StockData implements Serializable{

    /*
	 *	Declaration of Variables
	 */
	private static final long serialVersionUID = 1L;
	String	symbol;
    double	highPrice;
    double  lowPrice;
    double  closePrice;
    long	volume;
    double  openPrice;
    Date	stockDate;
    DecimalFormat decimalFormat = new DecimalFormat("#,##0");

    /*
     * Constructor
     */
    public StockData(){}
    
    public StockData(String symbol, double highPrice, double lowPrice, double closePrice, double openPrice, long volume, Date stockDate) {
		super();
		this.symbol = symbol;
		this.highPrice = highPrice;
		this.lowPrice = lowPrice;
		this.closePrice = closePrice;
		this.volume = volume;
		this.stockDate = stockDate;
		this.openPrice =openPrice;
	}

    /*
     * Getter/Setter Methods
     */
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public double getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(double highPrice) {
		this.highPrice = highPrice;
	}

	public double getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(double lowPrice) {
		this.lowPrice = lowPrice;
	}

	public double getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(double closePrice) {
		this.closePrice = closePrice;
	}

	public long getVolume() {
		return volume;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}

	public Date getStockDate() {
		return stockDate;
	}

	public void setStockDate(Date stockDate) {
		this.stockDate = stockDate;
	}

	public DecimalFormat getDecimalFormat() {
		return decimalFormat;
	}

	public void setDecimalFormat(DecimalFormat decimalFormat) {
		this.decimalFormat = decimalFormat;
	}

	public double getOpenPrice(){
		return openPrice;
	}
	
	public void setOpenPrice(double openPrice){
		this.openPrice = openPrice;
	}
	/*
	 * Equivalent to .toString() method
	 */
	public String getQuotes(){
        return   "Ticker Symbol: " + this.getSymbol() + "\n" +
                "High Price of Stock: $"  + this.getHighPrice() + "\n" +
                "Low Price of Stock: $"  + this.getHighPrice() + "\n" +
                "Close Price of Stock: $"  + this.getHighPrice() + "\n" +
                "Volume : " + decimalFormat.format(this.getVolume()) + "\n\n" +
                "Stock Date: " + stockDate.getTime();
    }

}