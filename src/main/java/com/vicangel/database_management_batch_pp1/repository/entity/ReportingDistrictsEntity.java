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

/**
 * A four-digit code that represents a sub-area within a Geographic Area.
 * All crime records reference the "RD" that it occurred in for statistical comparisons.
 * Find LAPD Reporting Districts on the LA City GeoHub at
 *
 * @see <a href="https://geohub.lacity.org/datasets/lahub::lapd-reporting-districts/about">lapd-reporting-districts</a>
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "reporting_districts")
public class ReportingDistrictsEntity {

  /**
   * @implNote Declaration of uniqueness is avoided for better performance on insertion and keeping as light as possible.
   * This index is not needed.
   */
  @Column(name = "fid", columnDefinition = "smallint") // unique = true, nullable = false
  private Integer fid;

  @Id
  @Column(name = "rep_dist", columnDefinition = "smallint") // reporting District Number
  private Integer repDist;

  @Column(name = "prec", columnDefinition = "smallint")
  private Integer prec;

  @Column(name = "aprec", columnDefinition = "varchar(20)")
  private String apRec;

  @Column(name = "bureau", columnDefinition = "varchar(20)")
  private String bureau;

  @Column(name = "basic_car", columnDefinition = "varchar(10)")
  private String basicCar;

  @Column(name = "agency", columnDefinition = "char(2)")
  private String agency;

  @Column(name = "name", columnDefinition = "varchar(10)")
  private String name;

  @Column(name = "shape_leng")
  private Double shapeLeng;

  @Column(name = "abbrev_apr", columnDefinition = "varchar(5)")
  private String abbrevApr;

  @Column(name = "shape_area")
  private Double shapeArea;

  @Column(name = "shape_length")
  private Double shapeLength;
}
