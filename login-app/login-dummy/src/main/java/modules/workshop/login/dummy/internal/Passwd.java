package modules.workshop.login.dummy.internal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Passwd {

	private List<User> users;

	public Passwd() {
		try {
			this.users = load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private List<User> load() throws IOException {
		InputStream resource = getClass().getResourceAsStream("/passwd");
		return new BufferedReader(new InputStreamReader(resource, StandardCharsets.UTF_8))
				.lines()
				.map(line->line.split(","))
				.map(line->new User(line[0],line[1]))
				.collect(Collectors.toList());				
	}

	public List<User> getUsers() {
		return users;
	}

	public User findUser(String username) {
		Optional<User> user = users.stream().filter(u -> u.getUsername().equals(username)).findAny();
		return user.isPresent() ? user.get() : null;
	}

}
