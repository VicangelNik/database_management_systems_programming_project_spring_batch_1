package com.vicangel.database_management_batch_pp1.repository;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class ReportedDataCrimeCodesKey implements Serializable {

  @Column(name = "dr_no")
  private Long drNo;

  @Column(name = "crm_cd")
  private Short crimeCode;
}
