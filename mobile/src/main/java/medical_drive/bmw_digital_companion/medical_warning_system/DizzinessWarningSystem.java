package medical_drive.bmw_digital_companion.medical_warning_system;

/**
 * Created by Nicolas on 18/11/2017.
 */

public class DizzinessWarningSystem {

    private DizzinessData dizzinessData;

    public DizzinessWarningSystem() {
        //dizzinessData = new DizzinessData();
    }

    public DizzinessWarnings giveWarnings() {
        //double dizzinessLevel = dizzinessData.getLevelBetweenZeroAndOne();

        //TODO change
        double dizzinessLevel = 0.6;

        if (dizzinessLevel > 0.8 && dizzinessLevel <= 1) return DizzinessWarnings.HIGH_DIZZINESS;
        else if (dizzinessLevel > 0.5 && dizzinessLevel <= 0.8) return DizzinessWarnings.DIZZY;
        else if (dizzinessLevel >= 0 && dizzinessLevel <= 0.5) return DizzinessWarnings.NOT_DIZZY;
        else throw new IllegalArgumentException("Dizziness must be between 0 and 1!");
    }
}
