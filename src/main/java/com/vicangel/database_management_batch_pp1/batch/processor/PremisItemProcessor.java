package com.vicangel.database_management_batch_pp1.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.vicangel.database_management_batch_pp1.model.DataItemDTO;
import com.vicangel.database_management_batch_pp1.repository.entity.PremisEntity;

@Component
public final class PremisItemProcessor implements ItemProcessor<DataItemDTO, PremisEntity> {

  @Override
  public PremisEntity process(DataItemDTO item) {
    return null == item.premic_cd()
           ? null
           : new PremisEntity(item.premic_cd(), item.premis_desc());
  }
}
