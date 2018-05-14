import java.awt.BorderLayout;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

/*
 * Class implemented to save the last 5 most recent searches made by the GUI from the YahooFinanceApi
 * It is saved on the defined path of the variable filePath under the file name variable fileName
 */
public class History{

	/*
	 * Declaration/Initialization of Variables
	 */
	private String filePath = System.getProperty("user.dir");
			//"C:\\Users\\Mande\\workspace\\COMP354Project\\COMP354Project\\data";
	private String fileName = "datalist.bin";
	private FileOutputStream fileOut = null;
	private ObjectOutputStream objOut = null;
	private FileInputStream fileIn = null;
	private ObjectInputStream objIn = null;
	private LinkedList<StockList> queue;
	private int queueSize;
	final private int MAX_QUEUE_SIZE = 5;
	
	
	/*
	 * Constructor;
	 * 		Verifies if file is already created.
	 * 			If yes, populate queue with file found
	 * 			If no, create new blank file and initialize 
	 */
	@SuppressWarnings("unchecked")
	public History() {
		setQueueSize(0);
		
		try {
			fileIn = new FileInputStream(filePath + "\\" + fileName); //adjust this
			objIn = new ObjectInputStream(fileIn);
			System.out.println("Previous search history loaded");
			queue = (LinkedList<StockList>) objIn.readObject();
		} catch (FileNotFoundException e) {
			System.out.println("No previous search history data saved yet.");
			queue = new LinkedList<StockList>();
			try {
				System.out.println("New search history file created in the following directory: \n" + 
										filePath + "\\" + fileName);
				saveToFile();
			} catch (Exception e1) {
					e1.printStackTrace();
			}
		} catch (IOException e2){
			e2.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if(!queue.isEmpty())
			setQueueSize(queue.size());
		
	}

	public Iterator<StockList> getStackIterator(){
		Iterator<StockList> ite =  queue.iterator();
		return ite;
	}
	public int getStackSize() {
		return queueSize;
	}

	public void setQueueSize(int queueSize) {
		this.queueSize = queueSize;
	}
	
	public void saveToFile() throws Exception{
		fileOut = new FileOutputStream(filePath + "\\" + fileName);
		objOut = new ObjectOutputStream(fileOut);
		objOut.writeObject(queue);
	}
	
	public void addToHistory(StockList obj) throws Exception{
		if(queue.size()==MAX_QUEUE_SIZE){
			queue.remove();
			queue.add(obj);
		}
		else
			queue.add(obj);
		
		saveToFile();
	}
	
	public void displayHistoryFrame(int stockNo){
		Iterator<StockList> ite = getStackIterator();
		StockList stockData = null;
		int count = 1;
		
		while(ite.hasNext()){
			stockData = ite.next();
			if (count++ == stockNo)
				break;
		}
		
		JFrame frameExt = new JFrame();
		//frameExt.setBounds(100, 100, 780, 650);
		frameExt.setSize(780, 700);
		frameExt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameExt.getContentPane().setLayout(null);
		JTextPane info = new JTextPane();
		info.setBounds(200, 490, 250, 100);
		info.setEditable(false);
		frameExt.getContentPane().add(info);
		
		
		
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		long totalAmountPre = 0;
		count = 1;
		for(StockData item: stockData.getStockList()){
			totalAmountPre += (long) item.getClosePrice();
			dataSet.addValue(totalAmountPre/count, "Simple Moving Average", Integer.toString(count));
			dataSet.addValue(item.getClosePrice(), "data", Integer.toString(count++));
		}
		info.setText("Recommendation:" + stockData.getRecommendation() + "\n"+
								"From: " + stockData.getStartDate() + "\n" + 
								"Until: " + stockData.getEndDate() +"\n" +
								"Created on: " + stockData.getCreatedOn());
		JPanel graphPanel = new JPanel();
		graphPanel.setBounds(10, 10, 744, 449);
		frameExt.getContentPane().add(graphPanel);
		JFreeChart graph = null;
			graph = ChartFactory.createLineChart("Close Market Price of " + stockData.getSymbol(), "No of " + stockData.getIntervalType() + " from Selected Date", "Price ($)", dataSet);
		CategoryPlot graphPlot = graph.getCategoryPlot();
		graphPlot.setRangeGridlinePaint(Color.CYAN);
		graphPlot.configureDomainAxes();
		
		ChartPanel graphingPanel = new ChartPanel(graph);
		graphPanel.removeAll();
		graphPanel.add(graphingPanel, BorderLayout.CENTER);
		graphPanel.validate();
		
		frameExt.setVisible(true);
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		Iterator<StockList> sl = queue.iterator();

		while(sl.hasNext()){
			StockList obj = sl.next();
			sb.append(obj.getSymbol()).append(", ").append(obj.getCreatedOn()).append(", ");
			sb.append(obj.getStartDate()).append(", ").append(obj.getEndDate()).append(", ");
			sb.append(obj.getIntervalType());
			sb.append(System.getProperty("line.separator"));
		}
		
		
		return sb.toString();
	}
}
