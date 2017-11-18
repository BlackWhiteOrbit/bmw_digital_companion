package medical_drive.bmw_digital_companion.bmw_rest_services;

import java.io.OutputStream;

/**
 * Created by Nicolas on 18/11/2017.
 */

public class OurBmwCommandsImpl {

    private BmwRestCommands bmwRestCommands;

    public OurBmwCommandsImpl() {
        bmwRestCommands = new BmwRestCommandsImpl();
    }

    public void doFlashLights() {
        bmwRestCommands.vehiclesIdServicesServiceTypeGet(RestServiceConstants.BMW_I5_ID, ServiceTypes.LIGHT_FLASH);
    }

    public void hornTheHonk() {
        bmwRestCommands.vehiclesIdServicesServiceTypeGet(RestServiceConstants.BMW_I5_ID, ServiceTypes.LIGHT_FLASH);
    }
}
