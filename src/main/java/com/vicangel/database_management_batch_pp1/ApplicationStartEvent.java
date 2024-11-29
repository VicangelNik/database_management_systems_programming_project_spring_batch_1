package com.vicangel.database_management_batch_pp1;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ApplicationStartEvent {

  private final JobLauncher jobLauncher;
  private final Job csvMoCodesImporterJob;

  @EventListener(ApplicationReadyEvent.class)
  public void onReadyEvent() throws JobExecutionException {
    jobLauncher.run(csvMoCodesImporterJob, new JobParameters());
  }
}
