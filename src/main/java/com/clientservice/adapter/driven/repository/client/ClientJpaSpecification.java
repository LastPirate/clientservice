package com.clientservice.adapter.driven.repository.client;


import com.clientservice.adapter.driven.repository.passport.PassportJpaStaticMetamodel;
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

    if (command.firstName != null) {
      Predicate firstNamePredicate = criteriaBuilder.equal(
          root.join(ClientJpaStaticMetamodel.passports).get(PassportJpaStaticMetamodel.firstName), command.firstName
      );
      predicates.add(firstNamePredicate);
    }

    if (command.middleName != null) {
      Predicate middleNamePredicate = criteriaBuilder.equal(
          root.join(ClientJpaStaticMetamodel.passports).get(PassportJpaStaticMetamodel.middleName), command.middleName
      );
      predicates.add(middleNamePredicate);
    }

    if (command.familyName != null) {
      Predicate familyNamePredicate = criteriaBuilder.equal(
          root.join(ClientJpaStaticMetamodel.passports).get(PassportJpaStaticMetamodel.familyName), command.familyName
      );
      predicates.add(familyNamePredicate);
    }

    if (command.phoneNumber != null) {
      Predicate phoneNumberPredicate = criteriaBuilder.equal(root.get(ClientJpaStaticMetamodel.phoneNumber), command.email);
      predicates.add(phoneNumberPredicate);
    }

    if (command.email != null) {
      Predicate emailPredicate = criteriaBuilder.equal(root.get(ClientJpaStaticMetamodel.email), command.email);
      predicates.add(emailPredicate);
    }

    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
  }
}
