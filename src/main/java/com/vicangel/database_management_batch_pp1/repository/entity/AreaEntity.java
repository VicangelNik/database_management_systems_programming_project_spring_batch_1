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
@Table(name = "area")
public class AreaEntity {

  /**
   * The LAPD has 21 Community Police Stations referred to as geographic areas within the department.
   * These areas are sequentially numbered from 1 to 21.
   */
  @Id
  @Column(name = "area", columnDefinition = "smallint")
  private Integer area; // area id

  /**
   * The above 21 areas also known as “patrol divisions,” are also given a name
   * designation that references a landmark or the surrounding community. For example 77th
   * Street Division is located at the intersection of South Broadway and 77th Street, serving
   * neighborhoods in the broader South Los Angeles.
   */
  @Column(name = "area_name")
  private String areaName;
}
