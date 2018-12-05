package pl.marcinmazur.portfolio.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "contact_form_messages")
public class ContactFormMessage {

	/*
	 * The Entity private fields
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "date")
	private Date date;

	@Column(name = "sender_name")
	private String senderName;

	@Column(name = "sender_email")
	private String senderEmail;

	@Column(name = "subject")
	private String messageSubject;

	@Column(name = "text")
	private String messageText;

	@Column(name = "is_readed")
	private boolean isReaded;

	@Column(name = "is_active")
	private boolean isActive;

	@Column(name = "is_replied")
	private boolean isReplied;

	@OneToMany(mappedBy = "contactFormMessage", cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.REFRESH })
	private List<Comment> commentsList;

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

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderEmail() {
		return senderEmail;
	}

	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	public String getMessageSubject() {
		return messageSubject;
	}

	public void setMessageSubject(String messageSubject) {
		this.messageSubject = messageSubject;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public boolean getIsReaded() {
		return isReaded;
	}

	public void setIsReaded(boolean isReaded) {
		this.isReaded = isReaded;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean getIsReplied() {
		return isReplied;
	}

	public void setIsReplied(boolean isReplied) {
		this.isReplied = isReplied;
	}

	public List<Comment> getCommentsList() {
		return commentsList;
	}

	public void setCommentsList(List<Comment> commentsList) {
		this.commentsList = commentsList;
	}

	/*
	 * Public methods
	 */

	public ContactFormMessage() {

	}

	public ContactFormMessage(Date date, String senderName, String senderEmail, String messageSubject,
			String messageText, boolean isReaded, boolean isActive, boolean isReplied, List<Comment> commentsList) {
		this.date = date;
		this.senderName = senderName;
		this.senderEmail = senderEmail;
		this.messageSubject = messageSubject;
		this.messageText = messageText;
		this.isReaded = isReaded;
		this.isActive = isActive;
		this.isReplied = isReplied;
		this.commentsList = commentsList;
	}

	@Override
	public String toString() {
		return "ContactFormMessage [id=" + id + ", date=" + date + ", senderName=" + senderName + ", senderEmail="
				+ senderEmail + ", messageSubject=" + messageSubject + ", messageText=" + messageText + ", isReaded="
				+ isReaded + ", isActive=" + isActive + ", isReplied=" + isReplied + ", commentsList=" + commentsList
				+ "]";
	}

}
