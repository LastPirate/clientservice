package com.clientservice.adapter.driven.repository.client;


import com.clientservice.application.entity.command.FindClientCommand;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ClientJpaSpecification implements Specification<ClientJpaEntity> {

  private final FindClientCommand command;

  public ClientJpaSpecification(FindClientCommand command) {
    this.command = command;
  }

  @Override
  public Predicate toPredicate(Root<ClientJpaEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
    List<Predicate> predicates = new ArrayList<>();

    query.distinct(true);

    if (command.firstName != null) {
      Predicate firstNamePredicate = criteriaBuilder.equal(
          root.join("passports").get("firstName"), command.firstName
      );
      predicates.add(firstNamePredicate);
    }

    if (command.middleName != null) {
      Predicate middleNamePredicate = criteriaBuilder.equal(
          root.join("passports").get("middleName"), command.middleName
      );
      predicates.add(middleNamePredicate);
    }

    if (command.familyName != null) {
      Predicate familyNamePredicate = criteriaBuilder.equal(
          root.join("passports").get("familyName"), command.familyName
      );
      predicates.add(familyNamePredicate);
    }

    if (command.phoneNumber != null) {
      Predicate phoneNumberPredicate = criteriaBuilder.equal(root.get("phoneNumber"), command.phoneNumber);
      predicates.add(phoneNumberPredicate);
    }

    if (command.email != null) {
      Predicate emailPredicate = criteriaBuilder.equal(root.get("email"), command.email);
      predicates.add(emailPredicate);
    }

    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
  }
}
