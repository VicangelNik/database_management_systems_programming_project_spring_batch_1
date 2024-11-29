package com.vicangel.database_management_batch_pp1.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.vicangel.database_management_batch_pp1.model.DataItemDTO;
import com.vicangel.database_management_batch_pp1.repository.entity.LocationEntity;

import static com.vicangel.database_management_batch_pp1.batch.processor.ProcessorUtil.normalizeSpace;

@Component
public final class LocationProcessor implements ItemProcessor<DataItemDTO, LocationEntity> {

  @Override
  public LocationEntity process(DataItemDTO item) {

    return item.location() == null && item.cross_street() == null
           ? null
           : new LocationEntity(item.dr_no(),
                                normalizeSpace(item.location()),
                                normalizeSpace(item.cross_street()),
                                item.lat(),
                                item.lon());
  }
}
