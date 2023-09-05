package Scs.entities.user;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import Scs.Exceptions.BadRequestException;
import Scs.Exceptions.NotFoundException;
import Scs.entities.user.payloads.UserRequestPayload;


@Service
public class UserService {

	@Autowired
	UserRepository userRepo;

	// SALVA NUOVO UTENTE + ECCEZIONE SE VIENE USATA LA STESSA EMAIL
	public User save(UserRequestPayload body) {
		
		userRepo.findByEmail(body.getEmail()).ifPresent(user -> {
			throw new BadRequestException("The email: " + body.getEmail() + " has been already used!", HttpStatus.BAD_REQUEST);
		});
		
		User newUtente = new User(body.getName(), body.getSurname(), body.getUsername(), body.getEmail(),body.getPassword(), Role.USER);
		
		return userRepo.save(newUtente);
	}

	// TORNA LA LISTA DEGLI UTENTI
	public List<User> getUsers() {
		return userRepo.findAll();
	}

	// CERCA UTENTE TRAMITE ID
	public User findById(UUID id) throws NotFoundException {
		return userRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	// CERCA E MODIFICA UTENTE TRAMITE ID
	public User findByIdAndUpdate(UUID id, UserRequestPayload body) throws NotFoundException {
		User found = this.findById(id);
		found.setName(body.getName());
		found.setSurname(body.getSurname());
		found.setEmail(body.getEmail());
		return userRepo.save(found);
	}

	// CERCA E CANCELLA UTENTE TRAMITE ID
	public void findByIdAndDelete(UUID id) throws NotFoundException {
		User found = this.findById(id);
		userRepo.delete(found);
	}

	public User findByEmail(String email) {
		return userRepo.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato"));
	}
}