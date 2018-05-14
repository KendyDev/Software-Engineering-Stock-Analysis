import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;


public class PurchaseRecommendationWT {

	@Test
	public void GoodtestStockRecommendation() {
		try {
			Stock stock = YahooFinance.get("APPL");
			MyApp appObject = new MyApp();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void BadtestStockRecommendation() {
		try {
			Stock stock = YahooFinance.get("NotExistent");
			MyApp appObject = new MyApp();
			String result = appObject.StockRecommendation(stock);
			System.out.println(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
