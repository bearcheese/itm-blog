package hu.bearmaster.itm.common.model;

import hu.bearmaster.itm.common.validators.PasswordComplexity;
import hu.bearmaster.itm.common.validators.PasswordMatches;

//import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Represents the value object of a user.
 *
 */
@PasswordMatches
public class UserVO extends GenericVO<Long> {
   public static final int MINIMUM_USER_NAME_LENGTH = 3;
   public static final int MAXIMUM_USER_NAME_LENGTH = 50;
   
   @NotBlank
   @Size(min = MINIMUM_USER_NAME_LENGTH, max = MAXIMUM_USER_NAME_LENGTH)
   private String name;
   
   @NotBlank
   @Email
   private String email;
      
   @NotBlank
   //@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=[^\\s]+$).{5,}$") //Source http://stackoverflow.com/questions/3802192/regexp-java-for-password-validation
   @PasswordComplexity
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

   public void setAdmin(final boolean admin) {
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