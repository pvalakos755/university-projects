package myy803.springboot.main.services.interfaces;

import org.springframework.stereotype.Service;

import myy803.springboot.main.domainmodel.User;

@Service
public interface UserService {
	public void saveUser(User user);
    public boolean isUserPresent(User user);
}
