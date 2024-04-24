package runner;

	import java.net.URL;
	import java.util.Scanner;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

	public class Balaji {
		private static final String GET_USER_URL = "https://localhost:8443/ReyBank/api";
		private static final String AUTH_TOKEN = "reyBank";
		private static final String USER = "2";

		public static void main(String[] args) {
			try {
				System.setProperty("javax.net.ssl.trustStore", "/home/jeffrey-pt7279/selfsigned.jks");
				System.setProperty("javax.net.ssl.trustStorePassword", "Jeffrey2003.");
				
				URL urlObj = new URL(GET_USER_URL);
				HttpsURLConnection connection = (HttpsURLConnection) urlObj.openConnection();

				connection.setRequestMethod("GET");
				connection.setRequestProperty("key", AUTH_TOKEN);
				connection.setRequestProperty("id", USER);
				
				  connection.setHostnameVerifier(new HostnameVerifier() {
				  
				  @Override public boolean verify(String hostname, SSLSession session) { return
				  true; } });
				 
				int responseCode = connection.getResponseCode();
				System.out.println("Response Code :" + responseCode);

				if (responseCode == HttpsURLConnection.HTTP_OK) {  

					StringBuilder sb = new StringBuilder();
					Scanner scanner = new Scanner(connection.getInputStream());
					while (scanner.hasNext()) {
						sb.append(scanner.next());
					}
					System.out.println(sb);

					scanner.close();
				} else if (responseCode == HttpsURLConnection.HTTP_UNAUTHORIZED) {
					System.out.println("Invalid Token");
				} else if (responseCode == HttpsURLConnection.HTTP_NOT_FOUND) {
					System.out.println("No Account Found");
				} else {
					System.out.println("Error: Unexpected response code " + responseCode);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

