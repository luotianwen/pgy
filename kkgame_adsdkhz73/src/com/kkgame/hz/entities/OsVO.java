package com.kkgame.hz.entities;

import com.kkgame.hz.base.BaseVO;
/**
 * 
 * @author Hong 平台
 */
public class OsVO extends BaseVO {

	private int id;// 平台序号

	private String name;// 平台名称

	private String intro;// 平台描述
	
	private int isPage;

	public int getIsPage() {
		return isPage;
	}

	public void setIsPage(int isPage) {
		this.isPage = isPage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}
}
