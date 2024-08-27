package mx.gob.banobras.nombreApp.usuarios.business.infraestructure.adapters.out.client;


import java.io.IOException;
import java.net.Socket;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedTrustManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;


import mx.gob.banobras.nombreApp.usuarios.business.aplication.port.out.IUsuarioPersistenceClient;
import mx.gob.banobras.nombreApp.usuarios.business.dominio.model.Usuario;
import mx.gob.banobras.nombreApp.usuarios.business.infraestructure.adapters.in.dto.HeaderDTO;
import mx.gob.banobras.nombreApp.usuarios.business.infraestructure.adapters.in.dto.UsuarioDTO;

@Component
public class UsuarioPersistenceClientImpl implements IUsuarioPersistenceClient{
	
	
	/**
	 * Variable que contiene la URL de conexion con para la capa de negocio
	 */
	@Value("${app.url.persistence.usuarios}")
	String urlPersistenceUsuarios;

//	@Override
//	public Usuario findById(HeaderDTO headerDTO, UsuarioDTO userInDTO) {
//		HttpHeaders headers = new HttpHeaders();
//
//		ResponseEntity<Usuario> responseEntity = null;
//		RestTemplate restTemplate = new RestTemplate();
//
//		try {
//			headers.setContentType(MediaType.APPLICATION_JSON);
//			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//			headers.set("credentials", headerDTO.getCredentials());
//			headers.set("app-name", headerDTO.getAppName());
//			headers.set("consumer-id", headerDTO.getConsumerId());
//			headers.set("functional-id", headerDTO.getFunctionalId());
//			headers.set("transaction-id", headerDTO.getTransactionId());
//			HttpEntity<String> entity = new HttpEntity<String>(headers);
//			responseEntity = restTemplate.exchange(urlPersistenceUsuarios + userInDTO.getId(), HttpMethod.GET, entity, Usuario.class);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		Usuario user = responseEntity.getBody();
//
//		return user;
//	}

	public Usuario findById(HeaderDTO headerDTO, UsuarioDTO userInDTO) throws NoSuchAlgorithmException, KeyManagementException, IOException, InterruptedException {
		Gson gson = new Gson();
		HttpClient client = null;
		HttpResponse<String> response = null;
		
		System.out.println("INICA PERSISTENCE");
		System.out.println("urlPersistenceUsuarios == " + urlPersistenceUsuarios);
		SSLContext sslContext = null;

		if(urlPersistenceUsuarios.toUpperCase().contains("HTTPS")) {
			sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, new TrustManager[] { MOCK_TRUST_MANAGER }, new SecureRandom());
			client =  HttpClient.newBuilder().sslContext(sslContext).build();
		}else {
			client =  HttpClient.newBuilder().build();	
		}
		HttpRequest request = HttpRequest.newBuilder()
				.setHeader("credentials", headerDTO.getCredentials())
				.setHeader("token-auth", headerDTO.getTokenAuth())
				.setHeader("app-name", headerDTO.getAppName())
				.setHeader("consumer-id", headerDTO.getConsumerId())
				.setHeader("functional-id", headerDTO.getFunctionalId())
				.setHeader("transaction-id", headerDTO.getTransactionId())
				.uri(URI.create(urlPersistenceUsuarios+""+userInDTO.getId()))
				.GET().build();

		response = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println("response: " + response);
		return gson.fromJson(response.body(), Usuario.class);
	}

	private static final TrustManager MOCK_TRUST_MANAGER = new X509ExtendedTrustManager() {
		@Override
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return new java.security.cert.X509Certificate[0];
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		@Override
		public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
		}

		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType, Socket socket)
				throws CertificateException {
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType, Socket socket)
				throws CertificateException {
		}

		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType, SSLEngine engine)
				throws CertificateException {

		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType, SSLEngine engine)
				throws CertificateException {
		}

	};
	
	
	
	

}
	
	


