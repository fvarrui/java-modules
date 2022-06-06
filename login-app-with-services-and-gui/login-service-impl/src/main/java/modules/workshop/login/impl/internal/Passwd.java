package modules.workshop.login.impl.internal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Users database
 * @author fvarrui
 */
public class Passwd {

	private List<User> users;

	public Passwd() {
		try {
			this.users = load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Load all user data in "passwd" resource file 
	 * @return List of users in "passwd" file
	 * @throws IOException
	 */
	private List<User> load() throws IOException {
		InputStream resource = getClass().getResourceAsStream("/passwd");
		return new BufferedReader(new InputStreamReader(resource, StandardCharsets.UTF_8))
				.lines()
				.filter(line -> !line.startsWith("#")) // ignore comments in passwd file
				.map(line-> line.split(","))
				.map(line-> new User(line[0], line[1]))
				.collect(Collectors.toList());				
	}

	/**
	 * Get all users
	 * @return All users list
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * Check if a user exists 
	 * @param username Username to find
	 * @return User or null if not exist
	 */
	public User findUser(String username) {
		Optional<User> user = users.stream().filter(u -> u.getUsername().equals(username)).findAny();
		return user.isPresent() ? user.get() : null;
	}

}
