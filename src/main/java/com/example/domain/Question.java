package com.example.domain;

import java.util.Date;

public class Question {
	private String writer;
	private String title;
	private String contents;
	private Date date;
	
	public Question(){};
	
	public Question(String writer, String title, String contents, Date date) {
		super();
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.date = date;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
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
	 
	 
}
