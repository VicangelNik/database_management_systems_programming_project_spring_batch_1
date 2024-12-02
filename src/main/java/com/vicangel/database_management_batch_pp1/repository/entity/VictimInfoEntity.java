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
@Table(name = "victim_info")
public class VictimInfoEntity {

  @Id
  @Column(name = "dr_no")
  private Long drNo;
  @Column(name = "vict_age", columnDefinition = "smallint")
  private Short victAge;
  @Column(name = "vict_sex")
  private Character victSex;
  @Column(name = "vict_descent")
  private Character victDescent;
}
