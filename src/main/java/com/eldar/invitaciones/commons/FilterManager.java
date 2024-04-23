package com.eldar.invitaciones.commons;

import com.eldar.invitaciones.model.SortEnum;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class FilterManager {

    public Pageable getPageable(SortEnum sort, Integer pageNumber, Integer pageSize) {
        return getPageable(sort, "id", pageNumber, pageSize);
    }
    public Pageable getPageable(SortEnum sort, String sortField, Integer pageNumber, Integer pageSize) {

        Sort sortFilter;

        if (sort.equals(SortEnum.ASC)) {
            sortFilter = Sort.by(Sort.Direction.ASC, sortField);
        } else {
            sortFilter = Sort.by(Sort.Direction.DESC, sortField);
        }

        return PageRequest.of(pageNumber, pageSize, sortFilter);

    }
}
