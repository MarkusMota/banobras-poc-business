package mx.gob.banobras.nombreApp.usuarios.business.aplication.port.in;







import mx.gob.banobras.nombreApp.usuarios.business.dominio.model.Usuario;
import mx.gob.banobras.nombreApp.usuarios.business.infraestructure.adapters.in.dto.HeaderDTO;
import mx.gob.banobras.nombreApp.usuarios.business.infraestructure.adapters.in.dto.UsuarioDTO;


public interface IUsuarioCasoUsoService {

	Usuario findById(HeaderDTO headerDTO, UsuarioDTO userInDTO) throws Exception;
	

}
