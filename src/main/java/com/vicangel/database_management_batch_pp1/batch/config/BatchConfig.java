package com.vicangel.database_management_batch_pp1.batch.config;

import java.util.Set;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.vicangel.database_management_batch_pp1.batch.listener.LapdDataImportJobListener;
import com.vicangel.database_management_batch_pp1.batch.processor.AreaItemProcessor;
import com.vicangel.database_management_batch_pp1.batch.processor.CaseStatusItemProcessor;
import com.vicangel.database_management_batch_pp1.batch.processor.CrimeCodeProcessor;
import com.vicangel.database_management_batch_pp1.batch.processor.CrimeDataItemProcessor;
import com.vicangel.database_management_batch_pp1.batch.processor.LocationProcessor;
import com.vicangel.database_management_batch_pp1.batch.processor.PremisItemProcessor;
import com.vicangel.database_management_batch_pp1.batch.processor.ReportedCrimeWithCrimeCodeProcessor;
import com.vicangel.database_management_batch_pp1.batch.processor.VictimInfoProcessor;
import com.vicangel.database_management_batch_pp1.batch.processor.WeaponsItemProcessor;
import com.vicangel.database_management_batch_pp1.model.DataItemDTO;
import com.vicangel.database_management_batch_pp1.repository.AreaRepository;
import com.vicangel.database_management_batch_pp1.repository.CaseStatusRepository;
import com.vicangel.database_management_batch_pp1.repository.CrimeCodeRepository;
import com.vicangel.database_management_batch_pp1.repository.LocationRepository;
import com.vicangel.database_management_batch_pp1.repository.MoCodesRepository;
import com.vicangel.database_management_batch_pp1.repository.PremisRepository;
import com.vicangel.database_management_batch_pp1.repository.ReportedCrimesRepository;
import com.vicangel.database_management_batch_pp1.repository.ReportedCrimesWithCrimeCodesRepository;
import com.vicangel.database_management_batch_pp1.repository.ReportingDistrictsRepository;
import com.vicangel.database_management_batch_pp1.repository.VictimInfoRepository;
import com.vicangel.database_management_batch_pp1.repository.WeaponsRepository;
import com.vicangel.database_management_batch_pp1.repository.entity.AreaEntity;
import com.vicangel.database_management_batch_pp1.repository.entity.CaseStatusEntity;
import com.vicangel.database_management_batch_pp1.repository.entity.CrimeCodeEntity;
import com.vicangel.database_management_batch_pp1.repository.entity.LocationEntity;
import com.vicangel.database_management_batch_pp1.repository.entity.MoCodesEntity;
import com.vicangel.database_management_batch_pp1.repository.entity.PremisEntity;
import com.vicangel.database_management_batch_pp1.repository.entity.ReportedCrimeWithCrimeCodesEntity;
import com.vicangel.database_management_batch_pp1.repository.entity.ReportedCrimesEntity;
import com.vicangel.database_management_batch_pp1.repository.entity.ReportingDistrictsEntity;
import com.vicangel.database_management_batch_pp1.repository.entity.VictimInfoEntity;
import com.vicangel.database_management_batch_pp1.repository.entity.WeaponsEntity;
import lombok.RequiredArgsConstructor;

import static com.vicangel.database_management_batch_pp1.batch.config.BatchUtil.readCharacterValue;
import static com.vicangel.database_management_batch_pp1.batch.config.BatchUtil.readIntValue;
import static com.vicangel.database_management_batch_pp1.batch.config.BatchUtil.readShortValue;

@RequiredArgsConstructor
@Configuration
class BatchConfig {

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
  private final ReportedCrimesWithCrimeCodesRepository reportedCrimesWithCrimeCodesRepository;

  @Bean
  public Job csvCrimeDataJob(Step importMocodesStep,
                             Step importReportingDistrictsStep,
                             Step importCrimeDataStep,
                             Step importAreaDataStep,
                             Step importVictimInfoStep,
                             Step importPremisDataStep,
                             Step importWeaponsDataStep,
                             Step importCaseStatusDataStep,
                             Step importLocationDataStep,
                             Step importCrimeCodeDataStep,
                             Step importReportedCrimeWithCrimeCodesDataStep,
                             JobRepository jobRepository,
                             LapdDataImportJobListener listener) {
    return new JobBuilder("crimeDataJob", jobRepository)
      .listener(listener)
      .flow(importMocodesStep)
      .next(importReportingDistrictsStep)
      .next(importAreaDataStep)
      .next(importVictimInfoStep)
      .next(importPremisDataStep)
      .next(importWeaponsDataStep)
      .next(importCaseStatusDataStep)
      .next(importLocationDataStep)
      .next(importCrimeCodeDataStep)
      .next(importCrimeDataStep)
      .next(importReportedCrimeWithCrimeCodesDataStep)
      .end()
      .build();
  }

