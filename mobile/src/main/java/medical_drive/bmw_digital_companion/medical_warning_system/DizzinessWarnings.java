package medical_drive.bmw_digital_companion.medical_warning_system;

/**
 * Created by Nicolas on 18/11/2017.
 */

public enum DizzinessWarnings implements Warning{
    NOT_DIZZY(""), DIZZY("Do you feel dizzy? Maybe you should take a short break."), HIGH_DIZZINESS("I think you are too dizzy to drive! You should take a break!");

    private String message;

    private DizzinessWarnings(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
