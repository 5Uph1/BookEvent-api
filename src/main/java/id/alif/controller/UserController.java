package id.alif.controller;

import java.util.List;

import id.alif.dto.request.CreateUserRequest;
import id.alif.dto.request.UpdateUserRequest;
import id.alif.dto.response.ApiResponse;
import id.alif.entity.User;
import id.alif.services.UserService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;

    @GET
    public ApiResponse<List<User>> getUsers() {
        return new ApiResponse<>(
                200,
                "Success get users",
                userService.getAllUsers()
        );
    }

    @GET
    @Path("/{id}")
    public ApiResponse<User> getUserById(@PathParam("id") Long id) {
        return new ApiResponse<>(
                200,
                "Success get user",
                userService.getUserById(id)
        );
    }

    @POST
    public ApiResponse<User> createUser(@Valid CreateUserRequest request) {
        return new ApiResponse<>(
                201,
                "Success create user",
                userService.createUser(request)
        );
    }

    @PUT
    @Path("/{id}")
    public ApiResponse<User> updateUser(
            @PathParam("id") Long id,
            UpdateUserRequest request) {

        return new ApiResponse<>(
                200,
                "Success update user",
                userService.updateUser(request, id)
        );
    }

    @DELETE
    @Path("/{id}")
    public ApiResponse<Void> deleteUser(@PathParam("id") Long id) {

        userService.deleteUser(id);

        return new ApiResponse<>(
                200,
                "Success delete user"
        );
    }

    // Pembuatan Admin
    @POST
    @Path("/create/admin")
    public ApiResponse<User> createAdmin() {
        return new ApiResponse<>(
                201,
                "Success create user",
                userService.createAdmin()
        );
    }
}