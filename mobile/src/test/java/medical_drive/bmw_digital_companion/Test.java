package medical_drive.bmw_digital_companion;

public class Test {

	private static HistoryDrawer testDrawer;

	@org.junit.Test
	public void launchThreads() {
		
		testDrawer = new HistoryDrawer();
		
		Thread threadMonitor = new Thread(testDrawer.getMonitor());
		Thread threadDrawer = new Thread (testDrawer);
		
		threadMonitor.start();
		threadDrawer.start();
		//Platform.runLater(threadDrawer);
		
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
