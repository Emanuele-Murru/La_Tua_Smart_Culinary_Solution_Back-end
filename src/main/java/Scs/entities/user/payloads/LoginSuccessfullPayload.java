package Scs.entities.user.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginSuccessfullPayload {
	String accessToken;
}
