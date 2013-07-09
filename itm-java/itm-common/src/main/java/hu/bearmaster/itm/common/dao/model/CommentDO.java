package hu.bearmaster.itm.common.dao.model;

import hu.bearmaster.itm.common.model.CommentVO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "comments")
public class CommentDO extends GenericDO<Long, CommentVO> {

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

   public CommentDO(final UserDO user, final PostDO post, final String content) {
      this.user = user;
      this.post = post;
      this.content = content;
   }

   public UserDO getUser() {
      return this.user;
   }

   public void setUser(final UserDO user) {
      this.user = user;
   }

   public PostDO getPost() {
      return this.post;
   }

   public void setPost(final PostDO post) {
      this.post = post;
   }

   public String getContent() {
      return this.content;
   }

   public void setContent(final String content) {
      this.content = content;
   }
   
   @Override
   public String toString() {
      ToStringBuilder builder = new ToStringBuilder(this);
      builder.append("id", getId())
            .append("user", user)
            .append("post", post)
            .append("content", content);
      return builder.toString();
   }

   @Override
   public CommentVO getVo() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Not implemented yet!");
   }

   @Override
   public void setVo(final CommentVO vo) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Not implemented yet!");
   }
}