package com.example.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity	
public class Question {
	
	@Id @GeneratedValue //고유한 아이디
	private long id;
	
	@ManyToOne
	private User writer;
	
//	@OneToMany(mappedBy="question")
//	private Answer answer;
	
//	@Column(length = 15, nullable = false)
//	private String writer;
	
	@Column(length = 15, nullable = false)
	private String title;
	
	@Column(length = 100, nullable = false)
	private String contents;
	
	@Column(length = 15, nullable = false)
	private Date date;
	
	public Question(){
		this.date =  new Date();
	};
	
	public Question(long id, User writer, String title, String contents) {
		super();
		this.id = id;
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.date =  new Date();
		//this.answer = answer; , Answer answer
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String content) {
		this.contents = content;
	}
	 
	public void update(Question question){
			this.contents = question.contents;
			this.title = question.title;
	}
	 
}
