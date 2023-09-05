package Scs.entities.user;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import Scs.entities.user.payloads.UserRequestPayload;



@RestController
@RequestMapping("/utenti")
public class UserController {

	@Autowired
	UserService userSrv;

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public User saveUser(@RequestBody UserRequestPayload body) {
		User createdUser = userSrv.save(body);
		return createdUser;
	}

	@GetMapping("")
	public List<User> getUsers() {
		return userSrv.getUsers();
	}

	@GetMapping("/{userId}")
	public User findById(@PathVariable UUID userId) {
		return userSrv.findById(userId);
	}

	@PutMapping("/{userId}")
	public User updateUser(@PathVariable UUID userId, @RequestBody UserRequestPayload body) {
		return userSrv.findByIdAndUpdate(userId, body);
	}

	@DeleteMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable UUID userId) {
		userSrv.findByIdAndDelete(userId);
	}
}