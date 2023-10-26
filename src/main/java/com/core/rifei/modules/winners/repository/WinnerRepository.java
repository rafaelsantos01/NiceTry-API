package com.core.rifei.modules.winners.repository;

import com.core.rifei.modules.campaigns.entityes.Campaigns;
import com.core.rifei.modules.order.entityes.Orders;
import com.core.rifei.modules.users.entityes.Users;
import com.core.rifei.modules.winners.entityes.Winner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface WinnerRepository extends JpaRepository<Winner, UUID> {
  List<Winner> findByIdUserAndOrdersAndCampaigns(Users idUser, Orders orders, Campaigns campaigns);

  List<Winner> findByCampaigns(Campaigns campaigns);

  List<Winner> findByPaymentTrueAndCampaigns(Campaigns campaigns);


}
