package medical_drive.bmw_digital_companion.bmw_rest_services;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Nicolas on 18/11/2017.
 */

public class BmwRestServicesTest {

    private BmwRestCommands bmwRestCommands;

    @Test
    public void servicesAreGotRight() {

    }

    @Test
    public void doFlashlights() {
        bmwRestCommands = new BmwRestCommandsImpl();
        bmwRestCommands.vehiclesIdServicesServiceTypeGet(RestServiceConstants.BMW_I5_ID, ServiceTypes.LIGHT_FLASH);
    }


}
