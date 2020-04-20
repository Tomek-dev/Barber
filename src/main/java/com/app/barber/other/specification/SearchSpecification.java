package com.app.barber.other.specification;

import com.app.barber.model.Barber;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

public class SearchSpecification implements Specification<Barber> {

    private String city;
    private String query;

    public SearchSpecification(String city, String query) {
        this.city = city;
        this.query = query;
    }

    public String getCity() {
        return city;
    }

    public String getQuery() {
        return query;
    }

    @Override
    public Predicate toPredicate(Root<Barber> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new LinkedList<>();
        if(query != null){
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + query.toLowerCase() + "%"));
        }
        if(city != null){
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("city")), "%" + city.toLowerCase() + "%"));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
