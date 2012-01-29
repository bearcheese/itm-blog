package hu.bearmaster.itm.common.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "users")
public class UserDO extends GenericDO<Long> {
   
   private static final long serialVersionUID = -1124453248446942086L;

   @Column(nullable = false, unique = true)
   private String name;
   
   @Column(nullable = false, unique = true)
   private String email;
   
   @Column(name = "hashed_password")
   private String hashedPassword;
   
   private String salt;
   
   private boolean admin;
   
   //...
   
   public UserDO() {
   }

   public UserDO(String name, String email, String hashedPassword, String salt, boolean admin) {
      this.name = name;
      this.email = email;
      this.hashedPassword = hashedPassword;
      this.salt = salt;
      this.admin = admin;
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

   public String getHashedPassword() {
      return this.hashedPassword;
   }

   public void setHashedPassword(String hashedPassword) {
      this.hashedPassword = hashedPassword;
   }

   public String getSalt() {
      return this.salt;
   }

   public void setSalt(String salt) {
      this.salt = salt;
   }
   
   public boolean isAdmin() {
      return admin;
   }

   public void setAdmin(boolean admin) {
      this.admin = admin;
   }

   @Override
   public String toString() {
      ToStringBuilder builder = new ToStringBuilder(this);
      builder.append("id", getId()).append("name", name)
             .append("email", email).append("admin", admin);
      return builder.toString();
   }
   
}