package id.alif.dto.request;

import java.time.LocalDateTime;

public class UpdateEventRequest {

    public String title;
    public String description;
    public String location;
    public LocalDateTime startDate;
    public LocalDateTime endDate;
    public Integer quota;

    public UpdateEventRequest() {
    }
}