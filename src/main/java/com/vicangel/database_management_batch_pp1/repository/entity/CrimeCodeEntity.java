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
@Entity
@Builder
@Table(name = "crime_codes")
public class CrimeCodeEntity {

  @Id
  @Column(name = "crm_cd")
  private Short crimeCode;

  @Column(name = "crm_cd_desc")
  private String crimeCodeDescription;
}
