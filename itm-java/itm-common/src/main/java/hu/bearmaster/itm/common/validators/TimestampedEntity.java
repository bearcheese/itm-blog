package hu.bearmaster.itm.common.validators;

import java.util.Date;

public interface TimestampedEntity {

   Date getCreatedAt();

   void setCreatedAt(Date createdAt);

   Date getUpdatedAt();

   void setUpdatedAt(Date updatedAt);
   
}