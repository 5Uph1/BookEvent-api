package id.alif.repository;

import id.alif.entity.Event;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EventRepository implements PanacheRepository<Event> {

    public PanacheQuery<Event> searchEvents(String keyword, Sort sort) {
        if (keyword != null && !keyword.isBlank()) {
            return find(
                    "LOWER(title) LIKE LOWER(:kw) OR LOWER(location) LIKE LOWER(:kw)",
                    sort,
                    Parameters.with("kw", "%" + keyword + "%")
            );
        }

        return findAll(sort);
    }
}