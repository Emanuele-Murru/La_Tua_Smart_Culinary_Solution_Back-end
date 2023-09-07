package Scs.entities.user;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import Scs.Exceptions.BadRequestException;
import Scs.Exceptions.NotFoundException;
import Scs.entities.user.payloads.UserRequestPayload;


@Service
public class UserService {

	@Autowired
	UserRepository userRepo;

	public User save(UserRequestPayload body) {
		
		userRepo.findByEmail(body.getEmail()).ifPresent(user -> {
			throw new BadRequestException("The email: " + body.getEmail() + " has been already used!", HttpStatus.BAD_REQUEST);
		});
		
		User newUtente = new User(body.getName(), body.getSurname(), body.getUsername(), body.getEmail(),body.getPassword(), Role.USER);
		
		return userRepo.save(newUtente);
	}

	public List<User> getUsers() {
		return userRepo.findAll();
	}
	
	public Page<User> findAll(int page, String sort) {
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sort));
		return userRepo.findAll(pageable);
	}

	public User findById(UUID id) throws NotFoundException {
		return userRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	public User findByIdAndUpdate(UUID id, UserRequestPayload body) throws NotFoundException {
		User found = this.findById(id);
		found.setName(body.getName());
		found.setSurname(body.getSurname());
		found.setEmail(body.getEmail());
		return userRepo.save(found);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		User found = this.findById(id);
		userRepo.delete(found);
	}

	public User findByEmail(String email) {
		return userRepo.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("We can not find any user with the email: " + email));
	}
}