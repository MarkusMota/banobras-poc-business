package mx.gob.banobras.nombreApp.usuarios.business.infraestructure.adapters.in.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeaderDTO {
	private String credentials;
	private String userName;
	private String password;
	private String tokenAuth;
	private String appName;
	private String consumerId;
	private String functionalId;
	private String transactionId;
	private String timeRefreshToken;

}
