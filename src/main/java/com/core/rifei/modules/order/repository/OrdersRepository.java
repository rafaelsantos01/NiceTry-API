package com.core.rifei.modules.order.repository;

import com.core.rifei.modules.campaigns.entityes.Campaigns;
import com.core.rifei.modules.order.entityes.Orders;
import com.core.rifei.modules.users.entityes.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, UUID> {
  List<Orders> findByUsers(Users users);
  Orders findByIdTransaction(Long idTransaction);

  List<Orders> findByStatusPaymentIgnoreCase(String statusPayment);

  List<Orders> findAllByCampaigns(Campaigns campaign);

  List<Orders> findByCampaignsAndStatusPayment(Campaigns campaigns, String statusPayment);


  List<Orders> findTop5ByPaymentOrderByCheckoutDateDesc(boolean payment);

  List<Orders> findAllByPayment(boolean b);

  List<Orders> findByCheckoutDateBetween(Date dataInicioMes, Date dataFimMes);

}
