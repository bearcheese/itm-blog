package hu.bearmaster.itm.common.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserVO extends GenericVO<Long> {
   
   private String name;
   
   private String email;
      
   private String password;
   
   private String passwordConfirmation;
   
   private boolean admin;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public boolean isAdmin() {
      return admin;
   }

   public void setAdmin(boolean admin) {
      this.admin = admin;
   }

   public void setPasswordConfirmation(String passwordConfirmation) {
      this.passwordConfirmation = passwordConfirmation;
   }

   public String getPasswordConfirmation() {
      return passwordConfirmation;
   }

   public String toString() {
      return new ToStringBuilder(this).append("id", getId()).append("name", name).append("email", email).append("admin", admin).toString();
   }

}