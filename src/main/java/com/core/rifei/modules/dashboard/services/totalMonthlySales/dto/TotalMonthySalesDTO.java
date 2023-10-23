package com.core.rifei.modules.dashboard.services.totalMonthlySales.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TotalMonthySalesDTO {

  private int newUsers;

  private int totalSales;

  private BigDecimal totalValueSalesConfirmed;
  private BigDecimal totalValueSales;
}
