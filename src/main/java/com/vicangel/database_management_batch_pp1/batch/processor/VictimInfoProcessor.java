package com.vicangel.database_management_batch_pp1.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.vicangel.database_management_batch_pp1.model.DataItemDTO;
import com.vicangel.database_management_batch_pp1.repository.entity.VictimInfoEntity;

@Component
public final class VictimInfoProcessor implements ItemProcessor<DataItemDTO, VictimInfoEntity> {

  @Override
  public VictimInfoEntity process(@NonNull final DataItemDTO item) {
    if (item.vict_age() == null && item.vict_sex() == null && item.vict_descent() == null) return null;

    Integer age = item.vict_age();
    if (age != null) {
      if (age < 0) {
        age = Math.abs(age);
      } else if (age > 100) age = age - 100;
    }

    return new VictimInfoEntity(item.dr_no(),
                                age,
                                item.vict_sex(),
                                item.vict_descent());
  }
}
