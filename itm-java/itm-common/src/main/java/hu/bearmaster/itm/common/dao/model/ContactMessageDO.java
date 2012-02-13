package hu.bearmaster.itm.common.dao.model;

import hu.bearmaster.itm.common.model.ContactMessagesVO;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "contact_messages")
public class ContactMessageDO extends GenericDO<Long, ContactMessagesVO> {

   private static final long serialVersionUID = 3514559176559223816L;

   private String name;
   private String email;
   private String subject;
   private String message;
   private boolean checked;

   public ContactMessageDO() {
   }

   public ContactMessageDO(String name, String email, String subject,
         String message, boolean checked, Date createdAt, Date updatedAt) {
      this.name = name;
      this.email = email;
      this.subject = subject;
      this.message = message;
      this.checked = checked;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getSubject() {
      return this.subject;
   }

   public void setSubject(String subject) {
      this.subject = subject;
   }

   public String getMessage() {
      return this.message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public boolean isChecked() {
      return checked;
   }

   public void setChecked(boolean checked) {
      this.checked = checked;
   }

   @Override
   public String toString() {
      ToStringBuilder builder = new ToStringBuilder(this);
      builder.append("id", getId()).append("name", name).append("email", email)
            .append("subject", subject).append("message", message)
            .append("checked", checked);
      return builder.toString();
   }

   @Override
   public ContactMessagesVO getVo() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Not implemented yet!");
   }

   @Override
   public void setVo(ContactMessagesVO vo) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Not implemented yet!");
   }
}