  @Bean
  public Step importCrimeDataStep(ItemReader<DataItemDTO> csvReader,
                                  CrimeDataItemProcessor processor,
                                  ItemWriter<ReportedCrimesEntity> reportedCrimesWriter,
                                  JobRepository jobRepository,
                                  PlatformTransactionManager transactionManager) {
    return new StepBuilder("importCrimeDataStep", jobRepository)
      .<DataItemDTO, ReportedCrimesEntity>chunk(100, transactionManager)
      .reader(csvReader)
      .processor(processor)
      .writer(reportedCrimesWriter)
      .build();
  }

  @Bean
  public Step importAreaDataStep(ItemReader<DataItemDTO> csvReader,
                                 AreaItemProcessor processor,
                                 ItemWriter<AreaEntity> writer,
                                 JobRepository jobRepository,
                                 PlatformTransactionManager transactionManager) {
    return new StepBuilder("importCrimeDataStep", jobRepository)
      .<DataItemDTO, AreaEntity>chunk(100, transactionManager)
      .reader(csvReader)
      .processor(processor)
      .writer(writer)
      .build();
  }

  @Bean
  public Step importCrimeCodeDataStep(ItemReader<DataItemDTO> csvReader,
                                      CrimeCodeProcessor processor,
                                      ItemWriter<Set<CrimeCodeEntity>> writer,
                                      JobRepository jobRepository,
                                      PlatformTransactionManager transactionManager) {
    return new StepBuilder("importCrimeCodeDataStep", jobRepository)
      .<DataItemDTO, Set<CrimeCodeEntity>>chunk(100, transactionManager)
      .reader(csvReader)
      .processor(processor)
      .writer(writer)
      .build();
  }

  @Bean
  public Step importReportedCrimeWithCrimeCodesDataStep(ItemReader<DataItemDTO> csvReader,
                                                        ReportedCrimeWithCrimeCodeProcessor processor,
                                                        ItemWriter<Set<ReportedCrimeWithCrimeCodesEntity>> writer,
                                                        JobRepository jobRepository,
                                                        PlatformTransactionManager transactionManager) {
    return new StepBuilder("importReportedCrimeWithCrimeCodesDataStep", jobRepository)
      .<DataItemDTO, Set<ReportedCrimeWithCrimeCodesEntity>>chunk(100, transactionManager)
      .reader(csvReader)
      .processor(processor)
      .writer(writer)
      .build();
  }

  @Bean
  public Step importPremisDataStep(ItemReader<DataItemDTO> csvReader,
                                   PremisItemProcessor processor,
                                   ItemWriter<PremisEntity> writer,
                                   JobRepository jobRepository,
                                   PlatformTransactionManager transactionManager) {
    return new StepBuilder("importPremisDataStep", jobRepository)
      .<DataItemDTO, PremisEntity>chunk(100, transactionManager)
      .reader(csvReader)
      .processor(processor)
      .writer(writer)
      .build();
  }

  @Bean
  public Step importLocationDataStep(ItemReader<DataItemDTO> csvReader,
                                     LocationProcessor processor,
                                     ItemWriter<LocationEntity> writer,
                                     JobRepository jobRepository,
                                     PlatformTransactionManager transactionManager) {
    return new StepBuilder("importLocationDataStep", jobRepository)
      .<DataItemDTO, LocationEntity>chunk(100, transactionManager)
      .reader(csvReader)
      .processor(processor)
      .writer(writer)
      .build();
  }

  @Bean
  public Step importWeaponsDataStep(ItemReader<DataItemDTO> csvReader,
                                    WeaponsItemProcessor processor,
                                    ItemWriter<WeaponsEntity> writer,
                                    JobRepository jobRepository,
                                    PlatformTransactionManager transactionManager) {
    return new StepBuilder("importWeaponsDataStep", jobRepository)
      .<DataItemDTO, WeaponsEntity>chunk(100, transactionManager)
      .reader(csvReader)
      .processor(processor)
      .writer(writer)
      .build();
  }

