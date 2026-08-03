package id.alif.dto.request;

import jakarta.validation.constraints.Min;

public class CreateBookingRequest {

    @Min(1)
    public Integer totalTicket;
}