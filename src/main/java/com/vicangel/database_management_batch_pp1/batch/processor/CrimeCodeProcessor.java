package com.vicangel.database_management_batch_pp1.batch.processor;

import java.util.HashSet;
import java.util.Set;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.vicangel.database_management_batch_pp1.model.DataItemDTO;
import com.vicangel.database_management_batch_pp1.repository.entity.CrimeCodeEntity;

import static com.vicangel.database_management_batch_pp1.batch.processor.ProcessorUtil.normalizeSpace;

@Component
public final class CrimeCodeProcessor implements ItemProcessor<DataItemDTO, Set<CrimeCodeEntity>> {

  @Override
  public Set<CrimeCodeEntity> process(@NonNull DataItemDTO item) {
    Set<CrimeCodeEntity> crimeCodeEntitySet = new HashSet<>();
    final Short crimeCode = item.crm_cd() == null ? item.crm_cd_1() : item.crm_cd();

    if (crimeCode != null) {
      crimeCodeEntitySet.add(new CrimeCodeEntity(crimeCode, normalizeSpace(item.crm_cd_desc())));
    }

    if (item.crm_cd_2() != null) {
      crimeCodeEntitySet.add(CrimeCodeEntity.builder().crimeCode(item.crm_cd_2()).build());
    }

    if (item.crm_cd_3() != null) {
      crimeCodeEntitySet.add(CrimeCodeEntity.builder().crimeCode(item.crm_cd_3()).build());
    }

    if (item.crm_cd_4() != null) {
      crimeCodeEntitySet.add(CrimeCodeEntity.builder().crimeCode(item.crm_cd_4()).build());
    }
    return crimeCodeEntitySet;
  }
}
