package medical_drive.bmw_digital_companion;

import android.app.Activity;
import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import medical_drive.bmw_digital_companion.datamodel.GlucoseHistory;
import medical_drive.bmw_digital_companion.medical_warning_system.BloodSugarWarningSystem;
import medical_drive.bmw_digital_companion.medical_warning_system.MedicalWarningHandler;

public class BloodSugarMonitor {
	
	private double time;
	private int glucoseConcentration = 0;
	//private FileInputStream inputStreamSource;
	private boolean abort = false;;
	private static final int TIME_INTERVAL = 1000;
	private GlucoseHistory glucoseHistory;
	int index = 0;


	private int[] bloodSugarLevels = {
			110, 105, 96, 93, 93, 95, 90, 80, 70, 69, 70, 68, 67,
			65, 64, 62, 59, 58, 60, 65, 78, 84, 93, 100, 105};
	
	public BloodSugarMonitor(){		
		//inputStreamInit();
		glucoseHistory = new GlucoseHistory();
	}

	public void produceBloodSugarValues() {
		
		//BufferedReader input = new BufferedReader(new InputStreamReader(
		//		inputStreamSource));

		BloodSugarWarningSystem bloodSugarWarningSystem = new BloodSugarWarningSystem();
		MedicalWarningHandler medicalWarningHandler = new MedicalWarningHandler();

		while(!abort){
			readIn();
			safeData(glucoseConcentration);
			System.out.println("Sensored Glucose-Level: " + glucoseConcentration);

			//TODO Please refactore me! I want to die!
			medicalWarningHandler.handleWarnings(bloodSugarWarningSystem.giveWarnings(glucoseConcentration));

			try {
				Thread.sleep(TIME_INTERVAL);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private synchronized void safeData(int glucoseConcentration) {
		glucoseHistory.addWithCurrentTime(glucoseConcentration);
	}


	private int readIn() {
		try {
			if (bloodSugarLevels.length > index) {
				glucoseConcentration = bloodSugarLevels[index];
				index++;
			} else {
				index = 0;
				//abort = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Something went wrong!");
		}
		
		return glucoseConcentration;
	}


	/**
	 * @return the glucoseConcentration
	 */
	public int getGlucoseConcentration() {
		return glucoseConcentration;
	}

	/**
	 * @param glucoseConcentration the glucoseConcentration to set
	 */
	public void setGlucoseConcentration(int glucoseConcentration) {
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
