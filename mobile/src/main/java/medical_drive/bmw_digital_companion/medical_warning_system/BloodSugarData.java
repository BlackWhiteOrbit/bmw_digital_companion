package medical_drive.bmw_digital_companion.medical_warning_system;

import medical_drive.bmw_digital_companion.BloodSugarMonitor;

/**
 * Created by Nicolas on 18/11/2017.
 */

public class BloodSugarData {
    private int levelInMGPerLitre;
    private BloodSugarMonitor bloodSugarMonitor;
    private boolean running;

    public BloodSugarData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (running) {
                    bloodSugarMonitor.produceBloodSugarValues();
                    levelInMGPerLitre = bloodSugarMonitor.getGlucoseConcentration();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public int getLevelInMGPerLitre() {
        return levelInMGPerLitre;
    }
}
