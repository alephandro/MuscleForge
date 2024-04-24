package com.example.gym.utils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkVariables {

	public static final int BackupPort = 8888;
	private static final String DuckDnsToken = "732fcb36-116b-470d-bb46-5addb4679007";
	private static final String ServerDomain = "muscleforge-server.duckdns.org";
	private static final String BackupDomain = "muscleforge-backup.duckdns.org";
	public static String BackupIP = getIPFromDNS(ServerDomain);


	public static void updateDomain(){
		try {
			// Construye la URL de actualización de utils.DuckDNS
			String updateUrl = "https://www.duckdns.org/update?domains=" + ServerDomain + "&token=" + DuckDnsToken + "&ip=";
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
			BackupIP = getIPFromDNS(BackupDomain);

			// Imprime la respuesta del servidor utils.DuckDNS
			System.out.println("Respuesta de DuckDNS: " + response.toString());
			System.out.println(BackupIP);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getIPFromDNS(String url) {
		try {
			InetAddress address = InetAddress.getByName(url);
			return address.getHostAddress();
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
	}
}

