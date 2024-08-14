package vn.hoidanit.jobhunter.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User saveUser(User requestUser) {
        User currentUser = this.getUserById(requestUser.getId());
        if (currentUser != null) {
            currentUser.setName(requestUser.getName());
            currentUser.setEmail(requestUser.getEmail());
            currentUser.setPassword(requestUser.getPassword());
            this.userRepository.save(currentUser);
        }
        return currentUser;
    }

    public User handleCreateUser(User user) {
        return this.userRepository.save(user);
    }

    public void handleDeleteUser(long id) {
        this.userRepository.deleteById(id);
    }
}
