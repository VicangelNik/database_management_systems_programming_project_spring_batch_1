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
@Table(name = "weapons")
public class WeaponsEntity {

  @Id
  @Column(name = "weapon_used_cd", columnDefinition = "smallint")
  private Integer weaponUsedCd;

  @Column(name = "weapon_desc")
  private String weaponDesc;
}
