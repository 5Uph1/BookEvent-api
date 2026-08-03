package id.alif.dto.response;

import id.alif.enums.UserRole;

public class LoginResponse {

    public String token;
    public UserSummary user;

    public LoginResponse(String token, UserSummary user) {
        this.token = token;
        this.user = user;
    }

    public static class UserSummary {
        public Long id;
        public String name;
        public String email;
        public UserRole role;

        public UserSummary(Long id, String name, String email, UserRole role) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.role = role;
        }
    }
}