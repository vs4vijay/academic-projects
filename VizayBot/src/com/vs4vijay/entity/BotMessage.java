package com.vs4vijay.entity;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.xmpp.MessageType;

@PersistenceCapable
public class BotMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String email;

	@Persistent
	private String body;

	@Persistent
	private String stanza;

	@Persistent
	private MessageType messageType;

	@Persistent
	private Date date = new Date();

	public BotMessage() {
		super();
	}

	public BotMessage(String email, String body, String stanza,
			MessageType messageType) {
		super();
		this.email = email;
		this.body = body;
		this.stanza = stanza;
		this.messageType = messageType;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getStanza() {
		return stanza;
	}

	public void setStanza(String stanza) {
		this.stanza = stanza;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "BotMessage [key=" + key + ", email=" + email + ", body=" + body
				+ ", stanza=" + stanza + ", messageType=" + messageType
				+ ", date=" + date + "]";
	}
}
