package medical_drive.bmw_digital_companion.bmw_rest_services;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Nicolas on 18/11/2017.
 */

public class OurBmwCommandsImplTest {

    private OurBmwCommandsImpl ourBmwCommands;

    @Test
    public void warningSystem() {
        ourBmwCommands = new OurBmwCommandsImpl();
        ourBmwCommands.startWarningSystem();

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ourBmwCommands.stopWarningSystem();
    }

    @Test
    public void doFlashlights() {
        ourBmwCommands = new OurBmwCommandsImpl();
        ourBmwCommands.doFlashLights();
    }

    @Test
    public void hornTheHonk() {
        ourBmwCommands = new OurBmwCommandsImpl();
        ourBmwCommands.hornTheHonk();
    }
}
