package id.alif.controller;

import id.alif.dto.request.LoginRequest;
import id.alif.dto.response.ApiResponse;
import id.alif.dto.response.LoginResponse;
import id.alif.services.AuthService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthController {

    @Inject
    AuthService authService;

    @POST
    @Path("/login")
    public ApiResponse<LoginResponse> login(@Valid LoginRequest request) {

        return new ApiResponse<>(
                200,
                "Login success",
                authService.login(request)
        );
    }

}
