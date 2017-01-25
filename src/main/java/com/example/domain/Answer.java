package com.example.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;

@Entity	
public class Answer {
	
	@Id @GeneratedValue //고유한 아이디
	private long id;
	
	@ManyToOne
	private User writer;
	
	@ManyToOne
	private Question question;
	
	@Column(length = 100)
	private String contents;
	
	@Column(length = 15, nullable = false)
	private Date date;
	
	public Answer(){
		this.date =  new Date();
	};
	
	public Answer(long id, User writer, Question question, String contents) {
		super();
		this.id = id;
		this.writer = writer;
		this.question = question;
		this.contents = contents;
		this.date =  new Date();
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getWriter() {
		return writer;
	}
	public void setWriter(User writer) {
		this.writer = writer; 
	}

	public String getContents() {
		return contents;
	}
	public void setContents(String content) {
		this.contents = content;
	}
	 
	public void update(Answer answer){
			this.contents = answer.contents;
	}
	 
}
