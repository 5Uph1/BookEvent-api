package id.alif.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class UpdateUserRequest {

    public String name;

    @Email(message = "Email is invalid")
    public String email;

    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    public String password;

    public UpdateUserRequest() {
    }
}