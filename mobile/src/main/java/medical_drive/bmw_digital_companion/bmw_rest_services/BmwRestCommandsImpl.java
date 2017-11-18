package medical_drive.bmw_digital_companion.bmw_rest_services;

import java.net.MalformedURLException;

/**
 * Created by Nicolas on 18/11/2017.
 */

public class BmwRestCommandsImpl implements BmwRestCommands {

    RestCommunicator restCommunicator = new RestCommunicator();

    @Override
    public void rootGet() {
        doCommunicate("/", RestMethod.GET);
    }

    @Override
    public void rootOptions() {
        doCommunicate("/", RestMethod.OPTIONS);
    }

    @Override
    public void schemaSwaggerJsonGet() {
        doCommunicate("/schema/swagger.json", RestMethod.GET);
    }

    @Override
    public void schemaSwaggerJsonOptions() {
        doCommunicate("/schema/swagger.json", RestMethod.OPTIONS);
    }

    @Override
    public void vehiclesGet() {
        doCommunicate("/vehicles", RestMethod.GET);
    }

    @Override
    public void vehiclesOptions() {
        doCommunicate("/vehicles", RestMethod.OPTIONS);
    }

    @Override
    public void vehiclesIdGet(String id) {
        String path = "/vehicles/" + id;
        doCommunicate(path,RestMethod.GET);
    }

    @Override
    public void vehiclesIdOptions(String id) {
        String path = "/vehicles/" + id;
        doCommunicate(path,RestMethod.OPTIONS);
    }

    @Override
    public void vehiclesIdDestinationsGet(String id) {
        String path = "/vehicles/" + id + "/destinations";
        doCommunicate(path,RestMethod.GET);
    }

    @Override
    public void vehiclesIdDestinationsOptions(String id) {
        String path = "/vehicles/" + id + "/destinations";
        doCommunicate(path,RestMethod.OPTIONS);
    }

    @Override
    public void vehiclesIdPoiPost(String id) {
        String path = "/vehicles" + id + "/poi";
        doCommunicate(path,RestMethod.POST);
    }

    @Override
    public void vehiclesIdPoiOptions(String id) {
        String path = "/vehicles/" + id + "/poi";
        doCommunicate(path,RestMethod.OPTIONS);
    }

    @Override
    public void vehiclesIdServicesGet(String id) {
        String path = "/vehicles/" + id + "/services";
        doCommunicate(path,RestMethod.GET);
    }

    @Override
    public void vehiclesIdServicesOptions(String id) {
        String path = "/vehicles/" + id + "/services";
        doCommunicate(path,RestMethod.OPTIONS);
    }

    @Override
    public void vehiclesIdServicesServiceTypeGet(String id, ServiceTypes serviceType) {
        String path = "/vehicles/" + id + "/services/" + serviceType.getHtmlRestServiceType();
        doCommunicate(path,RestMethod.GET);
    }

    @Override
    public void vehiclesIdServicesServiceTypeOptions(String id, ServiceTypes serviceType) {
        String path = "/vehicles/" + id + "/services/" + serviceType.getHtmlRestServiceType();
        doCommunicate(path,RestMethod.OPTIONS);
    }

    @Override
    public void vehiclesIdServicesServiceTypeStatusGet(String id, ServiceTypes serviceType) {
        String path = "/vehicles/" + id + "/services/" + serviceType.getHtmlRestServiceType() + "/status";
        doCommunicate(path,RestMethod.GET);
    }

    @Override
    public void vehiclesIdServicesServiceTypeStatusOptions(String id, ServiceTypes serviceType) {
        String path = "/vehicles/" + id + "/services/" + serviceType.getHtmlRestServiceType() + "/status";
        doCommunicate(path,RestMethod.OPTIONS);
    }

    private void doCommunicate(String pathAppendix, RestMethod method) {
        String path = RestServiceConstants.PATH_ENDPOINT + pathAppendix;
        try {
            restCommunicator.doCommunicate(path, method);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
