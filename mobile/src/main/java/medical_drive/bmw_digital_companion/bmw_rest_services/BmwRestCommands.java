package medical_drive.bmw_digital_companion.bmw_rest_services;

/**
 * Created by Nicolas on 18/11/2017.
 */

public interface BmwRestCommands {

    public void rootGet();

    public void rootOptions();

    public void schemaSwaggerJsonGet();

    public void schemaSwaggerJsonOptions();

    public void vehiclesGet();

    public void vehiclesOptions();

    public void vehiclesIdGet(String id);

    public void vehiclesIdOptions(String id);

    public void vehiclesIdDestinationsGet(String id);

    public void vehiclesIdDestinationsOptions(String id);

    public void vehiclesIdPoiPost(String id);

    public void vehiclesIdPoiOptions(String id);

    public void vehiclesIdServicesGet(String id);

    public void vehiclesIdServicesOptions(String id);

    public void vehiclesIdServicesServiceTypeGet(String id, String serviceType);

    public void vehiclesIdServicesServiceTypeOptions(String id, String serviceType);

    public void vehiclesIdServicesServiceTypeStatusGet(String id, String serviceType);

    public void vehiclesIdServicesServiceTypeStatusOptions(String id, String serviceType);
}
