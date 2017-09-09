package com.kkgame.feeop.adver.bean;
import com.kkgame.feeop.base.BasicVO; 

public class PromotionCustomerVO  extends BasicVO {
	 
	
	//columns START
    /**
     * id       db_column: id 
     */ 	
	private java.lang.Integer id;
    /**
     * name       db_column: name 
     */ 	
	private java.lang.String name;
    /**
     * cdate       db_column: cdate 
     */ 	
	private String cdate;
    /**
     * notes       db_column: notes 
     */ 	
	private java.lang.String notes;
	private java.lang.String email;
	private java.lang.String contact;
	//columns END


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	public void setCdate(String value) {
		this.cdate = value;
	}
	
	public String getCdate() {
		return this.cdate;
	}
	public void setNotes(java.lang.String value) {
		this.notes = value;
	}
	
	public java.lang.String getNotes() {
		return this.notes;
	}

	
	 
}
 
