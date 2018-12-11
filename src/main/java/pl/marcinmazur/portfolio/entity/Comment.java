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

/**
 * Represents a Comment record in the database
 * 
 * @author Marcin Mazur
 */
@Entity
@Table(name = "comments")
public class Comment {

	/**
	 * Unique identification number
	 */
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/**
	 * The date of added
	 */
	@Column(name = "date")
	private Date date;

	/**
	 * The text of the comment
	 */
	@Column(name = "text")
	private String commentText;

	/**
	 * The ContactFormMessage associated with the Comment
	 */
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "message_id")
	private ContactFormMessage contactFormMessage;

	/**
	 * Gets the unique identification number of the Comment
	 * 
	 * @return A long representing the unique identification number of the Comment
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the unique identification number of the Comment
	 * 
	 * @param id
	 *            A long containing the unique identification number of the Comment
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the date of added of the Comment
	 * 
	 * @return A Date representing the date of added of the Comment
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date of added of the Comment
	 * 
	 * @param date
	 *            A Date containing the date of added of the Comment
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Gets the text of the Comment
	 * 
	 * @return A String representing the text of the Comment
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 * Sets the text of the Comment
	 * 
	 * @param commentText
	 *            A String containing the text of the Comment
	 */
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	/**
	 * Gets the contact form message associated with the Comment
	 * 
	 * @return A ContactFormMessage associated the Comment
	 */
	public ContactFormMessage getContactFormMessage() {
		return contactFormMessage;
	}

	/**
	 * Sets the contactFormMessage associated with the Comment
	 * 
	 * @param contactFormMessage
	 *            A ContactFormMessage associated with the Comment
	 */
	public void setContactFormMessage(ContactFormMessage contactFormMessage) {
		this.contactFormMessage = contactFormMessage;
	}

	/**
	 * Constructs a Comment object.
	 */
	public Comment() {

	}

	/**
	 * Constructs a Comment with the date of added, the comment text and the
	 * ContactFormMessage object associated with the comment.
	 * 
	 * @param date
	 *            The date of added
	 * @param commentText
	 *            The text of comment
	 * @param contactFormMessage
	 *            The ContactFormMessage object associated with the comment
	 */
	public Comment(Date date, String commentText, ContactFormMessage contactFormMessage) {
		this.date = date;
		this.commentText = commentText;
		this.contactFormMessage = contactFormMessage;
	}

	/**
	 * Returns the String representation of the Comment object.
	 * 
	 * @return The String representation of the Comment object.
	 */
	@Override
	public String toString() {
		return "Comment [id=" + id + ", date=" + date + ", commentText=" + commentText + ", contactFormMessage="
				+ contactFormMessage + "]";
	}

}
