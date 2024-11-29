package com.vicangel.database_management_batch_pp1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(properties =
  "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration")
@SpringBootTest
final class DatabaseManagementBatchPp1ApplicationTests {

  @Test
  void contextLoads() {
  }
}
