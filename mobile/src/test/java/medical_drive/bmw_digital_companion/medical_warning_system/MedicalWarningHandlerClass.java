package medical_drive.bmw_digital_companion.medical_warning_system;

import org.junit.Test;

/**
 * Created by Nicolas on 18/11/2017.
 */

public class MedicalWarningHandlerClass {

    private MedicalWarningHandler medicalWarningHandler;

    @Test
    public void doMedicalWarningHandling() {
        medicalWarningHandler = new MedicalWarningHandler();
        medicalWarningHandler.startMedicalThread();

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        medicalWarningHandler.stopMedicalThread();
    }
}
