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

@Path("/bookings")
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed("ADMIN")
public class BookingController {

    @Inject
    BookingService bookingService;

    @GET
    public ApiResponse<List<BookingResponse>> getBookings() {
        List<BookingResponse> bookings = bookingService.getAllBooking()
                .stream()
                .map(BookingResponse::from)
                .collect(Collectors.toList());

        return new ApiResponse<>(200, "Success", bookings);
    }

    @GET
    @Path("/{id}")
    public ApiResponse<BookingResponse> getBookingById(@PathParam("id") Long id) {
        return new ApiResponse<>(
                200,
                "Success",
                BookingResponse.from(bookingService.getBookingById(id))
        );
    }
}