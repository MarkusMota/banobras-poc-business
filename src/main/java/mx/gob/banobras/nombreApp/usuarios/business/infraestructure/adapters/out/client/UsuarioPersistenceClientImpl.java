package mx.gob.banobras.nombreApp.usuarios.business.infraestructure.adapters.out.client;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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

	@Override
	public Usuario findById(HeaderDTO headerDTO, UsuarioDTO userInDTO) {
		HttpHeaders headers = new HttpHeaders();

		ResponseEntity<Usuario> responseEntity = null;
		RestTemplate restTemplate = new RestTemplate();

		try {
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("credentials", headerDTO.getCredentials());
			headers.set("app-name", headerDTO.getAppName());
			headers.set("consumer-id", headerDTO.getConsumerId());
			headers.set("functional-id", headerDTO.getFunctionalId());
			headers.set("transaction-id", headerDTO.getTransactionId());
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			responseEntity = restTemplate.exchange(urlPersistenceUsuarios + userInDTO.getId(), HttpMethod.GET, entity, Usuario.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Usuario user = responseEntity.getBody();

		return user;
	}


}
	
	


