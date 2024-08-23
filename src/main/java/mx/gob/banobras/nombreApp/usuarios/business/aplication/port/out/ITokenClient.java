package mx.gob.banobras.nombreApp.usuarios.business.aplication.port.out;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.naming.NamingException;

import mx.gob.banobras.nombreApp.usuarios.business.infraestructure.adapters.in.dto.HeaderDTO;

public interface ITokenClient {
	
	
	/**
	 * Metodo para validar el token.
	 * 
	 * @param securityAuthDTO componente que conciten los datos del token.
	 * @return HttpResponse<String> objeto que contiene los datos de validacion del token. 
	 * 
	 * @throws NamingException Excepción durante el proces.
	 */
	public HttpResponse<String> getTokenAuthorization(HeaderDTO headerDTO) throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException;
	
	
}
