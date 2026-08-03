package id.alif.dto.response;

public class UserResponse {
    public Long id;
    public String name;
    public String email;
    public String password;

    public UserResponse(){
    }

    public UserResponse(Long id, String name, String email, String password){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
