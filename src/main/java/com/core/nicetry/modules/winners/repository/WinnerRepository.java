package com.core.nicetry.modules.winners.repository;

import com.core.nicetry.modules.campaigns.entityes.Campaigns;
import com.core.nicetry.modules.order.entityes.Orders;
import com.core.nicetry.modules.users.entityes.Users;
import com.core.nicetry.modules.winners.entityes.Winner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WinnerRepository extends JpaRepository<Winner, UUID> {
  List<Winner> findByIdUserAndOrdersAndCampaigns(Users idUser, Orders orders, Campaigns campaigns);

  List<Winner> findByCampaigns(Campaigns campaigns);

  List<Winner> findByPaymentTrueAndCampaigns(Campaigns campaigns);


}
