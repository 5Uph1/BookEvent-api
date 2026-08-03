package id.alif.dto.response;

import java.util.List;

import io.quarkus.panache.common.Page;

public class PageResponse<T> {

    public List<T> content;
    public int number;
    public int size;
    public long totalElements;
    public int totalPages;

    public PageResponse() {
    }

    public PageResponse(List<T> content, Page page, long totalElements) {
        this.content = content;
        this.number = page.index;
        this.size = page.size;
        this.totalElements = totalElements;
        this.totalPages = (int) Math.ceil((double) totalElements / page.size);
        if (this.totalPages == 0) {
            this.totalPages = 1;
        }
    }
}