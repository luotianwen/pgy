package com.kkgame.feeop.customer.bean;

import java.io.File;

public class ProjectFileVO {

	private int id;
	
	private String name;
	
	private int projectId;
	
	private String info;
	
	private String createTime;
	
	private int times;
	
	private String lastDownTime;

	private File projectFile;
	
	private String projectFileFileName;
	
	private String filePath;
	
	private String projectFileContentType;
	
	private int uploadId;

	public int getUploadId() {
		return uploadId;
	}

	public void setUploadId(int uploadId) {
		this.uploadId = uploadId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getProjectFileContentType() {
		return projectFileContentType;
	}

	public void setProjectFileContentType(String projectFileContentType) {
		this.projectFileContentType = projectFileContentType;
	}

	public File getProjectFile() {
		return projectFile;
	}

	public void setProjectFile(File projectFile) {
		this.projectFile = projectFile;
	}


	public String getProjectFileFileName() {
		return projectFileFileName;
	}

	public void setProjectFileFileName(String projectFileFileName) {
		this.projectFileFileName = projectFileFileName;
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

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public String getLastDownTime() {
		return lastDownTime;
	}

	public void setLastDownTime(String lastDownTime) {
		this.lastDownTime = lastDownTime;
	}
}
