package com.vicangel.database_management_batch_pp1.repository.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
@Table(name = "reported_crimes", indexes = {
  @Index(name = "idx_name_reported", columnList = "dateReported"),
  @Index(name = "idx_crime_occurred_date_time", columnList = "occDateTime")
})
public class ReportedCrimesEntity {

  @Id
  @Column(name = "dr_no")
  private Long drNo;

  @Column(name = "date_reported")
  private LocalDate dateReported; // MM/DD/YYYY

  @Column(name = "occ_date_time", columnDefinition = "timestamp(0)")
  private LocalDateTime occDateTime;

  @ManyToOne // FK_ForeignKeyTable_PrimaryKeyTable
  @JoinColumn(name = "area", foreignKey = @ForeignKey(name = "FK_Reported_Crimes_Area"))
  private AreaEntity area; // area id

  @Column(name = "part_1_2")
  private Byte part12;

  @ManyToOne
  @JoinColumn(name = "reporting_district", referencedColumnName = "rep_dist", foreignKey = @ForeignKey(name = "FK_Reported_Crimes_Reporting_Districts"))
  private ReportingDistrictsEntity reportingDistrict;

  @ManyToMany
  @JoinTable(
    name = "reported_crimes_mocodes",
    joinColumns = @JoinColumn(name = "dr_no", foreignKey = @ForeignKey(name = "FK_Mocodes_Reported_Crimes")),
    inverseJoinColumns = @JoinColumn(name = "mocode_id", foreignKey = @ForeignKey(name = "FK_Reported_Crimes_Mocodes"))
  )
  private Set<MoCodesEntity> moCodes;

  @ManyToOne
  @JoinColumn(name = "victim_info", foreignKey = @ForeignKey(name = "FK_Victim_Info_Reported_Crimes"))
  private VictimInfoEntity victimInfo;

  @ManyToOne
  @JoinColumn(name = "premis", foreignKey = @ForeignKey(name = "FK_Reported_Crimes_Premises_"))
  private PremisEntity premis;

  @ManyToOne
  @JoinColumn(name = "weapon", foreignKey = @ForeignKey(name = "FK_Reported_Crimes_Weapons"))
  private WeaponsEntity weapon;

  @ManyToOne
  @JoinColumn(name = "status", foreignKey = @ForeignKey(name = "FK_Reported_Crimes_Premises"))
  private CaseStatusEntity status;

  @ManyToOne
  @JoinColumn(name = "location", foreignKey = @ForeignKey(name = "FK_Location_Reported_Crimes"))
  private LocationEntity location;
}
