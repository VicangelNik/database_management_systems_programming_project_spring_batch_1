package com.vicangel.database_management_batch_pp1.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "mo_codes")
public class MoCodesEntity {

  @Id
  @Column(name = "id", columnDefinition = "smallint")
  private Short id;

  @Column(name = "mo_code", columnDefinition = "varchar(200)")
  private String moCode;
}
