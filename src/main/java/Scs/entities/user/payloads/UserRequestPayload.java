package Scs.entities.user.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRequestPayload {
	private String name;
	private String surname;
	private String username;
	private String email;
	private String password;
}
