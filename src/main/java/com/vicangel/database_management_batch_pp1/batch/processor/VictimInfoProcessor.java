package com.vicangel.database_management_batch_pp1.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.vicangel.database_management_batch_pp1.model.DataItemDTO;
import com.vicangel.database_management_batch_pp1.repository.entity.VictimInfoEntity;

@Component
public final class VictimInfoProcessor implements ItemProcessor<DataItemDTO, VictimInfoEntity> {

  @Override
  public VictimInfoEntity process(DataItemDTO item) {

    return item.vict_age() == null && item.vict_sex() == null && item.vict_descent() == null
           ? null
           : new VictimInfoEntity(item.dr_no(),
                                  item.vict_age(),
                                  item.vict_sex(),
                                  item.vict_descent());
  }
}
