package medical_drive.bmw_digital_companion.medical_warning_system;

import java.util.Timer;
import java.util.TimerTask;

import medical_drive.bmw_digital_companion.bmw_rest_services.BmwRestCommands;
import medical_drive.bmw_digital_companion.bmw_rest_services.BmwRestCommandsImpl;
import medical_drive.bmw_digital_companion.bmw_rest_services.OurBmwCommandsImpl;

/**
 * Created by Nicolas on 18/11/2017.
 */

public class MedicalWarningHandler extends  Thread{

    private WarningSystem dizzinessWarningSystem;
    private WarningSystem bloodSugarWarningSystem;

    private DizzinessWarnings actualDizzinessWarning = null;
    private BloodSugarWarnings actualBloodSugarWarning = null;
    private DizzinessWarnings oldDizzinessWarning = null;
    private BloodSugarWarnings oldBloodSugarWarning = null;

    private OurBmwCommandsImpl ourBmwCommandsImpl;
    private boolean warningSystemsOn = false;

    private boolean isRunning = false;


    public MedicalWarningHandler() {
        dizzinessWarningSystem = new DizzinessWarningSystem();
        bloodSugarWarningSystem = new BloodSugarWarningSystem();
        ourBmwCommandsImpl = new OurBmwCommandsImpl();
    }

    @Override
    public void run() {
        while (isRunning) {
            handleWarnings();
        }
    }

    public void startMedicalThread() {
        isRunning = true;
        run();
    }

    public void stopMedicalThread() {
        isRunning = false;
    }

    private void handleWarnings() {
        actualDizzinessWarning = (DizzinessWarnings) dizzinessWarningSystem.giveWarnings();
        actualBloodSugarWarning = (BloodSugarWarnings) bloodSugarWarningSystem.giveWarnings();

        handleDizzinessWarnings();
        handleBloodSugarWarnings();

        oldDizzinessWarning = actualDizzinessWarning;
        oldBloodSugarWarning = actualBloodSugarWarning;
    }

    private void handleDizzinessWarnings() {

        if (oldDizzinessWarning != actualDizzinessWarning) {
            handleMessage(actualDizzinessWarning.getMessage());
        }

        switch (actualDizzinessWarning) {
            case NOT_DIZZY:
                stopWarningSystems();
                break;
            case DIZZY:
                stopWarningSystems();
                break;
            case HIGH_DIZZINESS:
                startWarningSystems();
                break;
        }
    }

    private void handleBloodSugarWarnings() {

        if (oldBloodSugarWarning != actualBloodSugarWarning) {
            handleMessage(actualBloodSugarWarning.getMessage());
        }

        switch (actualBloodSugarWarning) {
            case BLOOD_SUGAR_TOO_HIGH_INFO:
                stopWarningSystems();
                break;
            case NORMAL_BLOOD_SUGAR:
                stopWarningSystems();
                break;
            case BLOOD_SUGAR_TOO_LOW_INFO:
                stopWarningSystems();
                break;
            case WARNING:
                stopWarningSystems();
                break;
            case DANGER_WARNING:
                stopWarningSystems();
                break;
            case EMERGENCY_CALL:
                stopWarningSystems();
                if (actualDizzinessWarning == DizzinessWarnings.HIGH_DIZZINESS) {
                    //Emergency call
                }
                break;
        }
    }

    private void startWarningSystems() {
        if (!warningSystemsOn) {
            warningSystemsOn = true;
            ourBmwCommandsImpl.startWarningSystem();
        }
    }

    private void stopWarningSystems() {
        if(warningSystemsOn
                && actualBloodSugarWarning != BloodSugarWarnings.DANGER_WARNING
                && actualBloodSugarWarning != BloodSugarWarnings.EMERGENCY_CALL) {
            warningSystemsOn = false;
            ourBmwCommandsImpl.stopWarningSystem();
        }
    }

    private void handleMessage(String message) {
        //TODO Do something with the message!
        System.out.println(message);
    }
}
