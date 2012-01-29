package hu.bearmaster.itm.common.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class GenericDO<ID> implements java.io.Serializable {
   
   private static final long serialVersionUID = -7709984498689739891L;
   
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   private ID id;

   @Column(name = "created_at")
   @Temporal(TemporalType.TIMESTAMP)
   private Date createdAt;
   
   @Column(name = "updated_at")
   @Temporal(TemporalType.TIMESTAMP)
   private Date updatedAt;
   
   //Getters, setters
   
   public ID getId() {
      return this.id;
   }

   public void setId(ID id) {
      this.id = id;
   }
   
   public Date getCreatedAt() {
      return this.createdAt;
   }

   public void setCreatedAt(Date createdAt) {
      this.createdAt = createdAt;
   }

   public Date getUpdatedAt() {
      return this.updatedAt;
   }

   public void setUpdatedAt(Date updatedAt) {
      this.updatedAt = updatedAt;
   }

}