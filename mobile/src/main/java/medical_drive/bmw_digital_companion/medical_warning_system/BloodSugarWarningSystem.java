package medical_drive.bmw_digital_companion.medical_warning_system;

/**
 * Created by Nicolas on 18/11/2017.
 */

public class BloodSugarWarningSystem {

    private BloodSugarData bloodSugarData;

    public BloodSugarWarningSystem() {
        bloodSugarData = new BloodSugarData();
    }

    public BloodSugarWarnings giveWarnings(int bloodSugarLevel) {
        //int bloodSugarLevel = bloodSugarData.getLevelInMGPerLitre();

        if(bloodSugarLevel < 50) return BloodSugarWarnings.EMERGENCY_CALL;
        else if (bloodSugarLevel < 60) return BloodSugarWarnings.DANGER_WARNING;
        else if (bloodSugarLevel < 70) return BloodSugarWarnings.WARNING;
        else if (bloodSugarLevel < 75) return BloodSugarWarnings.BLOOD_SUGAR_TOO_LOW_INFO;
        else if (bloodSugarLevel >= 200) return BloodSugarWarnings.BLOOD_SUGAR_TOO_HIGH_INFO;
        else return BloodSugarWarnings.NORMAL_BLOOD_SUGAR;
    }
}
