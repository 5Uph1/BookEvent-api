package id.alif.services;

import id.alif.dto.request.LoginRequest;
import id.alif.dto.response.LoginResponse;
import id.alif.entity.User;
import id.alif.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotAuthorizedException;

@ApplicationScoped
public class AuthService {

    @Inject
    UserRepository userRepository;

    @Inject
    PasswordService passwordService;

    @Inject
    JwtService jwtService;

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.email);

        if (user == null) {
            throw new NotAuthorizedException("Email or password is incorrect");
        }

        if (!passwordService.verify(request.password, user.password)) {
            throw new NotAuthorizedException("Email or password is incorrect");
        }

        String token = jwtService.generateToken(user);

        LoginResponse.UserSummary userSummary = new LoginResponse.UserSummary(
                user.id, user.name, user.email, user.role
        );

        return new LoginResponse(token, userSummary);
    }

}