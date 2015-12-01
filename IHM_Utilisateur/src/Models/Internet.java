package Models;

import javafx.scene.control.Alert;

import java.net.URL;
import java.net.URLConnection;

public class Internet {
    public static boolean isConnected = false;

    /* Test the internet connectivity */
    public static void TestConnectivity() {
        try {
            final URL url = new URL("http://www.google.com");
            final URLConnection connection = url.openConnection();
            connection.setConnectTimeout(1000);
            connection.connect();
            Internet.isConnected = true;
        } catch (Exception e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur de connexion internet");
            alert.setHeaderText(null);
            alert.setContentText("Aucune connexion internet détéctée.");
            alert.showAndWait();
            Internet.isConnected = false;
        }
    }
}
