package Scs.entities.user;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import Scs.entities.Recipes;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"password"})
public class User implements UserDetails{
	
	@Id
	@GeneratedValue
	private UUID id;
	
	private String name;
	private String surname;
	private String username;
	private String email;
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	private List<Recipes> recipesLiked;
	
	public User(String _name, String _surname, String _username, String _email, String _password, Role _role) {
		
		this.name = _name;
		this.surname = _surname;
		this.username = _username;
		this.email = _email;
		this.password = _password;	
		this.role = Role.USER;
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}
	
	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
	
}
