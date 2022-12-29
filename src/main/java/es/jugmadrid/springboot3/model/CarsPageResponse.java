package es.jugmadrid.springboot3.model;

import java.util.List;

public class CarsPageResponse<T> implements PageResponse<T> {

    private List<T> content;
    private int size;
    private long totalSize;
    private int page;
    private int totalPages;

    @Override
    //@Schema(name="content", description = "List of responses on the current page")
    public List<T> getContent() {
        return content;
    }

    @Override
    public void setContent(List<T> content) {
        this.content = content;
    }

    @Override
    //@Schema(name="size", description = "Page size")
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    //@Schema(name="totalSize", description = "Total count")
    public long getTotalSize() {
        return totalSize;
    }

    @Override
    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    @Override
    //@Schema(name="page", description = "Current page number, 0 based; i.e first-page = 0, second-page = 1")
    public int getPage() {
        return page;
    }

    @Override
    public void setPage(int page) {
        this.page = page;
    }

    @Override
    //@Schema(name="totalPages", description = "Total pages")
    public int getTotalPages() {
        return totalPages;
    }

    @Override
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void add(T element) {
        content.add(element);
    }

}
