package com.core.nicetry.modules.winners.entityes;


import com.core.nicetry.modules.campaigns.entityes.Campaigns;
import com.core.nicetry.modules.order.entityes.Orders;
import com.core.nicetry.modules.users.entityes.Users;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter

@Entity
@Table(name = "winners")
public class Winner {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", columnDefinition = "uuid")
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_user")
  private Users idUser;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_order")
  private Orders orders;

  @Column(name = "draw_date")
  private Timestamp drawDate;

  @Column(name = "payment")
  private boolean payment;

  @Column(name = "number")
  private int number;

  @Column(name = "winner")
  private boolean winner;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_campaign")
  private Campaigns campaigns;
}
