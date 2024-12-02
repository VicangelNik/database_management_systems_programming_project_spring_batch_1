package com.vicangel.database_management_batch_pp1.batch.processor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.vicangel.database_management_batch_pp1.model.DataItemDTO;
import com.vicangel.database_management_batch_pp1.repository.entity.AreaEntity;
import com.vicangel.database_management_batch_pp1.repository.entity.CaseStatusEntity;
import com.vicangel.database_management_batch_pp1.repository.entity.LocationEntity;
import com.vicangel.database_management_batch_pp1.repository.entity.MoCodesEntity;
import com.vicangel.database_management_batch_pp1.repository.entity.PremisEntity;
import com.vicangel.database_management_batch_pp1.repository.entity.ReportedCrimesEntity;
import com.vicangel.database_management_batch_pp1.repository.entity.ReportingDistrictsEntity;
import com.vicangel.database_management_batch_pp1.repository.entity.VictimInfoEntity;
import com.vicangel.database_management_batch_pp1.repository.entity.WeaponsEntity;

@Component
public class CrimeDataItemProcessor implements ItemProcessor<DataItemDTO, ReportedCrimesEntity> {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
  private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmm");

  @Override
  public ReportedCrimesEntity process(@NonNull final DataItemDTO item) {

    final WeaponsEntity weapon = item.weapon_used_cd() != null
                                 ? WeaponsEntity.builder().weaponUsedCd(item.weapon_used_cd()).build()
                                 : null;

    final PremisEntity premis = item.premic_cd() != null
                                ? PremisEntity.builder().premisCd(item.premic_cd()).build()
                                : null;

    final AreaEntity area = item.area() != null ? AreaEntity.builder().area(item.area()).build() : null;

    VictimInfoEntity victimInfo = VictimInfoEntity.builder().drNo(item.dr_no()).build();

    if (item.vict_age() == null && item.vict_sex() == null && item.vict_descent() == null) {
      victimInfo = null;
    }

    final CaseStatusEntity status = item.status() != null
                                    ? CaseStatusEntity.builder().status(item.status()).build()
                                    : null;

    LocationEntity location = LocationEntity.builder().drNo(item.dr_no()).build();

    if (item.location() == null && item.cross_street() == null) {
      location = null;
    }

    Set<MoCodesEntity> moCodesEntitySet = getMoCodesEntities(item);

    return ReportedCrimesEntity.builder()
      .drNo(item.dr_no())
      .dateReported(LocalDate.parse(item.date_rptd(), FORMATTER))
      .occDateTime(getOccDateTime(item))
      .area(area)
      .part12(item.part_1_2())
      .reportingDistrict(ReportingDistrictsEntity.builder().repDist(item.rpt_dist()).build())
      .moCodes(moCodesEntitySet.isEmpty() ? null : moCodesEntitySet)
      .victimInfo(victimInfo)
      .premis(premis)
      .weapon(weapon)
      .status(status)
      .location(location)
      .build();
  }

  private static Set<MoCodesEntity> getMoCodesEntities(DataItemDTO item) {
    Set<MoCodesEntity> moCodesEntitySet = new HashSet<>();

    Set<Short> moCodes = item.mocodes() == null
                         ? new HashSet<>()
                         : Arrays.stream(item.mocodes().trim().split(" "))
                           .filter(m -> !m.isEmpty())
                           .filter(m -> !"0947".equals(m)) // it does not exist in mo codes csv
                           .map(Short::parseShort)
                           .collect(Collectors.toSet());

    moCodes.forEach(
      m -> moCodesEntitySet.add(MoCodesEntity.builder().id(m).build())
    );
    return moCodesEntitySet;
  }

  @NonNull
  private static LocalDateTime getOccDateTime(@NonNull final DataItemDTO item) {
    final LocalDate occDate = LocalDate.parse(item.date_occ(), FORMATTER);
    final LocalTime occTime = LocalTime.parse(item.time_occ(), TIME_FORMATTER);
    return LocalDateTime.of(occDate.getYear(), occDate.getMonth(), occDate.getDayOfMonth(), occTime.getHour(), occTime.getMinute());
  }
}