  @Bean
  public Step importCaseStatusDataStep(ItemReader<DataItemDTO> csvReader,
                                       CaseStatusItemProcessor processor,
                                       ItemWriter<CaseStatusEntity> writer,
                                       JobRepository jobRepository,
                                       PlatformTransactionManager transactionManager) {
    return new StepBuilder("importCaseStatusDataStep", jobRepository)
      .<DataItemDTO, CaseStatusEntity>chunk(100, transactionManager)
      .reader(csvReader)
      .processor(processor)
      .writer(writer)
      .build();
  }

  @Bean
  public Step importVictimInfoStep(ItemReader<DataItemDTO> csvReader,
                                   VictimInfoProcessor processor,
                                   ItemWriter<VictimInfoEntity> writer,
                                   JobRepository jobRepository,
                                   PlatformTransactionManager transactionManager) {
    return new StepBuilder("importVictimInfoStep", jobRepository)
      .<DataItemDTO, VictimInfoEntity>chunk(100, transactionManager)
      .reader(csvReader)
      .processor(processor)
      .writer(writer)
      .build();
  }

  @Bean
  public Step importMocodesStep(ItemReader<MoCodesEntity> csvReader,
                                ItemWriter<MoCodesEntity> csvWriter,
                                JobRepository jobRepository,
                                PlatformTransactionManager transactionManager) {
    return new StepBuilder("importMoCodesStep", jobRepository)
      .<MoCodesEntity, MoCodesEntity>chunk(100, transactionManager)
      .reader(csvReader)
      .writer(csvWriter)
      .build();
  }

  @Bean
  public Step importReportingDistrictsStep(ItemReader<ReportingDistrictsEntity> csvReader,
                                           ItemWriter<ReportingDistrictsEntity> csvWriter,
                                           JobRepository jobRepository,
                                           PlatformTransactionManager transactionManager) {
    return new StepBuilder("importReportingDistrictsStep", jobRepository)
      .<ReportingDistrictsEntity, ReportingDistrictsEntity>chunk(100, transactionManager)
      .reader(csvReader)
      .writer(csvWriter)
      .build();
  }

  @Bean
  public FlatFileItemReader<DataItemDTO> csvReader() {
    return new FlatFileItemReaderBuilder<DataItemDTO>()
      .linesToSkip(1)
      .maxItemCount(100)
      .name("csvCrimeDataReader")
      .resource(new ClassPathResource("csv/Crime_Data_from_2020_to_Present_20241110.csv"))
      .delimited()
      .delimiter(",")
      .names("DR_NO", "Date Rptd", "DATE OCC", "TIME OCC", "AREA", "AREA NAME", "Rpt Dist No", "Part 1-2", "Crm Cd", "Crm Cd Desc",
             "Mocodes", "Vict Age", "Vict Sex", "Vict Descent", "Premis Cd", "Premis Desc", "Weapon Used Cd", "Weapon Desc",
             "Status", "Status Desc", "Crm Cd 1", "Crm Cd 2", "Crm Cd 3", "Crm Cd 4", "LOCATION", "Cross Street", "LAT", "LON")
      .fieldSetMapper(fieldSet -> DataItemDTO.builder()
        .dr_no(fieldSet.readLong("DR_NO"))
        .date_rptd(fieldSet.readString("Date Rptd"))
        .date_occ(fieldSet.readString("DATE OCC"))
        .time_occ(fieldSet.readString("TIME OCC"))
        .area(readIntValue(fieldSet, "AREA"))
        .area_name(fieldSet.readString("AREA NAME"))
        .rpt_dist(readIntValue(fieldSet, "Rpt Dist No"))
        .part_1_2(fieldSet.readByte("Part 1-2"))
        .crm_cd(readShortValue(fieldSet, "Crm Cd"))
        .crm_cd_desc(fieldSet.readString("Crm Cd Desc"))
        .mocodes(fieldSet.readString("Mocodes"))
        .vict_age(fieldSet.readShort("Vict Age"))
        .vict_sex(readCharacterValue(fieldSet, "Vict Sex"))
        .vict_descent(readCharacterValue(fieldSet, "Vict Descent"))
        .premic_cd(readIntValue(fieldSet, "Premis Cd"))
        .premis_desc(fieldSet.readString("Premis Desc"))
        .weapon_used_cd(readIntValue(fieldSet, "Weapon Used Cd"))
        .weapon_desc(fieldSet.readString("Weapon Desc")) // , , ,, ,,,, ,
        .status(fieldSet.readString("Status"))
        .status_desc(fieldSet.readString("Status Desc"))
        .crm_cd_1(readShortValue(fieldSet, "Crm Cd 1"))
        .crm_cd_2(readShortValue(fieldSet, "Crm Cd 2"))
        .crm_cd_3(readShortValue(fieldSet, "Crm Cd 3"))
        .crm_cd_4(readShortValue(fieldSet, "Crm Cd 4"))
        .location(fieldSet.readString("LOCATION"))
        .cross_street(fieldSet.readString("Cross Street"))
        .lat(fieldSet.readDouble("LAT"))
        .lon(fieldSet.readDouble("LON"))
        .build()
      ).build();
  }

