package id.alif.services;

import java.util.List;

import id.alif.dto.request.CreateEventRequest;
import id.alif.dto.request.UpdateEventRequest;
import id.alif.dto.response.PageResponse;
import id.alif.entity.Event;
import id.alif.repository.EventRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class EventService {

    @Inject
    EventRepository eventRepository;

    @Transactional
    public Event createEvent(CreateEventRequest request) {

        Event event = new Event();

        event.title = request.title;
        event.description = request.description;
        event.location = request.location;
        event.startDate = request.startDate;
        event.endDate = request.endDate;
        event.quota = request.quota;
        event.remainingQuota = request.quota;

        eventRepository.persist(event);

        return event;
    }

    public PageResponse<Event> getAllEvents(int page, int size, String keyword, String sortParam) {

        Sort sort = parseSort(sortParam);
        Page pageRequest = Page.of(page, size);

        PanacheQuery<Event> query = eventRepository.searchEvents(keyword, sort);
        long totalElements = query.count();

        List<Event> content = query.page(pageRequest).list();

        return new PageResponse<>(content, pageRequest, totalElements);
    }

    private Sort parseSort(String sortParam) {
        if (sortParam == null || sortParam.isBlank()) {
            return Sort.by("startDate").ascending();
        }

        String[] parts = sortParam.split(",");
        String field = parts[0];
        boolean desc = parts.length > 1 && parts[1].equalsIgnoreCase("desc");

        return desc ? Sort.by(field).descending() : Sort.by(field).ascending();
    }

    public Event getEventById(Long eventId) {

        Event event = eventRepository.findById(eventId);

        if (event == null) {
            throw new NotFoundException("Event not found");
        }

        return event;
    }

    @Transactional
    public Event updateEvent(UpdateEventRequest request, Long eventId) {

        Event event = eventRepository.findById(eventId);

        if (event == null) {
            throw new NotFoundException("Event not found");
        }

        if (request.title != null) {
            event.title = request.title;
        }

        if (request.description != null) {
            event.description = request.description;
        }

        if (request.location != null) {
            event.location = request.location;
        }

        if (request.startDate != null) {
            event.startDate = request.startDate;
        }

        if (request.endDate != null) {
            event.endDate = request.endDate;
        }

        if (request.quota != null) {
            int diff = request.quota - event.quota;
            event.quota = request.quota;
            event.remainingQuota = Math.max(0, event.remainingQuota + diff);
        }

        return event;
    }

    @Transactional
    public void deleteEvent(Long eventId) {

        Event event = eventRepository.findById(eventId);

        if (event == null) {
            throw new NotFoundException("Event not found");
        }

        eventRepository.delete(event);
    }
}