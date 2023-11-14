package com.core.nicetry.shared.entities;


import com.core.nicetry.modules.users.entityes.Users;
import com.core.nicetry.security.context.GetUserService;
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
    GetUserService setUserService = new GetUserService();

    if(this.userCreated == null || this.userUpdated == null){
      Users users = setUserService.execute();
      this.userCreated = users;
      this.userUpdated = users;
    }
  }


  @PreUpdate
  private void beforeUpdate(){

    GetUserService setUserService = new GetUserService();

    if( this.userUpdated == null){
      Users users = setUserService.execute();
      this.userUpdated = users;
    }


  }

}
