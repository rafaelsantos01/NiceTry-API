package com.core.nicetry.modules.dashboard.services.totalMonthlySales;

import com.core.nicetry.modules.order.entityes.Orders;
import com.core.nicetry.modules.order.repository.OrdersRepository;
import com.core.nicetry.modules.dashboard.services.totalMonthlySales.dto.TotalMonthySalesDTO;
import com.core.nicetry.modules.users.entityes.Users;
import com.core.nicetry.modules.users.repository.UsersRepository;
import com.core.nicetry.shared.mercadoPago.ENUM.STATUSPAYMENTMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class TotalMonthlySalesService {

  @Autowired
  OrdersRepository ticketsRepository;


  @Autowired
  UsersRepository usersRepository;


  public TotalMonthySalesDTO execute() {
   TotalMonthySalesDTO response = new TotalMonthySalesDTO();

    LocalDate dataAtual = LocalDate.now();

    LocalDate primeiroDiaDoMes = dataAtual.withDayOfMonth(1);
    LocalDate ultimoDiaDoMes = dataAtual.withDayOfMonth(dataAtual.lengthOfMonth());

    Date dataInicioMes = Date.from(primeiroDiaDoMes.atStartOfDay(ZoneId.systemDefault()).toInstant());
    Date dataFimMes = Date.from(ultimoDiaDoMes.atStartOfDay(ZoneId.systemDefault()).toInstant());

    List<Orders> registrosDoMesAtual = ticketsRepository.findByCheckoutDateBetween(dataInicioMes,dataFimMes);
    List<Users> totalUsersMonth = usersRepository.findByCreateDateBetween(dataInicioMes, dataFimMes);

    BigDecimal totalValues = BigDecimal.valueOf(0);
    BigDecimal totalValuesConfirmed = BigDecimal.valueOf(0);
    for(Orders tickets: registrosDoMesAtual){
      response.setTotalSales(registrosDoMesAtual.size());
      totalValues = totalValues.add(tickets.getCampaigns().getNumberValue().multiply(new BigDecimal(tickets.getNumbers())));
      if(tickets.getStatusPayment().equals(STATUSPAYMENTMP.APPROVED.getKey())){
        totalValuesConfirmed = totalValuesConfirmed.add(tickets.getCampaigns().getNumberValue().multiply(new BigDecimal(tickets.getNumbers())));
      }
    }
    response.setTotalValueSalesConfirmed(totalValuesConfirmed);
    response.setNewUsers(totalUsersMonth.size());
    response.setTotalValueSales(totalValues);
    return  response;
  }
}
