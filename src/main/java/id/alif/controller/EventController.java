package id.alif.controller;

import id.alif.dto.request.CreateEventRequest;
import id.alif.dto.request.UpdateEventRequest;
import id.alif.dto.response.ApiResponse;
import id.alif.dto.response.PageResponse;
import id.alif.entity.Event;
import id.alif.services.EventService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/events")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventController {

    @Inject
    EventService eventService;

    @GET
    public ApiResponse<PageResponse<Event>> getEvents(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("9") int size,
            @QueryParam("keyword") String keyword,
            @QueryParam("sort") String sort) {

        return new ApiResponse<>(
                200,
                "Success get events",
                eventService.getAllEvents(page, size, keyword, sort)
        );
    }

    @GET
    @Path("/{id}")
    public ApiResponse<Event> getEventById(@PathParam("id") Long id) {

        return new ApiResponse<>(
                200,
                "Success get event",
                eventService.getEventById(id)
        );
    }

    @POST
    public ApiResponse<Event> createEvent(@Valid CreateEventRequest request) {

        return new ApiResponse<>(
                201,
                "Event created successfully",
                eventService.createEvent(request)
        );
    }

    @PUT
    @Path("/{id}")
    public ApiResponse<Event> updateEvent(
            @PathParam("id") Long id,
            @Valid UpdateEventRequest request) {

        return new ApiResponse<>(
                200,
                "Event updated successfully",
                eventService.updateEvent(request, id)
        );
    }

    @DELETE
    @Path("/{id}")
    public ApiResponse<Void> deleteEvent(@PathParam("id") Long id) {

        eventService.deleteEvent(id);

        return new ApiResponse<>(
                200,
                "Event deleted successfully"
        );
    }
}