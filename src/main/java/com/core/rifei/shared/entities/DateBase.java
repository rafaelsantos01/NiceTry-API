package com.core.rifei.shared.entities;


import com.core.rifei.modules.users.entityes.Users;
import com.core.rifei.security.context.SetUserService;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@MappedSuperclass
public class DateBase {

  @Column(name = "created_at")
  @CreationTimestamp()
  private Timestamp createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp()
  private Timestamp updatedAt;

  @ManyToOne
  @JoinColumn(name = "user_updated")
  private Users userUpdated;

  @ManyToOne
  @JoinColumn(name = "user_created")
  private Users userCreated;


  @PrePersist
  private void beforePersist(){
    SetUserService setUserService = new SetUserService();

    if(this.userCreated == null || this.userUpdated == null){
      Users users = setUserService.execute();
      this.userCreated = users;
      this.userUpdated = users;
    }
  }


  @PreUpdate
  private void beforeUpdate(){

    SetUserService setUserService = new SetUserService();

    if( this.userUpdated == null){
      Users users = setUserService.execute();
      this.userUpdated = users;
    }


  }

}
