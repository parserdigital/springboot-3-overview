package es.jugmadrid.springboot3.model;

import java.util.Iterator;
import java.util.List;

public interface PageResponse<T> extends Iterable<T>{

    List<T> getContent();

    void setContent(List<T> content);

    int getSize();

    void setSize(int size);

    long getTotalSize();

    void setTotalSize(long totalSize);

    int getPage();

    void setPage(int page);

    int getTotalPages();

    void setTotalPages(int totalPages);

    @Override
    default Iterator<T> iterator() {
        return getContent().iterator();
    }
}
