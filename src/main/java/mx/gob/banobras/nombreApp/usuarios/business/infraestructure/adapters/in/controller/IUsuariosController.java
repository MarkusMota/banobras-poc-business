package mx.gob.banobras.nombreApp.usuarios.business.infraestructure.adapters.in.controller;


import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.gob.banobras.nombreApp.usuarios.business.dominio.model.Usuario;

@Tag(name = "UsuariosController", description = "Servicios de la funcionalidad de administraci&oacute;n de usuarios.")
@RequestMapping("/usuarios/v1")
public interface IUsuariosController {


	/**
	 * Method to authenticated the user through LDAP
	 * 
	 * @param credentials    - Credeniales e usurio encriptadas.
	 * @param app-name       - Nombre del aplicativo que consume el servicio
	 * @param consumer-id    - Interfaz o capa que consume el servicio.
	 * @param functional-id  - Funcionlidad negocio.
	 * @param transaction-id - Identificador Unico de la transacci&oacute;n, generado por UUID.
	 * 
	 * @return regresa el objeto Usuario con los datos del usuaio.
	 * @throws manda una excepci&oacute;n si ocurre un error.
	 * 
	 */
	@Operation(summary = "Consulta un usuario por ID.", description = "Servicio que realiza la consulta por ID.")
	@Parameter(name = "credentials", required = true, description = "Credenciales de usuario encriptadas.", example = "0FFA7868B0A8CE36ED6C98230E7AC933")
	@Parameter(name = "token-auth", required = true, description = "Token de autenticaci&oacute;n", example = "Bearer eyJ0eXAiOiJKV1Qi...")
	@Parameter(name = "app-name", required = true, description = "Nombre del aplicativo que consume el servicio.", example = "SICOVI")
	@Parameter(name = "consumer-id", required = true, description = "Interfaz o capa que consume el servicio.", example = "UI SICOVI")
	@Parameter(name = "functional-id", required = true, description = "Funcionlidad negocio.", example = "Login user")
	@Parameter(name = "transaction-id", required = true, description = "Identificador Unico de la transacci&oacute;n, generado por UUID.", example = "9680e51f-4766-4124-a3ff-02e9c3a5f9d6")

	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "400", description = "Error en la petici&oacute;n.", content = @Content)
	@ApiResponse(responseCode = "404", description = "No esta disponible el recurso.", content = @Content)
	@ApiResponse(responseCode = "500", description = "Error interno.", content = @Content)
	@ApiResponse(responseCode = "503", description = "Service no disponible.", content = @Content)
	
	
    @GetMapping("/{id}")
    public Usuario buscaPorId(
			@RequestHeader(value = "credentials") String credentials,
			@RequestHeader(value = "token-auth") String tokenAuth,
			@RequestHeader(value = "app-name") String appName,
			@RequestHeader(value = "consumer-id") String consumerId,
			@RequestHeader(value = "functional-id") String functionalId,
			@RequestHeader(value = "transaction-id") String transactionId,
    		@PathVariable Integer id) throws Exception;
 
}
