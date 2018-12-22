package com.cssl.vo;

import java.util.List;

public class Tree {

	private int id;
	private String text;
	private String state;	
	
	private List<Tree> children;
	
	public Tree() {
		super();
	}

	public Tree(int id, String text, List<Tree> children) {
		super();
		this.id = id;
		this.text = text;
		this.children = children;
	}

	public Tree(int id, String text) {
		super();
		this.id = id;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Tree> getChildren() {
		return children;
	}

	public void setChildren(List<Tree> children) {
		this.children = children;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
