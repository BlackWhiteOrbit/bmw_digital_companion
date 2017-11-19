package medical_drive.bmw_digital_companion;

import org.junit.Test;

public class BloodSugarMonitorTest {

	private BloodSugarMonitor monitor;

	@Test
	public void mainTest() {
		monitor = new BloodSugarMonitor();
		monitor.produceBloodSugarValues();
	}

	/*
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		view = new ChartView();
		scene = view.getScene();
		
		this.stage = primaryStage;
		stage.setTitle("Glucose-Level");
	       
	    stage.setScene(scene);
	    stage.show();
	    
	    launchThreads();
	}*/
	

}
