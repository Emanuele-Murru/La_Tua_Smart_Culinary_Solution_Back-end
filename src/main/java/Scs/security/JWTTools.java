package Scs.security;


import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import Scs.Exceptions.UnauthorizedException;
import Scs.entities.user.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
@Component
public class JWTTools {

	@Value("${spring.jwt.secret}")
	private String secret;

	public String createToken(User u) {
		String token = Jwts.builder()
				.setSubject(u.getId().toString())
				.claim("role", u.getRole())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
				.signWith(Keys.hmacShaKeyFor(secret.getBytes()))
				.compact();
		return token;
	}

	public void verifyToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new UnauthorizedException("Token is invalid! Please login up again");
		}
	}

	public String extractSubject(String token) {
		return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parseClaimsJws(token)
				.getBody().getSubject();
	}
}
