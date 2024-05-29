package org.example.specification;

import org.example.entity.Patient;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PatientSpecification {

    public static Specification<Patient> getPatientsByCriteria(String firstName, String lastName, Long doctorId) {
        return new Specification<Patient>() {
            @Override
            public Predicate toPredicate(Root<Patient> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.conjunction();

                if (firstName != null) {
                    predicate.getExpressions().add(criteriaBuilder.equal(root.get("firstName"), firstName));
                }

                if (lastName != null) {
                    predicate.getExpressions().add(criteriaBuilder.equal(root.get("lastName"), lastName));
                }

                if (doctorId != null) {
                    predicate.getExpressions().add(criteriaBuilder.equal(root.get("doctorId").get("id"), doctorId));
                }

                return predicate;
            }
        };
    }
}
