package mx.gob.banobras.nombreApp.usuarios.business.aplication.port.out;


import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import mx.gob.banobras.nombreApp.usuarios.business.dominio.model.Usuario;
import mx.gob.banobras.nombreApp.usuarios.business.infraestructure.adapters.in.dto.HeaderDTO;
import mx.gob.banobras.nombreApp.usuarios.business.infraestructure.adapters.in.dto.UsuarioDTO;


public interface IUsuarioPersistenceClient  {

	Usuario findById(HeaderDTO headerDTO, UsuarioDTO userInDTO) throws 
	NoSuchAlgorithmException, KeyManagementException, IOException, InterruptedException ;
	
}
