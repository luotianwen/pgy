package com.kkgame.hz.entities;

/**
 * @author Hong
 * 
 * 客户端屏幕分辨率VO
 * 
 */
public class ScreenVO {

	private int id;

	private int width;// 宽

	private int height;// 高

	private String name;// 名字

	private String alias;

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
