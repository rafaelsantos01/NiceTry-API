package com.core.rifei.modules.users.entityes;

import com.core.rifei.modules.users.ENUM.UserRole;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter

@Entity
@Table(name = "users")
public class Users implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", columnDefinition = "uuid")
  private UUID id;

  @Column(name = "name")
  private String name;

  @Column(name = "cpf")
  private String cpf;

  @Column(name = "email")
  private String login;

  @Column(name = "password")
  private String password;

  @Column(name = "permission")
  private UserRole permission;

  @Column(name = "trade_link")
  private String tradeLink;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "create_date")
  private Timestamp createDate;

  @Column(name = "status")
  private boolean status;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if(this.permission == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
    else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public String getUsername() {
    return login;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
