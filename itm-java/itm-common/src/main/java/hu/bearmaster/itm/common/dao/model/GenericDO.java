package hu.bearmaster.itm.common.dao.model;

import hu.bearmaster.itm.common.validators.TimestampUpdateListener;
import hu.bearmaster.itm.common.validators.TimestampedEntity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
@EntityListeners({TimestampUpdateListener.class})
public abstract class GenericDO<ID, VO> implements java.io.Serializable, TimestampedEntity {
   
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
   
   public abstract VO getVo();
   
   public abstract void setVo(VO vo);

}