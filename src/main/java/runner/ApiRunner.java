package runner;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class ApiRunner {
	
	public static void main(String[] args) throws IOException, InterruptedException, KeyStoreException, NoSuchAlgorithmException, CertificateException, KeyManagementException, URISyntaxException {
		
		System.setProperty("javax.net.ssl.trustStore","/home/jeffrey-pt7279/selfsigned.jks"); 
		System.setProperty("javax.net.ssl.trustStorePassword","Jeffrey2003." );
		HttpRequest request=HttpRequest.newBuilder()
				.GET()
				.uri(new URI("https://localhost:8443/ReyBank/api"))
				.header("id","1")
				.header("key","reyBank")
				.build();
		System.out.println(request);
		
		HttpClient client=HttpClient.newBuilder().build();
		
		HttpResponse<String> response= client.send(request,HttpResponse.BodyHandlers.ofString());
		System.out.println(response.statusCode());
		System.out.println(response.body());

	}
}
	
	

