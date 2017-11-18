package medical_drive.bmw_digital_companion.bmw_rest_services;

/**
 * Created by Nicolas on 18/11/2017.
 */

public enum ServiceTypes {

    LIGHT_FLASH("light_flash"), CLIMATE_NOW("climate_now");

    private String htmlRestServiceType;

    private ServiceTypes(String htmlRestServiceType) {
        this.htmlRestServiceType = htmlRestServiceType;
    }

    public String getHtmlRestServiceType() {
        return htmlRestServiceType;
    }

}
