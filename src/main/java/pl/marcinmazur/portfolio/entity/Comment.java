package pl.marcinmazur.portfolio.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {

	/*
	 * The Entity private fields
	 */

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "date")
	private Date date;

	@Column(name = "text")
	private String commentText;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "message_id")
	private ContactFormMessage contactFormMessage;

	/*
	 * Setters and getters methods
	 */

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public ContactFormMessage getContactFormMessage() {
		return contactFormMessage;
	}

	public void setContactFormMessage(ContactFormMessage contactFormMessage) {
		this.contactFormMessage = contactFormMessage;
	}

	/*
	 * Public methods
	 */

	public Comment() {

	}

	public Comment(Date date, String commentText, ContactFormMessage contactFormMessage) {
		this.date = date;
		this.commentText = commentText;
		this.contactFormMessage = contactFormMessage;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", date=" + date + ", commentText=" + commentText + ", contactFormMessage="
				+ contactFormMessage + "]";
	}

}
