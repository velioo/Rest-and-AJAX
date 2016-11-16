package bg.elsys.ip.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {
	private static UserService instance;

	public static UserService getInstance() {
		if (instance == null) {
			instance = new UserService();
		}
		return instance;
	}

	private List<User> userList = new ArrayList<>();

	public UserService() {
		userList.add(new User(1, "George"));
		userList.add(new User(2, "John"));
		userList.add(new User(3, "Ivan"));
		userList.add(new User(1, "George"));
		userList.add(new User(2, "John"));
		userList.add(new User(3, "Ivan"));

	}

	public List<User> getUsers() {
		return Collections.unmodifiableList(userList);
	}

	public void addUser(User user) {
		userList.add(user);
	}
	
	public PagedResponse getUsersInPagesFiltered(int page, int perPage, String withName) {
		long previousEntries = page * perPage;
		List<User> pageOfUsrs = userList.stream()
							.filter((u) -> u.getName().equals(withName) || withName == null)
							.skip(previousEntries)
							.limit(perPage)
							.collect(Collectors.toList());
		int totalPages = (int) Math.ceil(((double)userList.size() / perPage));
		PagedResponse response = new PagedResponse(pageOfUsrs, page, totalPages);
		
		return response;
	}
	
	public List<String> getAllDistinctNames() {
		return userList.stream()
				.map((u) -> u.getName())
				.distinct()
				.collect(Collectors.toList());
	}
}
