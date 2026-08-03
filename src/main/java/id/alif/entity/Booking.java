package id.alif.entity;

import java.time.LocalDateTime;

import id.alif.enums.BookingStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User user;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    public Event event;

    @Column(nullable = false)
    public Integer totalTicket;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public BookingStatus status = BookingStatus.ACTIVE;

    @Column(nullable = false)
    public LocalDateTime bookingDate = LocalDateTime.now();
}