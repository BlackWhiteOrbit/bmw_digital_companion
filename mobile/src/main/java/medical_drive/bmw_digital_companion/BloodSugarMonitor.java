package medical_drive.bmw_digital_companion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import medical_drive.bmw_digital_companion.datamodel.GlucoseHistory;

public class BloodSugarMonitor implements Runnable {
	
	private double time;
	private double glucoseConcentration = 0;
	private FileInputStream inputStreamSource;
	private boolean abort = false;;
	private static final int TIME_INTERVAL = 4000;
	private GlucoseHistory glucoseHistory;
	
	public BloodSugarMonitor(){		
		inputStreamInit();
		setGlucoseHistory(new GlucoseHistory());
	}


	private void inputStreamInit() {
		try {
			inputStreamSource = new FileInputStream("sensorGlucose");
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found !");
		}
	}
	

	@Override
	public void run() {
		
		BufferedReader input = new BufferedReader(
				new InputStreamReader(inputStreamSource));
		String line = null;

		String responseData = "0";
		
		while(!abort){
			readIn(input, line, responseData);
			safeData(glucoseConcentration);
			System.out.println("Sensored Glucose-Level: "+glucoseConcentration);
			
			try {
				Thread.sleep(TIME_INTERVAL);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}


	private synchronized void safeData(double glucoseConcentration) {
		glucoseHistory.addWithCurrentTime(glucoseConcentration);
	}


	private Double readIn(BufferedReader input, String line,
			String responseData) {
		try {
			if((responseData = input.readLine()) != null) {
				glucoseConcentration = Double.parseDouble(responseData); 
			}
			else{abort = true;}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Something went wrong!");
		}
		
		return glucoseConcentration;
	}


	/**
	 * @return the glucoseConcentration
	 */
	public double getGlucoseConcentration() {
		return glucoseConcentration;
	}

	/**
	 * @param glucoseConcentration the glucoseConcentration to set
	 */
	public void setGlucoseConcentration(double glucoseConcentration) {
		this.glucoseConcentration = glucoseConcentration;
	}


	/**
	 * @return the time
	 */
	public double getTime() {
		return time;
	}


	/**
	 * @param time the time to set
	 */
	public void setTime(double time) {
		this.time = time;
	}


	/**
	 * @return the glucoseHistory
	 */
	public GlucoseHistory getGlucoseHistory() {
		return glucoseHistory;
	}


	/**
	 * @param glucoseHistory the glucoseHistory to set
	 */
	public void setGlucoseHistory(GlucoseHistory glucoseHistory) {
		this.glucoseHistory = glucoseHistory;
	}

}
