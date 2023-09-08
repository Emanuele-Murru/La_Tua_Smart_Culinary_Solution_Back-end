package Scs.entities.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import Scs.entities.recipe.Recipe;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({ "password" })
public class User implements UserDetails {

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

	@OneToMany(mappedBy = "user")
	private List<Recipe> recipes = new ArrayList<>();

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
		if (this.role != null) {
			return List.of(new SimpleGrantedAuthority(role.name()));
		}

		return Collections.emptyList();
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
