package com.core.rifei.modules.campaigns.repository;

import com.core.rifei.modules.campaigns.entityes.Campaigns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CampaignsRepository extends JpaRepository<Campaigns, UUID> {
    List<Campaigns> findByStatus(boolean active);
}

