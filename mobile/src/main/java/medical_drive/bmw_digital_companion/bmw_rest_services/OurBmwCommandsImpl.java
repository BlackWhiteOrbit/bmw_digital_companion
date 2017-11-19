package medical_drive.bmw_digital_companion.bmw_rest_services;

import java.io.OutputStream;
import java.util.Timer;

/**
 * Created by Nicolas on 18/11/2017.
 */

public class OurBmwCommandsImpl {

    public static final int WAITING_TIME_BETWEEN_WARNING_SIGNALS_IN_MILLIS = 10000;

    private BmwRestCommands bmwRestCommands;
    private Thread warningTimer;
    private boolean warningIsRunning;

    public OurBmwCommandsImpl() {
        bmwRestCommands = new BmwRestCommandsImpl();
        initWarningTimer();
    }

    private void initWarningTimer() {
        warningTimer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (warningIsRunning) {
                    try {
                        Thread.sleep(WAITING_TIME_BETWEEN_WARNING_SIGNALS_IN_MILLIS);
                        doFlashLights();
                        hornTheHonk();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void startWarningSystem() {
        warningIsRunning = true;
        warningTimer.start();
    }

    public void stopWarningSystem() {
        warningIsRunning = false;
    }

    public void doFlashLights() {
        bmwRestCommands.vehiclesIdServicesServiceTypeGet(RestServiceConstants.BMW_I5_ID, ServiceTypes.LIGHT_FLASH);
    }

    public void hornTheHonk() {
        bmwRestCommands.vehiclesIdServicesServiceTypeGet(RestServiceConstants.BMW_I5_ID, ServiceTypes.LIGHT_FLASH);
    }
}
