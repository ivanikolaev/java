package org.example.specification;

import org.example.entity.Card;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CardSpecification {

    public static Specification<Card> getCardsByCriteria(String cardNumber, String code, Long bankId) {
        return new Specification<Card>() {
            @Override
            public Predicate toPredicate(Root<Card> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.conjunction();

                if (cardNumber != null) {
                    predicate.getExpressions().add(criteriaBuilder.equal(root.get("cardNumber"), cardNumber));
                }

                if (code != null) {
                    predicate.getExpressions().add(criteriaBuilder.equal(root.get("code"), code));
                }

                if (bankId != null) {
                    predicate.getExpressions().add(criteriaBuilder.equal(root.get("bank").get("id"), bankId));
                }

                return predicate;
            }
        };
    }
}
