package hu.bearmaster.itm.common.dao.model;

import hu.bearmaster.itm.common.model.UserVO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Database entity representing the users.
 *
 */
@Entity
@Table(name = "users")
@NamedQueries({
   @NamedQuery(name = "findByUsername", query = "select u from UserDO u where u.name = :name")
})
public class UserDO extends GenericDO<Long, UserVO> {
   
   private static final long serialVersionUID = -1124453248446942086L;

   @Column(nullable = false, unique = true)
   private String name;
   
   @Column(nullable = false, unique = true)
   private String email;
   
   @Column(name = "hashed_password")
   private String hashedPassword;
   
   private String salt;
   
   private boolean admin;
   
   /**
    * Default constructor.
    */
   public UserDO() {
   }

   public UserDO(final String name, final String email, final String hashedPassword, final String salt, final boolean admin) {
      this.name = name;
      this.email = email;
      this.hashedPassword = hashedPassword;
      this.salt = salt;
      this.admin = admin;
   }

   public String getName() {
      return this.name;
   }

   public void setName(final String name) {
      this.name = name;
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(final String email) {
      this.email = email;
   }

   public String getHashedPassword() {
      return this.hashedPassword;
   }

   public void setHashedPassword(final String hashedPassword) {
      this.hashedPassword = hashedPassword;
   }

   public String getSalt() {
      return this.salt;
   }

   public void setSalt(final String salt) {
      this.salt = salt;
   }
   
   public boolean isAdmin() {
      return admin;
   }

   public void setAdmin(final boolean admin) {
      this.admin = admin;
   }

   @Override
   public String toString() {
      ToStringBuilder builder = new ToStringBuilder(this);
      builder.append("id", getId()).append("name", name)
             .append("email", email).append("admin", admin);
      return builder.toString();
   }

   @Override
   public UserVO getVo() {
      UserVO vo = new UserVO();
      vo.setId(this.getId());
      vo.setName(this.name);
      vo.setEmail(this.getEmail());
      vo.setAdmin(this.admin);
      return vo;
   }

   @Override
   public void setVo(final UserVO vo) {
      this.setId(vo.getId());
      this.setName(vo.getName());
      this.setEmail(vo.getEmail());
      this.setAdmin(vo.isAdmin());
   }
   
}