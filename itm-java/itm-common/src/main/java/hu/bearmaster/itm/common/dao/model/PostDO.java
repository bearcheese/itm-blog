package hu.bearmaster.itm.common.dao.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "posts")
public class PostDO extends GenericDO<Long> {

   private static final long serialVersionUID = -1302251252265949756L;

   @Column(nullable = false)
   private String title;

   @Lob
   @Column(name = "summary", nullable = false)
   private String summary;

   @Lob
   @Column(name = "content", nullable = false)
   private String content;

   private boolean published;

   @Column(name = "publish_date")
   @Temporal(TemporalType.TIMESTAMP)
   private Date publishDate;

   private String slug;

   @Column(name = "comments_allowed")
   private boolean commentsAllowed;

   @JoinColumn(name = "author_id", nullable = false)
   @ManyToOne
   private UserDO author;

   private String category;

   @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
   private List<CommentDO> comments;

   public PostDO() {
   }

   public PostDO(String title, String summary, String content,
         boolean published, Date publishDate, String slug,
         boolean commentsAllowed, UserDO author, String category) {
      this.title = title;
      this.summary = summary;
      this.content = content;
      this.published = published;
      this.publishDate = publishDate;
      this.slug = slug;
      this.commentsAllowed = commentsAllowed;
      this.author = author;
      this.category = category;
   }

   public String getTitle() {
      return this.title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getSummary() {
      return this.summary;
   }

   public void setSummary(String summary) {
      this.summary = summary;
   }

   public String getContent() {
      return this.content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public boolean getPublished() {
      return this.published;
   }

   public void setPublished(boolean published) {
      this.published = published;
   }

   public Date getPublishDate() {
      return this.publishDate;
   }

   public void setPublishDate(Date publishDate) {
      this.publishDate = publishDate;
   }

   public String getSlug() {
      return this.slug;
   }

   public void setSlug(String slug) {
      this.slug = slug;
   }

   public boolean getCommentsAllowed() {
      return this.commentsAllowed;
   }

   public void setCommentsAllowed(boolean commentsAllowed) {
      this.commentsAllowed = commentsAllowed;
   }

   public UserDO getAuthor() {
      return this.author;
   }

   public void setAuthor(UserDO author) {
      this.author = author;
   }

   public String getCategory() {
      return this.category;
   }

   public void setCategory(String category) {
      this.category = category;
   }

   public List<CommentDO> getComments() {
      return comments;
   }

   public void setComments(List<CommentDO> comments) {
      this.comments = comments;
   }

   @Override
   public String toString() {
      ToStringBuilder builder = new ToStringBuilder(this);
      builder.append("id", getId()).append("title", title)
            .append("published", published).append("publishDate", publishDate)
            .append("slug", slug)
            .append("authorId", (author != null ? author.getId() : null))
            .append("category", category);
      return builder.toString();
   }
}