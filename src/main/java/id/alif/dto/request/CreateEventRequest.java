package id.alif.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateEventRequest {

    @NotBlank
    public String title;

    @NotBlank
    public String description;

    @NotBlank
    public String location;

    @NotNull
    public LocalDateTime startDate;

    @NotNull
    public LocalDateTime endDate;

    @NotNull
    public Integer quota;

    public CreateEventRequest() {
    }
}