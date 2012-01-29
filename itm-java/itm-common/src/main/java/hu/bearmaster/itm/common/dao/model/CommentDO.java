package hu.bearmaster.itm.common.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "comments")
public class CommentDO extends GenericDO<Long> {

   private static final long serialVersionUID = -3201547867025559326L;

   @JoinColumn(name = "user_id", nullable = false)
   @ManyToOne
   private UserDO user;

   @ManyToOne
   @JoinColumn(name = "post_id", nullable = false)
   private PostDO post;

   @Column(nullable = false)
   private String content;

   public CommentDO() {
   }

   public CommentDO(UserDO user, PostDO post, String content) {
      this.user = user;
      this.post = post;
      this.content = content;
   }

   public UserDO getUser() {
      return this.user;
   }

   public void setUser(UserDO user) {
      this.user = user;
   }

   public PostDO getPost() {
      return this.post;
   }

   public void setPost(PostDO post) {
      this.post = post;
   }

   public String getContent() {
      return this.content;
   }

   public void setContent(String content) {
      this.content = content;
   }
   
   @Override
   public String toString() {
      ToStringBuilder builder = new ToStringBuilder(this);
      builder.append("id", getId())
            .append("user", (user != null ? user.getId() : null))
            .append("post", (post != null ? post.getId() : null))
            .append("content", content);
      return builder.toString();
   }   
}