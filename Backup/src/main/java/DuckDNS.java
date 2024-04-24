import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DuckDNS {

    private String duckDnsToken = "732fcb36-116b-470d-bb46-5addb4679007";
    public String domain = "muscleforge-backup.duckdns.org";

    public DuckDNS(){}

    public void updateDomain(){

        try {
            // Construye la URL de actualización de DuckDNS
            String updateUrl = "https://www.duckdns.org/update?domains=" + domain + "&token=" + duckDnsToken + "&ip=";
            URL url = new URL(updateUrl);

            // Abre una conexión HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Obtiene la respuesta
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Imprime la respuesta del servidor DuckDNS
            System.out.println("Respuesta de DuckDNS: " + response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

