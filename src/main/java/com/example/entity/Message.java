package com.example.entity;

import javax.persistence.*;

// Marks class as entity for spring and specifies table mapping
@Entity
@Table(name="message")
public class Message {
    // Maps messageId to the messageId column in the Message table and as a generated value primary key
    @Column (name="messageId")
    @Id @GeneratedValue
    private Integer messageId;

    // Maps postedBy to the postedBy column in the Message table
    @Column (name="postedBy")
    private Integer postedBy;
  
    // Maps messageText to the messageText column in the Message table
    @Column (name="messageText")
    private String messageText;
   
    // Maps timePostedEpoch to the timePostedEpoch column in the Message table
    @Column (name="timePostedEpoch")
    private Long timePostedEpoch;
  
    // Default Constructor
    public Message() {}

    // Copy Constructor
    public Message(Integer postedBy, String messageText, Long timePostedEpoch) {
        this.postedBy = postedBy;
        this.messageText = messageText;
        this.timePostedEpoch = timePostedEpoch;
    }
    
    // Copy Constructor with messageID
    public Message(Integer messageId, Integer postedBy, String messageText, Long timePostedEpoch) {
        this.messageId = messageId;
        this.postedBy = postedBy;
        this.messageText = messageText;
        this.timePostedEpoch = timePostedEpoch;
    }
    
    // Properly named messageID getter
    public Integer getMessageId() {
        return messageId;
    }
    
    // Properly named messageID setter
    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }
    
    // Properly named postedBy getter
    public Integer getPostedBy() {
        return postedBy;
    }

    // Properly named postedBy setter
    public void setPostedBy(Integer postedBy) {
        this.postedBy = postedBy;
    }
    
    // Properly named messageText getter
    public String getMessageText() {
        return messageText;
    }
    
    // Properly named messageText setter
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
    
    // Properly named timePostedEpoch getter
    public Long getTimePostedEpoch() {
        return timePostedEpoch;
    }
    
    // Properly named timePostedEpoch setter
    public void setTimePostedEpoch(Long timePostedEpoch) {
        this.timePostedEpoch = timePostedEpoch;
    }
    
    // equals override method
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (messageId == null) {
			if (other.messageId != null)
				return false;
		} else if (!messageId.equals(other.messageId))
			return false;
		if (messageText == null) {
			if (other.messageText != null)
				return false;
		} else if (!messageText.equals(other.messageText))
			return false;
		if (postedBy == null) {
			if (other.postedBy != null)
				return false;
		} else if (!postedBy.equals(other.postedBy))
			return false;
		if (timePostedEpoch == null) {
			if (other.timePostedEpoch != null)
				return false;
		} else if (!timePostedEpoch.equals(other.timePostedEpoch))
			return false;
		return true;
	}
	
    // toString override method
    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", postedBy=" + postedBy +
                ", messageText='" + messageText + '\'' +
                ", timePostedEpoch=" + timePostedEpoch +
                '}';
    }


}
