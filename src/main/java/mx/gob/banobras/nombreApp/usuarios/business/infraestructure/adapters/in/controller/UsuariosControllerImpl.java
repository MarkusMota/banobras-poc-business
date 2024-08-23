package mx.gob.banobras.nombreApp.usuarios.business.infraestructure.adapters.in.controller;


import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import mx.gob.banobras.nombreApp.usuarios.business.aplication.port.in.IUsuarioCasoUsoService;
import mx.gob.banobras.nombreApp.usuarios.business.dominio.model.Usuario;
import mx.gob.banobras.nombreApp.usuarios.business.infraestructure.adapters.in.dto.HeaderDTO;
import mx.gob.banobras.nombreApp.usuarios.business.infraestructure.adapters.in.dto.UsuarioDTO;


@CrossOrigin(originPatterns = {"*"})
@RestController
public class UsuariosControllerImpl implements IUsuariosController{

	/** Trace for the application */
	Logger log = LogManager.getLogger(UsuariosControllerImpl.class);
	
	private final IUsuarioCasoUsoService iUsuarioCasoUsoService;

	public UsuariosControllerImpl(IUsuarioCasoUsoService iUsuarioCasoUsoService) {
		this.iUsuarioCasoUsoService = iUsuarioCasoUsoService;
	}

	@Override
    @GetMapping("/{id}")
    public Usuario buscaPorId(
			@RequestHeader(value = "credentials") String credentials,
			@RequestHeader(value = "token-auth") String tokenAuth,
			@RequestHeader(value = "app-name") String appName,
			@RequestHeader(value = "consumer-id") String consumerId,
			@RequestHeader(value = "functional-id") String functionalId,
			@RequestHeader(value = "transaction-id") String transactionId,
    		@PathVariable Integer id) throws Exception{
    	HeaderDTO headerDTO = new HeaderDTO(credentials, null, null, tokenAuth, appName, consumerId, functionalId, transactionId, null);
    	UsuarioDTO userInDTO = new UsuarioDTO(id, null, null, null, null, null, null, null, null, null, 0);
    	System.out.println("Servicio Negocio Usuarios");
    	log.info("transaction-id: " + transactionId + "busca usuario: " + id  );
        return iUsuarioCasoUsoService.findById(headerDTO, userInDTO );
    }

}
