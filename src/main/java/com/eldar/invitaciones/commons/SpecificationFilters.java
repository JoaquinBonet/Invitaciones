package com.eldar.invitaciones.commons;


import com.eldar.invitaciones.model.Person;
import org.springframework.data.jpa.domain.Specification;
import java.util.Objects;


public class SpecificationFilters {


    public static Specification<Person> personConfirmedFilter(Boolean confirmed) {
        return (root, query, builder) -> {

            if (Objects.isNull(confirmed)) {
                return null;
            }
            return builder.equal(root.get("confirmed"), confirmed);
        };
    }


    }
