package org.example.specification;

import org.example.entity.Doctor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class DoctorSpecification {

    public static Specification<Doctor> getDoctorsByCriteria(String firstName, String lastName, String position) {
        return new Specification<Doctor>() {
            @Override
            public Predicate toPredicate(Root<Doctor> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.conjunction();

                if (firstName != null) {
                    predicate.getExpressions().add(criteriaBuilder.equal(root.get("firstName"), firstName));
                }

                if (lastName != null) {
                    predicate.getExpressions().add(criteriaBuilder.equal(root.get("lastName"), lastName));
                }

                if (position != null) {
                    predicate.getExpressions().add(criteriaBuilder.equal(root.get("position"), position));
                }

                return predicate;
            }
        };
    }
}
