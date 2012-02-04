package hu.bearmaster.itm.common.validators;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class TimestampUpdateListener {
   
   @PrePersist
   public void setTimestampAtPersist(TimestampedEntity entity) {
      Date now = new Date();
      
      entity.setCreatedAt(now);
      entity.setUpdatedAt(now);
   }
   
   @PreUpdate
   public void setTimestampAtUpdate(TimestampedEntity entity) {
      Date now = new Date();
      
      entity.setUpdatedAt(now);
   }

}