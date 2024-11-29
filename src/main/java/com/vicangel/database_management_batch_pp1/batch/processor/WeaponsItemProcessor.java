package com.vicangel.database_management_batch_pp1.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.vicangel.database_management_batch_pp1.model.DataItemDTO;
import com.vicangel.database_management_batch_pp1.repository.entity.WeaponsEntity;

@Component
public final class WeaponsItemProcessor implements ItemProcessor<DataItemDTO, WeaponsEntity> {

  @Override
  public WeaponsEntity process(DataItemDTO item) {

    return null == item.weapon_used_cd()
           ? null
           : new WeaponsEntity(item.weapon_used_cd(), item.weapon_desc());
  }
}
