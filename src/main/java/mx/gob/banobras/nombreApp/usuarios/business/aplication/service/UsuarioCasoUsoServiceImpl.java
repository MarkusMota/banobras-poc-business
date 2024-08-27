package mx.gob.banobras.nombreApp.usuarios.business.aplication.service;

import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

import mx.gob.banobras.nombreApp.usuarios.business.aplication.port.in.IUsuarioCasoUsoService;
import mx.gob.banobras.nombreApp.usuarios.business.aplication.port.out.ITokenClient;
import mx.gob.banobras.nombreApp.usuarios.business.aplication.port.out.IUsuarioPersistenceClient;
import mx.gob.banobras.nombreApp.usuarios.business.dominio.model.Usuario;
import mx.gob.banobras.nombreApp.usuarios.business.infraestructure.adapters.in.dto.HeaderDTO;
import mx.gob.banobras.nombreApp.usuarios.business.infraestructure.adapters.in.dto.UsuarioDTO;



@Service
public class UsuarioCasoUsoServiceImpl implements IUsuarioCasoUsoService {

	private final IUsuarioPersistenceClient iUsuarioPersistenceClient;
	private final ITokenClient iTokenClient;

	public UsuarioCasoUsoServiceImpl(IUsuarioPersistenceClient iUsuarioPersistenceClient, ITokenClient iTokenClient) {
		this.iUsuarioPersistenceClient = iUsuarioPersistenceClient;
		this.iTokenClient = iTokenClient;
	}

	@Override
	public Usuario findById(HeaderDTO headerDTO, UsuarioDTO userInDTO) throws Exception {
		HttpResponse<String> response = null;
		System.out.println("voy a validar ");
		response = iTokenClient.getTokenAuthorization(headerDTO);

		System.out.println("response.body() " + response.body());
		if (response.statusCode() == 200) {
			System.out.println("token correcto");
			System.out.println("invoca persistence");
			try {
				return this.iUsuarioPersistenceClient.findById(headerDTO, userInDTO);
			}catch(Exception e) {
				e.printStackTrace();
				throw new IllegalArgumentException("****", e);
			}
		} else {
			return null;
		}

	}

}
