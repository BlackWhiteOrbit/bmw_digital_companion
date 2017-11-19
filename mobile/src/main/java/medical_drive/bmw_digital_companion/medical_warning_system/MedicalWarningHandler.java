package medical_drive.bmw_digital_companion.medical_warning_system;

import medical_drive.bmw_digital_companion.bmw_rest_services.OurBmwCommandsImpl;

/**
 * Created by Nicolas on 18/11/2017.
 */

public class MedicalWarningHandler extends  Thread{

    private final String DIZZINESS_HEADER = "Dizziness state changed: ";
    private final String BLOOD_SUGAR_HEADER = "Blood sugar state changed: ";
    private final String EMERGENCY_MESSAGE = "Emergency was called! Help is coming soon! : ";

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
        start();
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

        if (oldDizzinessWarning == actualDizzinessWarning) {
            return;
        }

        switch (actualDizzinessWarning) {
            case NOT_DIZZY:
                stopWarningSystems();
                infoMessage(DIZZINESS_HEADER + actualDizzinessWarning.getMessage());
                break;
            case DIZZY:
                stopWarningSystems();
                warningMessage(DIZZINESS_HEADER + actualDizzinessWarning.getMessage());
                break;
            case HIGH_DIZZINESS:
                startWarningSystems();
                emergencyMessage(DIZZINESS_HEADER + actualDizzinessWarning.getMessage());
                break;
        }
    }

    private void handleBloodSugarWarnings() {

        if (oldBloodSugarWarning == actualBloodSugarWarning) {
            return;
        }

        switch (actualBloodSugarWarning) {
            case BLOOD_SUGAR_TOO_HIGH_INFO:
                stopWarningSystems();
                infoMessage(BLOOD_SUGAR_HEADER + actualBloodSugarWarning.getMessage());
                break;
            case NORMAL_BLOOD_SUGAR:
                stopWarningSystems();
                infoMessage(BLOOD_SUGAR_HEADER + actualBloodSugarWarning.getMessage());
                break;
            case BLOOD_SUGAR_TOO_LOW_INFO:
                stopWarningSystems();
                infoMessage(BLOOD_SUGAR_HEADER + actualBloodSugarWarning.getMessage());
                break;
            case WARNING:
                stopWarningSystems();
                warningMessage(BLOOD_SUGAR_HEADER + actualBloodSugarWarning.getMessage());
                break;
            case DANGER_WARNING:
                startWarningSystems();
                emergencyMessage(BLOOD_SUGAR_HEADER + actualBloodSugarWarning.getMessage());
                break;
            case EMERGENCY_CALL:
                startWarningSystems();
                emergencyMessage(BLOOD_SUGAR_HEADER + actualBloodSugarWarning.getMessage());
                if (actualDizzinessWarning == DizzinessWarnings.HIGH_DIZZINESS) {
                    //TODO improve
                    emergencyMessage(EMERGENCY_MESSAGE);
                }
                break;
        }
    }

    private void startWarningSystems() {
        if (!warningSystemsOn) {
            warningSystemsOn = true;
            ourBmwCommandsImpl.startWarningSystem();
            //For testing
            System.out.println("Warning system started");
        } else {
            System.out.println("Warning system is still in progress");
        }
    }

    private void stopWarningSystems() {
        if(warningSystemsOn
                && actualBloodSugarWarning != BloodSugarWarnings.DANGER_WARNING
                && actualBloodSugarWarning != BloodSugarWarnings.EMERGENCY_CALL) {
            warningSystemsOn = false;
            ourBmwCommandsImpl.stopWarningSystem();
            //For testing
            System.out.println("Warning system stopped");
        } else {
            System.out.println("Warning system is still stopped");
        }
    }

    private void infoMessage(String message) {
        //TODO Do something with the message!
        System.out.println(message);
    }

    private void warningMessage(String message) {
        //TODO Do something with the message!
        System.out.println(message);
    }

    private void emergencyMessage(String message) {
        //TODO Do something with the message!
        System.out.println(message);
    }
}
