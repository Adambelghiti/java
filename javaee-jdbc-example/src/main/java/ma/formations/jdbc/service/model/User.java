package ma.formations.jdbc.service.model;

import org.mindrot.jbcrypt.BCrypt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private Long id;
	private String username;
	private String password;
	/*public void setPassword(String password) {
		// Hash the password before storing it
		String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
		this.password = hashedPassword;
	}

	public boolean verifyPassword(String password) {
		// Verify if the provided password matches the stored hash
		return BCrypt.checkpw(password, this.password);
	}*/
}
