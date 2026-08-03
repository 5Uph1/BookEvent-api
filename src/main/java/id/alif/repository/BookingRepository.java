package id.alif.repository;

import id.alif.entity.Booking;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BookingRepository implements PanacheRepository<Booking> {

    public Booking findActiveByUserAndEvent(Long userId, Long eventId) {
        return find(
                "user.id = ?1 and event.id = ?2 and status = ?3",
                userId, eventId, id.alif.enums.BookingStatus.ACTIVE
        ).firstResult();
    }

    public java.util.List<Booking> findAllByUser(Long userId) {
        return list("user.id", userId);
    }
}