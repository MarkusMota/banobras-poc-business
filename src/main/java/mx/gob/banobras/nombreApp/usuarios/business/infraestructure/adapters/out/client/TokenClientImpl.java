package mx.gob.banobras.nombreApp.usuarios.business.infraestructure.adapters.out.client;


import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedTrustManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import mx.gob.banobras.nombreApp.usuarios.business.aplication.port.out.ITokenClient;
import mx.gob.banobras.nombreApp.usuarios.business.infraestructure.adapters.in.dto.HeaderDTO;

import java.io.*;

import java.net.Socket;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class TokenClientImpl implements ITokenClient {
	
	/** Trazas de la aplicación */
	Logger log = LogManager.getLogger(TokenClientImpl.class);

	/** Variable que contiene el nombre de usuario de conexion en ldap */
	@Value("${app.url.token.valid}")
	String urlTokenValid;

	/**
	 * Metodo para validar el token.
	 * 
	 * @param HeadetDTO componente que contiene los datos del token.
	 * 
	 * @return HttpResponse<String> regresa un objeto con los datos del token
	 *         validado
	 * @throws IOException,             InterruptedException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * 
	 */
	@Override
	public HttpResponse<String> getTokenAuthorization(HeaderDTO headerDTO)
			throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {

		HttpClient client = null;
		HttpResponse<String> response = null;
		log.info("Inicia cliente validar token");

		SSLContext sslContext = null;

		if(urlTokenValid.toUpperCase().contains("HTTPS")) {
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
				.setHeader("transaction-id", headerDTO.getTransactionId()).
				uri(URI.create(urlTokenValid))
				.POST(HttpRequest.BodyPublishers.noBody()).build();

		response = client.send(request, HttpResponse.BodyHandlers.ofString());
		log.info(new StringBuilder().append("StatusCode: ").append(response.statusCode()));
		log.info("Finaliza cliente validar token");
		return response;
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
