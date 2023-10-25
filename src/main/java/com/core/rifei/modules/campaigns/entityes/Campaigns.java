package com.core.rifei.modules.campaigns.entityes;


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
@Table(name = "campaigns")
public class Campaigns  extends DateBase {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", columnDefinition = "uuid")
  private UUID id;

  @Column(name = "url_image")
  private String urlImage;

  @Column(name = "name")
  private String name;

  @Column(name = "type")
  private String type;

  @Column(name = "description")
  private String description;

  @Column(name = "label")
  private String label;

  @Column(name = "max_numbers")
  private int maxNumbers;

  @Column(name = "product_value")
  private BigDecimal productValue;

  @Column(name = "number_value")
  private BigDecimal numberValue;

  @Column(name = "status")
  private boolean status;
}
