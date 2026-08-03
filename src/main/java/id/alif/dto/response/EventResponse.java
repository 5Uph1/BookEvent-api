package id.alif.dto.response;

public class EventResponse {

    public Long id;
    public String title;

    public EventResponse() {
    }

    public EventResponse(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}