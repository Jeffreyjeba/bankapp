package runner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

public class ApiUrlRunner {

	public static void main(String... args) throws IOException {
		try {
			System.setProperty("javax.net.ssl.trustStore","/home/jeffrey-pt7279/selfsigned.jks"); 
			System.setProperty("javax.net.ssl.trustStorePassword","Jeffrey2003." );
			URL url =new URL("https://192.168.10.80:8443/ReyBank/api");
			HttpsURLConnection connection=(HttpsURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("id","1");
			connection.setRequestProperty("key","reyBank");
			connection.setHostnameVerifier(new HostnameVerifier() {
				@Override public boolean verify(String hostname, SSLSession session) {
					if(hostname.equals("localhost")) {
						return true; 
						}
					else if(hostname.equals("192.168.10.80")){
						return true;
						} else{ 
						return false;
						} 
					}
			});

			try(BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));){
				StringBuilder stringBuilder = new StringBuilder();
				reader.lines().forEach(a->stringBuilder.append(a));
				System.out.println(stringBuilder);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