  @Bean
  public FlatFileItemReader<ReportingDistrictsEntity> reportingDistrictsReader() {
    return new FlatFileItemReaderBuilder<ReportingDistrictsEntity>()
      .linesToSkip(1)
      .name("csvReportingDistrictsReader")
      .resource(new ClassPathResource("csv/LAPD_Reporting_Districts.csv"))
      .delimited()
      .delimiter(",")
      .names("fid", "repDist", "prec", "apRec", "bureau", "basicCar", "agency", "name", "shapeLeng", "abbrevApr", "shapeArea", "shapeLength")
      .fieldSetMapper(reportingDistrictsBeanWrapperFieldSetMapper())
      .build();
  }

  @Bean
  public FlatFileItemReader<MoCodesEntity> moCodesReader() {
    return new FlatFileItemReaderBuilder<MoCodesEntity>()
      .linesToSkip(1)
      .name("csvMoCodesReader")
      .resource(new ClassPathResource("csv/MO_CODES.csv"))
      .delimited()
      .delimiter(";")
      .names("id", "moCode")
      .fieldSetMapper(moCodesBeanWrapperFieldSetMapper())
      .build();
  }

  @Bean
  public BeanWrapperFieldSetMapper<MoCodesEntity> moCodesBeanWrapperFieldSetMapper() {
    BeanWrapperFieldSetMapper<MoCodesEntity> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
    fieldSetMapper.setTargetType(MoCodesEntity.class);
    return fieldSetMapper;
  }

  @Bean
  public BeanWrapperFieldSetMapper<ReportingDistrictsEntity> reportingDistrictsBeanWrapperFieldSetMapper() {
    BeanWrapperFieldSetMapper<ReportingDistrictsEntity> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
    fieldSetMapper.setTargetType(ReportingDistrictsEntity.class);
    return fieldSetMapper;
  }

  @Bean
  public ItemWriter<MoCodesEntity> moCodesWriter() {
    return moCodesRepository::saveAll;
  }

  @Bean
  public ItemWriter<ReportingDistrictsEntity> reportingDistrictsWriter() {
    return reportingDistrictsRepository::saveAll;
  }

  @Bean
  public ItemWriter<ReportedCrimesEntity> reportedCrimesWriter() {
    return reportedCrimesRepository::saveAll;
  }

  @Bean
  public ItemWriter<AreaEntity> areaWriter() {
    return areaRepository::saveAll;
  }

  @Bean
  public ItemWriter<VictimInfoEntity> victimInfoWriter() {
    return victimInfoRepository::saveAll;
  }

  @Bean
  public ItemWriter<PremisEntity> premisWriter() {
    return premisRepository::saveAll;
  }

  @Bean
  public ItemWriter<WeaponsEntity> weaponWriter() {
    return weaponsRepository::saveAll;
  }

  @Bean
  public ItemWriter<CaseStatusEntity> statusWriter() {
    return caseStatusRepository::saveAll;
  }

  @Bean
  public ItemWriter<LocationEntity> locationWriter() {
    return locationRepository::saveAll;
  }

  @Bean
  public ItemWriter<Set<CrimeCodeEntity>> crimeCodeWriter() {
    return chunk -> chunk.getItems().forEach(crimeCodeRepository::saveAll);
  }

  @Bean
  public ItemWriter<Set<ReportedCrimeWithCrimeCodesEntity>> reportedCrimesWithCrimeCodesWriter() {
    return chunk -> chunk.getItems().forEach(reportedCrimesWithCrimeCodesRepository::saveAll);
  }
}