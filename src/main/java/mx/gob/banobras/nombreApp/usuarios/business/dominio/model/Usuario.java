package mx.gob.banobras.nombreApp.usuarios.business.dominio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
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
