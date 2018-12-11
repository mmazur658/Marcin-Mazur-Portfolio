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

/**
 * Represents a ContactFormMessage record in the database
 * 
 * @author Marcin Mazur
 */
@Entity
@Table(name = "contact_form_messages")
public class ContactFormMessage {

	/**
	 * Unique identification number
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	/**
	 * The date of added
	 */
	@Column(name = "date")
	private Date date;

	/**
	 * The name of the sender
	 */
	@Column(name = "sender_name")
	private String senderName;

	/**
	 * The email od the sender 
	 */
	@Column(name = "sender_email")
	private String senderEmail;

	/**
	 * The subject of the message 
	 */
	@Column(name = "subject")
	private String messageSubject;

	/**
	 * The the text of the message 
	 */
	@Column(name = "text")
	private String messageText;

	/**
	 * The isRead status
	 */
	@Column(name = "is_readed")
	private boolean isReaded;

	/**
	 * The isActive status
	 */
	@Column(name = "is_active")
	private boolean isActive;

	/**
	 * The isReplied status
	 */
	@Column(name = "is_replied")
	private boolean isReplied;

	/**
	 * The list of comments
	 */
	@OneToMany(mappedBy = "contactFormMessage", cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.REFRESH })
	private List<Comment> commentsList;

	/**
	 * Gets the unique identification number of the ContactFormMessage
	 * 
	 * @return A long representing the unique identification number of the
	 *         ContactFormMessage
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the unique identification number of the ContactFormMessage
	 * 
	 * @param id
	 *            A long containing the unique identification number of the
	 *            ContactFormMessage
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the date of added of the ContactFormMessage
	 * 
	 * @return A Date representing the date of added of the ContactFormMessage
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date of added of the ContactFormMessage
	 * 
	 * @param date
	 *            A Date containing the date of added of the ContactFormMessage
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Gets the sender name of the ContactFormMessage
	 * 
	 * @return A String representing the sender name of the ContactFormMessage
	 */
	public String getSenderName() {
		return senderName;
	}

	/**
	 * Sets the sender name of the ContactFormMessage
	 * 
	 * @param senderName
	 *            A String containing the sender name of the ContactFormMessage
	 */
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	/**
	 * Gets the sender email of the ContactFormMessage
	 * 
	 * @return A String representing the sender email of the ContactFormMessage
	 */
	public String getSenderEmail() {
		return senderEmail;
	}

	/**
	 * Sets the sender email of the ContactFormMessage
	 * 
	 * @param senderEmail
	 *            A String containing the sender email of the ContactFormMessage
	 */
	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	/**
	 * Gets the message subject of the ContactFormMessage
	 * 
	 * @return A String representing the message subject of the ContactFormMessage
	 */
	public String getMessageSubject() {
		return messageSubject;
	}

	/**
	 * Sets the message subject of the ContactFormMessage
	 * 
	 * @param messageSubject
	 *            A String containing the message subject of the ContactFormMessage
	 */
	public void setMessageSubject(String messageSubject) {
		this.messageSubject = messageSubject;
	}

	/**
	 * Gets the message text of the ContactFormMessage
	 * 
	 * @return A String representing the message text of the ContactFormMessage
	 */
	public String getMessageText() {
		return messageText;
	}

	/**
	 * Sets the message text of the ContactFormMessage
	 * 
	 * @param messageText
	 *            A String containing the message text of the ContactFormMessage
	 */
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	/**
	 * Gets the isReaded status of the ContactFormMessage
	 * 
	 * @return A boolean representing the isReaded status of the ContactFormMessage
	 */
	public boolean getIsReaded() {
		return isReaded;
	}

	/**
	 * Sets the isReaded status of the ContactFormMessage
	 * 
	 * @param isReaded
	 *            A boolean containing the isReaded status of the
	 *            ContactFormMessage
	 */
	public void setIsReaded(boolean isReaded) {
		this.isReaded = isReaded;
	}

	/**
	 * Gets the isActive status of the ContactFormMessage
	 * 
	 * @return A boolean representing the isActive status of the ContactFormMessage
	 */
	public boolean getIsActive() {
		return isActive;
	}

	/**
	 * Sets the isActive status of the ContactFormMessage
	 * 
	 * @param isActive
	 *            A boolean containing the isActive status of the
	 *            ContactFormMessage
	 */
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * Gets the isReplied status of the ContactFormMessage
	 * 
	 * @return A boolean representing the isReplied status of the ContactFormMessage
	 */
	public boolean getIsReplied() {
		return isReplied;
	}

	/**
	 * Sets the isReplied status of the ContactFormMessage
	 * 
	 * @param isReplied
	 *            A boolean containing the isReplied status of the
	 *            ContactFormMessage
	 */
	public void setIsReplied(boolean isReplied) {
		this.isReplied = isReplied;
	}

	/**
	 * Gets the ContactFormMessage`s list of the comments
	 * 
	 * @return A List of Comment
	 */
	public List<Comment> getCommentsList() {
		return commentsList;
	}

	/**
	 * Sets the ContactFormMessage`s list of the comments
	 * 
	 * @param commentsList
	 *            A ContactFormMessage`s list of the comments
	 */
	public void setCommentsList(List<Comment> commentsList) {
		this.commentsList = commentsList;
	}

	/**
	 * Constructs a ContactFormMessage object.
	 */
	public ContactFormMessage() {

	}

	/**
	 * Constructs a ContactFormMessage with the date of added, the sedner name and
	 * email, the message text and subject, the isReaded, isActive and isReplied
	 * status and with the list of the comments.
	 * 
	 * @param date
	 *            The date of the added
	 * @param senderName
	 *            The name of the sender
	 * @param senderEmail
	 *            The email of the sender
	 * @param messageSubject
	 *            The subject of the message
	 * @param messageText
	 *            The text of the message
	 * @param isReaded
	 *            The isRead status of the message
	 * @param isActive
	 *            The isActive status of the message
	 * @param isReplied
	 *            The isReplied status of the message
	 * @param commentsList
	 *            The comment list of the message\
	 */
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

	/**
	 * Returns the String representation of the ContactFormMessage object.
	 * 
	 * @return The String representation of the ContactFormMessage object.
	 */
	@Override
	public String toString() {
		return "ContactFormMessage [id=" + id + ", date=" + date + ", senderName=" + senderName + ", senderEmail="
				+ senderEmail + ", messageSubject=" + messageSubject + ", messageText=" + messageText + ", isReaded="
				+ isReaded + ", isActive=" + isActive + ", isReplied=" + isReplied + ", commentsList=" + commentsList
				+ "]";
	}

}
