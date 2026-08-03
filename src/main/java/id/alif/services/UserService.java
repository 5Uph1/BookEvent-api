package id.alif.services;

import java.util.List;

import id.alif.dto.request.CreateUserRequest;
import id.alif.dto.request.UpdateUserRequest;
import id.alif.entity.User;
import id.alif.enums.UserRole;
import id.alif.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    @Inject
    PasswordService passwordService;

    @Transactional
    public User createUser(CreateUserRequest request) {

        User user = new User();

        user.name = request.name;
        user.email = request.email;
        user.password = passwordService.hash(request.password);
        user.role = UserRole.USER;

        userRepository.persist(user);

        return user;
    }

    @Transactional
    public User createAdmin() {

        User user = new User();

        user.name = "Admin";
        user.email = "admin@gmail.com";
        user.password = passwordService.hash("admin123");
        user.role = UserRole.ADMIN;

        userRepository.persist(user);

        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.listAll();
    }

    public User getUserById(Long userId) {

        User user = userRepository.findById(userId);

        if (user == null) {
            throw new NotFoundException("User not found");
        }

        return user;
    }

    @Transactional
    public User updateUser(UpdateUserRequest request, Long userId) {

        User user = userRepository.findById(userId);

        if (user == null) {
            throw new NotFoundException("User not found");
        }

        if (request.name != null) {
            user.name = request.name;
        }

        if (request.email != null) {
            user.email = request.email;
        }

        if (request.password != null) {
            user.password = request.password;
        }

        return user;
    }

    @Transactional
    public void deleteUser(Long userId) {

        User user = userRepository.findById(userId);

        if (user == null) {
            throw new NotFoundException("User not found");
        }

        userRepository.delete(user);
    }
}