package medical_drive.bmw_digital_companion;

import java.io.IOException;
/*
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
*/
import java.util.ArrayList;

import medical_drive.bmw_digital_companion.BloodSugarMonitor;
import medical_drive.bmw_digital_companion.datamodel.GlucoseHistory;
import medical_drive.bmw_digital_companion.datamodel.TimeGlucose;

public class HistoryDrawer implements Runnable{

	private static final long WAITING_TIME = 5000;
	private BloodSugarMonitor monitor;
	private GlucoseHistory history;
	private boolean abort;
	
	/*
	final int portNumber = 8080;
	private ServerSocket serverSocket;
	private ObjectOutputStream os;
	*/
	
	public HistoryDrawer(){

		monitor = new BloodSugarMonitor();
		history = monitor.getGlucoseHistory();
		abort = false;
	}
	
	
	@Override
	public synchronized void run() {
		
		/*
		System.out.println("Creating server socket on port " + portNumber);
		try {
			serverSocket = new ServerSocket(portNumber);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
		
		while(!abort){
			
			ArrayList<TimeGlucose> historyList = history.getHistory();
			
			try {
				draw(historyList);
			} catch (InterruptedException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				Thread.sleep(WAITING_TIME);
				notifyAll();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		/*
		try {
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	
	}



	private void draw(ArrayList<TimeGlucose> historyList) throws InterruptedException, IOException {
		addToChart(drawTime(historyList) , drawGlucose(historyList));
	}


	double drawTime(ArrayList<TimeGlucose> historyList) throws InterruptedException {
		
		if(historyList.size() > 1 && 
				(historyList.get(historyList.size()-1) != historyList.get(historyList.size()-2))
				){
			return timeOf(historyList.get(historyList.size()-1));
		}
			Thread.sleep(WAITING_TIME);
		
		return 0;
	}

	public double drawGlucose(ArrayList<TimeGlucose> historyList) throws IOException {
		
		if(historyList.size() > 0){
			double glucose = historyList.get(historyList.size()-1).getGlucoseConcentration();
		//	send(glucose);
			return glucose;
		}
		
		try {
			Thread.sleep(WAITING_TIME);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	/*
	private void send(double glucose) throws IOException {
		
			System.out.println("DATA SENT");
		
			Socket socket = serverSocket.accept();
			os = (ObjectOutputStream) socket.getOutputStream();
			os.writeObject(glucose);

			os.flush();
		
	}
*/

	public void addToChart(double time, double glucose){
		
		//view.getSeries().getData().add(new XYChart.Data<Number, Number>(time, glucose));
		System.out.println("Time: "+Double.toString(time)+"Lvl: "+Double.toString(glucose));
		
	}


	private double timeOf(TimeGlucose timeGlucose) {
		double time = timeGlucose.getTime().getTime();
		return Math.floor(time*100)/100.0;
	}


	/**
	 * @return the monitor
	 */
	public BloodSugarMonitor getMonitor() {
		return monitor;
	}



	/**
	 * @param monitor the monitor to set
	 */
	public void setMonitor(BloodSugarMonitor monitor) {
		this.monitor = monitor;
	}



	/**
	 * @return the history
	 */
	public GlucoseHistory getHistory() {
		return history;
	}



	/**
	 * @param history the history to set
	 */
	public void setHistory(GlucoseHistory history) {
		this.history = history;
	}

}
