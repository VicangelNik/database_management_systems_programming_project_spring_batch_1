package com.vicangel.database_management_batch_pp1.batch.listener;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import com.vicangel.database_management_batch_pp1.repository.AreaRepository;
import com.vicangel.database_management_batch_pp1.repository.CaseStatusRepository;
import com.vicangel.database_management_batch_pp1.repository.CrimeCodeRepository;
import com.vicangel.database_management_batch_pp1.repository.LocationRepository;
import com.vicangel.database_management_batch_pp1.repository.MoCodesRepository;
import com.vicangel.database_management_batch_pp1.repository.PremisRepository;
import com.vicangel.database_management_batch_pp1.repository.ReportedCrimesRepository;
import com.vicangel.database_management_batch_pp1.repository.ReportingDistrictsRepository;
import com.vicangel.database_management_batch_pp1.repository.VictimInfoRepository;
import com.vicangel.database_management_batch_pp1.repository.WeaponsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class LapdDataImportJobListener implements JobExecutionListener {

  private final MoCodesRepository moCodesRepository;
  private final ReportingDistrictsRepository reportingDistrictsRepository;
  private final ReportedCrimesRepository reportedCrimesRepository;
  private final AreaRepository areaRepository;
  private final VictimInfoRepository victimInfoRepository;
  private final PremisRepository premisRepository;
  private final WeaponsRepository weaponsRepository;
  private final CaseStatusRepository caseStatusRepository;
  private final LocationRepository locationRepository;
  private final CrimeCodeRepository crimeCodeRepository;

  @Override
  public void beforeJob(JobExecution jobExecution) {
    log.info("Job:{} execution started", jobExecution.getJobInstance().getJobName());
  }

  @Override
  public void afterJob(JobExecution jobExecution) {
    if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
      log.info("Job completed: {}", jobExecution.getJobInstance().getJobName());
      log.info("MoCodes found: {}", moCodesRepository.findAll().size());
      log.info("Reporting Districts found: {}", reportingDistrictsRepository.findAll().size());
      log.info("Reported Crimes found: {}", reportedCrimesRepository.findAll().size());
      log.info("Area data found: {}", areaRepository.findAll().size());
      log.info("Victim Info found: {}", victimInfoRepository.findAll().size());
      log.info("Premis data found: {}", premisRepository.findAll().size());
      log.info("Weapons data found: {}", weaponsRepository.findAll().size());
      log.info("Case status data found: {}", caseStatusRepository.findAll().size());
      log.info("Location data found: {}", locationRepository.findAll().size());
      log.info("Crime codes found: {}", crimeCodeRepository.findAll().size());
    } else if (jobExecution.getStatus() == BatchStatus.FAILED) {
      log.error("Error while running job: {}", jobExecution.getJobInstance().getJobName());
    }
  }
}
