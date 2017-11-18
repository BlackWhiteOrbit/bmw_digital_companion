package medical_drive.bmw_digital_companion.medical_warning_system;

import medical_drive.bmw_digital_companion.bmw_rest_services.BmwRestCommands;
import medical_drive.bmw_digital_companion.bmw_rest_services.BmwRestCommandsImpl;

/**
 * Created by Nicolas on 18/11/2017.
 */

public class MedicalWarningHandler {

    private WarningSystem dizzinessWarningSystem;
    private WarningSystem bloodSugarWarningSystem;
    private DizzinessWarnings oldDizzinessWarning = null;
    private BloodSugarWarnings oldBloodSugarWarning = null;
    private BmwRestCommands bmwRestCommands;

    public MedicalWarningHandler() {
        dizzinessWarningSystem = new DizzinessWarningSystem();
        bloodSugarWarningSystem = new BloodSugarWarningSystem();
        bmwRestCommands = new BmwRestCommandsImpl();
    }

    public void handleWarnings() {
        DizzinessWarnings dizzinessWarning = (DizzinessWarnings) dizzinessWarningSystem.giveWarnings();
        BloodSugarWarnings bloodSugarWarning = (BloodSugarWarnings) bloodSugarWarningSystem.giveWarnings();

        handleDizzinessWarnings(dizzinessWarning);

        handleBloodSugarWarnings(bloodSugarWarning, dizzinessWarning);

        oldDizzinessWarning = dizzinessWarning;
        oldBloodSugarWarning = bloodSugarWarning;
    }

    private void handleDizzinessWarnings(DizzinessWarnings dizzinessWarning) {


        switch (dizzinessWarning) {
            case NOT_DIZZY:
                break;
            case DIZZY:
                break;
            case HIGH_DIZZINESS:
                break;
        }
    }

    private void handleBloodSugarWarnings(BloodSugarWarnings bloodSugarWarning, DizzinessWarnings dizzinessWarning) {


        switch (bloodSugarWarning) {
            case BLOOD_SUGAR_TOO_HIGH_INFO:
                break;
            case NORMAL_BLOOD_SUGAR:
                break;
            case BLOOD_SUGAR_TOO_LOW_INFO:
                break;
            case WARNING:
                break;
            case DANGER_WARNING:

                break;
            case EMERGENCY_CALL:

                if (dizzinessWarning == DizzinessWarnings.HIGH_DIZZINESS) {
                    //Emergency call
                }
                break;
        }
    }
}
