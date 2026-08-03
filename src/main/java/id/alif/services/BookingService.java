package id.alif.services;

import java.util.List;

import id.alif.entity.Booking;
import id.alif.entity.Event;
import id.alif.entity.User;
import id.alif.enums.BookingStatus;
import id.alif.repository.BookingRepository;
import id.alif.repository.EventRepository;
import id.alif.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class BookingService {

    @Inject
    BookingRepository bookingRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    EventRepository eventRepository;

    @Transactional
    public Booking registerEvent(Long userId, Long eventId, int totalTicket) {

        User user = userRepository.findById(userId);
        if (user == null) {
            throw new NotFoundException("User not found");
        }

        Event event = eventRepository.findById(eventId);
        if (event == null) {
            throw new NotFoundException("Event not found");
        }

        Booking existing = bookingRepository.findActiveByUserAndEvent(userId, eventId);
        if (existing != null) {
            throw new WebApplicationException("Already registered for this event", Response.Status.CONFLICT);
        }

        if (event.remainingQuota < totalTicket) {
            throw new IllegalArgumentException("Quota is not enough");
        }

        event.remainingQuota -= totalTicket;
        event.participantCount += totalTicket;

        Booking booking = new Booking();
        booking.user = user;
        booking.event = event;
        booking.totalTicket = totalTicket;
        booking.status = BookingStatus.ACTIVE;

        bookingRepository.persist(booking);

        return booking;
    }

    @Transactional
    public void cancelBooking(Long userId, Long eventId) {

        Booking booking = bookingRepository.findActiveByUserAndEvent(userId, eventId);

        if (booking == null) {
            throw new NotFoundException("Active booking not found");
        }

        booking.event.remainingQuota = Math.min(
                booking.event.quota,
                booking.event.remainingQuota + booking.totalTicket
        );
        booking.event.participantCount = Math.max(
                0,
                booking.event.participantCount - booking.totalTicket
        );
        booking.status = id.alif.enums.BookingStatus.CANCELLED;
    }

    public List<Booking> getMyEvents(Long userId) {
        return bookingRepository.findAllByUser(userId);
    }

    public List<Booking> getAllBooking() {
        return bookingRepository.listAll();
    }

    public Booking getBookingById(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId);
        if (booking == null) {
            throw new NotFoundException("Booking not found");
        }
        return booking;
    }
}