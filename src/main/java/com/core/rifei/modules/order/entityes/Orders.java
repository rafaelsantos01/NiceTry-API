package com.core.rifei.modules.order.entityes;


import com.core.rifei.modules.campaigns.entityes.Campaigns;
import com.core.rifei.modules.users.entityes.Users;
import com.core.rifei.shared.entities.DateBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter

@Entity
@Table(name = "orders")
public class Orders extends DateBase {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", columnDefinition = "uuid")
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_user")
  private Users users;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_campaign")
  private Campaigns campaigns;

  @Column(name = "numbers")
  private int numbers;

  @Column(name = "checkout_date")
  private Timestamp checkoutDate;

  @Column(name = "payment")
  private boolean payment;

  @Column(name = "id_transaction")
  private Long idTransaction;

  @Column(name = "qrcode")
  private String QRCode;

  @Column(name = "ticket_url")
  private String ticketUrl;

  @Column(name = "status_payment")
  private String statusPayment;

  @Column(name = "transaction_amount")
  private BigDecimal transactionAmount;
}
