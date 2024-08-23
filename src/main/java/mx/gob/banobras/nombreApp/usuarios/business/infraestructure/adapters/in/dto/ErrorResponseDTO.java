package mx.gob.banobras.nombreApp.usuarios.business.infraestructure.adapters.in.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDTO {
	private Integer status;
    private String error;
    private String message;
}
