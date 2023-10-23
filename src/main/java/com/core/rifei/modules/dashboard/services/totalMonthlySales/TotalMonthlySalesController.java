package com.core.rifei.modules.dashboard.services.totalMonthlySales;

import com.core.rifei.modules.dashboard.services.totalMonthlySales.dto.TotalMonthySalesDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Dashboard Administrador")
@RestController
@RequestMapping("/admin/totalSales")
public class TotalMonthlySalesController {

  @Autowired
  TotalMonthlySalesService service;


  @ApiOperation("Endpoint responsável por mostrar novos usuários,total de vendas e valor dessas vendas.")
  @Tag(name = "Dashboard Administrador")
  @GetMapping
  @ResponseBody
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<TotalMonthySalesDTO> handle(){

    TotalMonthySalesDTO respon = service.execute();

    return new ResponseEntity<>(respon, HttpStatus.OK);
  }
}
