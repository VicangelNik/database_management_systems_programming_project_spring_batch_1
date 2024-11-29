package com.vicangel.database_management_batch_pp1.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.vicangel.database_management_batch_pp1.model.DataItemDTO;
import com.vicangel.database_management_batch_pp1.repository.entity.AreaEntity;

@Component
public final class AreaItemProcessor implements ItemProcessor<DataItemDTO, AreaEntity> {

  @Override
  public AreaEntity process(DataItemDTO item) {

    return null == item.area() ? null : new AreaEntity(item.area(), item.area_name());
  }
}
