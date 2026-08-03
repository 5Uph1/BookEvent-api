package id.alif.controller;

import id.alif.dto.request.CreateBookingRequest;
import id.alif.dto.response.ApiResponse;
import id.alif.dto.response.BookingResponse;
import id.alif.entity.Booking;
import id.alif.services.BookingService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/events/{eventId}/register")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed("USER")
public class EventRegistrationController {

    @Inject
    BookingService bookingService;

    @Inject
    JsonWebToken jwt;

    @POST
    public ApiResponse<BookingResponse> register(
            @PathParam("eventId") Long eventId,
            CreateBookingRequest request) {

        Long userId = Long.valueOf(jwt.getSubject());
        int totalTicket = (request != null && request.totalTicket != null)
                ? request.totalTicket
                : 1;

        Booking booking = bookingService.registerEvent(userId, eventId, totalTicket);

        return new ApiResponse<>(
                201,
                "Booking created successfully",
                BookingResponse.from(booking)
        );
    }

    @DELETE
    public ApiResponse<Void> cancel(
            @PathParam("eventId") Long eventId) {

        Long userId = Long.valueOf(jwt.getSubject());

        bookingService.cancelBooking(userId, eventId);

        return new ApiResponse<>(200, "Booking cancelled successfully");
    }
}