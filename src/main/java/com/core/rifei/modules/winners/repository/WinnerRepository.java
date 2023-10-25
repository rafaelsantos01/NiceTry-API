package com.core.rifei.modules.winners.repository;

import com.core.rifei.modules.campaigns.entityes.Campaigns;
import com.core.rifei.modules.winners.entityes.Winner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WinnerRepository extends JpaRepository<Winner, UUID> {
  List<Winner> findByCampaigns(Campaigns campaigns);


}
