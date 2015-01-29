package jaci.openrio.toast.core.loader;

import edu.wpi.first.wpilibj.RobotBase;
import jaci.openrio.toast.lib.crash.CrashHandler;
import jaci.openrio.toast.lib.listener.Robot;
import jaci.openrio.toast.lib.log.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.Manifest;

public class RobotLoader {

    static Robot robot;
    static Logger log;

    public static void init() {
        log = new Logger("RobotLoader", Logger.ATTR_DEFAULT);

        String roboClass = "";
        Enumeration<URL> resources = null;
        try {
            resources = RobotBase.class.getClassLoader().getResources("META-INF/MANIFEST.MF");
            while (resources.hasMoreElements()) {
                Manifest manifest = new Manifest(resources.nextElement().openStream());
                roboClass = manifest.getMainAttributes().getValue("Toast-Robot");
            }
        } catch (IOException e) {
            log.error("Could not load Robot Manifest");
            log.exception(e);

            CrashHandler.handle(e);
        }

        try {
            robot = (Robot) Class.forName(roboClass).newInstance();
        } catch (Throwable t) {
            log.error("Could not load Robot Class");
            log.exception(t);

            CrashHandler.handle(t);
        }
    }

}
