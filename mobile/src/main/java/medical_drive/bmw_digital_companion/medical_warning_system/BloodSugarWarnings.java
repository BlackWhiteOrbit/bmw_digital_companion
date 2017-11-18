package medical_drive.bmw_digital_companion.medical_warning_system;

/**
 * Created by Nicolas on 18/11/2017.
 */

public enum BloodSugarWarnings implements Warning{
    EMERGENCY_CALL("You're are in danger! The emergency is called!"),
    DANGER_WARNING("You are in danger! Please eat something!"),
    WARNING("You're blood sugar level is too low! Please eat something"),
    BLOOD_SUGAR_TOO_LOW_INFO("Your blood sugar level is going too be too low!"),
    NORMAL_BLOOD_SUGAR("Your blood sugar level is in a normal state!"),
    BLOOD_SUGAR_TOO_HIGH_INFO("Your blood sugar level is too high!");

    private String message;

    private BloodSugarWarnings(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
