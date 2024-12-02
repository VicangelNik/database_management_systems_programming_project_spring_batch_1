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
@Table(name = "premises")
public class PremisEntity {

  @Id
  @Column(name = "premic_cd", columnDefinition = "smallint")
  private Integer premisCd;

  @Column(name = "premis_desc")
  private String premisDesc;
}
