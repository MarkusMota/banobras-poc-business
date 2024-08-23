package mx.gob.banobras.nombreApp.usuarios.business.infraestructure.adapters.in.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
	private Integer id;
    private String direccion;
    private String celular;
    private String email;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombre;
    private String contrasena;
    private String tipoUsuario;
    private String usuario;
    private Integer activo;

}