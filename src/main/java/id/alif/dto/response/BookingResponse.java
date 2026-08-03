package id.alif.dto.response;

import java.time.LocalDateTime;

import id.alif.entity.Booking;
import id.alif.enums.BookingStatus;

public class BookingResponse {

    public Long id;
    public Long userId;
    public Long eventId;
    public Integer totalTicket;
    public BookingStatus status;
    public LocalDateTime bookingDate;
    public EventSummary event;

    public BookingResponse() {
    }

    public static class EventSummary {
        public Long id;
        public String title;
        public String location;
        public LocalDateTime startDate;
        public LocalDateTime endDate;

        public EventSummary(Long id, String title, String location,
                             LocalDateTime startDate, LocalDateTime endDate) {
            this.id = id;
            this.title = title;
            this.location = location;
            this.startDate = startDate;
            this.endDate = endDate;
        }
    }

    public static BookingResponse from(Booking booking) {
        BookingResponse response = new BookingResponse();
        response.id = booking.id;
        response.userId = booking.user != null ? booking.user.id : null;
        response.eventId = booking.event != null ? booking.event.id : null;
        response.totalTicket = booking.totalTicket;
        response.status = booking.status;
        response.bookingDate = booking.bookingDate;

        if (booking.event != null) {
            response.event = new EventSummary(
                    booking.event.id,
                    booking.event.title,
                    booking.event.location,
                    booking.event.startDate,
                    booking.event.endDate
            );
        }

        return response;
    }
}