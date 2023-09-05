package Scs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import Exceptions.UnauthorizedException;
import Scs.entities.user.User;
import Scs.entities.user.UserRepository;
import Scs.entities.user.UserService;
import Scs.entities.user.payloads.LoginSuccessfullPayload;
import Scs.entities.user.payloads.UserLoginPayload;
import Scs.entities.user.payloads.UserRequestPayload;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	UserService userSrv;

	@Autowired
	UserRepository userRepo;

	@Autowired
	JWTTools jwtTools;

	@Autowired
	PasswordEncoder bcrypt;

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public User saveUser(@RequestBody UserRequestPayload body) {

		body.setPassword(bcrypt.encode(body.getPassword()));
		User created = userSrv.save(body);
		return created;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserLoginPayload body) {
	    User user = userSrv.findByEmail(body.getEmail());

	    if (user == null) {
	    	
	        // L'utente con l'email fornita non è stato trovato
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	            .body("Invalid email!");
	    }

	    if (bcrypt.matches(body.getPassword(), user.getPassword())) {
	        String token = jwtTools.createToken(user);
	        System.out.println("Token: " + token);
	        return ResponseEntity.ok(new LoginSuccessfullPayload(token));
	    } else {
	        // Password non corrisponde
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	            .body("Invalid password!");
	    }
	}


	@PostMapping("/logout")
	public ResponseEntity<String> logout() {
		System.out.println("You have been logout successfully!");
		return ResponseEntity.ok("You have been logout successfully!");

	}
}