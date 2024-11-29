package com.vicangel.database_management_batch_pp1.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.vicangel.database_management_batch_pp1.model.DataItemDTO;
import com.vicangel.database_management_batch_pp1.repository.entity.CaseStatusEntity;

@Component
public final class CaseStatusItemProcessor implements ItemProcessor<DataItemDTO, CaseStatusEntity> {

  @Override
  public CaseStatusEntity process(DataItemDTO item) {

    return null == item.area() ? null : new CaseStatusEntity(item.status(), item.status_desc());
  }
}
