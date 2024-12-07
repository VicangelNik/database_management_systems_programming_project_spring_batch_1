package com.vicangel.database_management_batch_pp1.model;

import lombok.Builder;

@Builder
public record DataItemDTO(Long dr_no,
                          String date_rptd, // read date time values as string and will be converted in the processor
                          String date_occ,
                          String time_occ,
                          Integer area,
                          String area_name,
                          Integer rpt_dist,
                          Byte part_1_2,
                          Short crm_cd,
                          String crm_cd_desc,
                          String mocodes, // 4-digit numbers separated by space
                          Integer vict_age,
                          Character vict_sex,
                          Character vict_descent,
                          Integer premic_cd,
                          String premis_desc,
                          Integer weapon_used_cd,
                          String weapon_desc,
                          String status,
                          String status_desc,
                          Short crm_cd_1,
                          Short crm_cd_2,
                          Short crm_cd_3,
                          Short crm_cd_4,
                          String location,
                          String cross_street,
                          Double lat,
                          Double lon) {
}
