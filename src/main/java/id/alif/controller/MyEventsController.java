package id.alif.controller;

import java.util.List;
import java.util.stream.Collectors;

import id.alif.dto.response.ApiResponse;
import id.alif.dto.response.BookingResponse;
import id.alif.services.BookingService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/me/events")
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed("USER")
public class MyEventsController {

    @Inject
    BookingService bookingService;

    @Inject
    JsonWebToken jwt;

    @GET
    public ApiResponse<List<BookingResponse>> getMyEvents() {

        Long userId = Long.valueOf(jwt.getSubject());

        List<BookingResponse> bookings = bookingService.getMyEvents(userId)
                .stream()
                .map(BookingResponse::from)
                .collect(Collectors.toList());

        return new ApiResponse<>(200, "Success", bookings);
    }
}