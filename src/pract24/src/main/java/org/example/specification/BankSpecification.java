package org.example.specification;

import org.example.entity.Bank;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class BankSpecification {

    public static Specification<Bank> getBanksByCriteria(String name, String address) {
        return new Specification<Bank>() {
            @Override
            public Predicate toPredicate(Root<Bank> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.conjunction();

                if (name != null) {
                    predicate.getExpressions().add(criteriaBuilder.equal(root.get("name"), name));
                }

                if (address != null) {
                    predicate.getExpressions().add(criteriaBuilder.equal(root.get("address"), address));
                }

                return predicate;
            }
        };
    }
}
