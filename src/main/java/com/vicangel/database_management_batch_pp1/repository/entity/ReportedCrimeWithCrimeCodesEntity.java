package com.vicangel.database_management_batch_pp1.repository.entity;

import com.vicangel.database_management_batch_pp1.repository.ReportedDataCrimeCodesKey;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
@Table(name = "reported_crimes_crime_codes")
public class ReportedCrimeWithCrimeCodesEntity {

  @EmbeddedId
  private ReportedDataCrimeCodesKey id;

  @ManyToOne
  @MapsId("drNo")
  @JoinColumn(name = "dr_no", foreignKey = @ForeignKey(name = "FK_Reported_With_Codes_Reported_Crimes"))
  private ReportedCrimesEntity reportedCrime;

  @ManyToOne
  @MapsId("crimeCode")
  @JoinColumn(name = "crm_cd", foreignKey = @ForeignKey(name = "FK_Reported_With_Codes_Crime_Codes"))
  private CrimeCodeEntity crimeCode;

  @Column(name = "crm_cd_level", columnDefinition = "smallint")
  private Short crimeCodeLevel;
}
