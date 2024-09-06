package vn.hoidanit.jobhunter.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.domain.dto.Meta;
import vn.hoidanit.jobhunter.domain.dto.ResultPaginationDTO;
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

    public User getUserByUsername(String username) {
        return this.userRepository.findByEmail(username);
    }

    public ResultPaginationDTO getAllUsers(Specification<User> spec, Pageable pageable) {
        Page<User> pageUser = this.userRepository.findAll(spec, pageable);
        ResultPaginationDTO resultPaginationDTO = new ResultPaginationDTO();
        Meta meta = new Meta();
        meta.setPage(pageUser.getNumber() + 1);
        meta.setPageSize(pageUser.getSize());
        meta.setTotal(pageUser.getTotalElements());
        meta.setPages(pageUser.getTotalPages());
        resultPaginationDTO.setMeta(meta);
        resultPaginationDTO.setResult(pageUser.getContent());
        return resultPaginationDTO;
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
