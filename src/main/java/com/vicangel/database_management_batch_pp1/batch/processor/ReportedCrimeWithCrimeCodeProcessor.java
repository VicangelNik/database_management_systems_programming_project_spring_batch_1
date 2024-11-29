package com.vicangel.database_management_batch_pp1.batch.processor;

import java.util.HashSet;
import java.util.Set;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.vicangel.database_management_batch_pp1.model.DataItemDTO;
import com.vicangel.database_management_batch_pp1.repository.ReportedDataCrimeCodesKey;
import com.vicangel.database_management_batch_pp1.repository.entity.CrimeCodeEntity;
import com.vicangel.database_management_batch_pp1.repository.entity.ReportedCrimeWithCrimeCodesEntity;
import com.vicangel.database_management_batch_pp1.repository.entity.ReportedCrimesEntity;

@Component
public final class ReportedCrimeWithCrimeCodeProcessor implements ItemProcessor<DataItemDTO, Set<ReportedCrimeWithCrimeCodesEntity>> {

  @Override
  public Set<ReportedCrimeWithCrimeCodesEntity> process(@NonNull DataItemDTO item) {
    Set<ReportedCrimeWithCrimeCodesEntity> reportedCrimeWithCrimeCodeEntities = new HashSet<>();
    final Short crimeCode = item.crm_cd() == null ? item.crm_cd_1() : item.crm_cd();
    final var reportedCrime = ReportedCrimesEntity.builder().drNo(item.dr_no()).build();

    if (crimeCode != null) {
      reportedCrimeWithCrimeCodeEntities.add(ReportedCrimeWithCrimeCodesEntity.builder()
                                               .id(getReportedDataCrimeCodesKey(item.dr_no(), crimeCode))
                                               .reportedCrime(reportedCrime)
                                               .crimeCode(CrimeCodeEntity.builder().crimeCode(crimeCode).build())
                                               .crimeCodeLevel((short) 1)
                                               .build());
    }

    if (item.crm_cd_2() != null) {
      reportedCrimeWithCrimeCodeEntities.add(ReportedCrimeWithCrimeCodesEntity.builder()
                                               .id(getReportedDataCrimeCodesKey(item.dr_no(), item.crm_cd_2()))
                                               .reportedCrime(reportedCrime)
                                               .crimeCode(CrimeCodeEntity.builder().crimeCode(item.crm_cd_2()).build())
                                               .crimeCodeLevel((short) 2)
                                               .build());
    }

    if (item.crm_cd_3() != null) {
      reportedCrimeWithCrimeCodeEntities.add(ReportedCrimeWithCrimeCodesEntity.builder()
                                               .id(getReportedDataCrimeCodesKey(item.dr_no(), item.crm_cd_3()))
                                               .reportedCrime(reportedCrime)
                                               .crimeCode(CrimeCodeEntity.builder().crimeCode(item.crm_cd_3()).build())
                                               .crimeCodeLevel((short) 3)
                                               .build());
    }

    if (item.crm_cd_4() != null) {
      reportedCrimeWithCrimeCodeEntities.add(ReportedCrimeWithCrimeCodesEntity.builder()
                                               .id(getReportedDataCrimeCodesKey(item.dr_no(), item.crm_cd_4()))
                                               .reportedCrime(reportedCrime)
                                               .crimeCode(CrimeCodeEntity.builder().crimeCode(item.crm_cd_4()).build())
                                               .crimeCodeLevel((short) 4)
                                               .build());
    }
    return reportedCrimeWithCrimeCodeEntities;
  }

  @NonNull
  private static ReportedDataCrimeCodesKey getReportedDataCrimeCodesKey(@NonNull final Long drNo,
                                                                        @NonNull final Short crimeCode) {
    final var key = new ReportedDataCrimeCodesKey();
    key.setDrNo(drNo);
    key.setCrimeCode(crimeCode);
    return key;
  }
}
