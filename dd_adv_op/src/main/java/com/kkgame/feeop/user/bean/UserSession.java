package com.kkgame.feeop.user.bean;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.kkgame.feeop.base.PkigConstants;
import com.kkgame.feeop.util.CheckUtilities;

public class UserSession {

	private UserVO userVO;
	
	private Set<String> hasRoleList = new HashSet<String>();
	
	private Set<String> hasResList = new HashSet<String>();
	
	public void setPermission(String resName){
		hasResList.add(resName);
	}
	
	public void setRole(String roleName) {
		hasRoleList.add(roleName);
	}
	
	public boolean hasPermission(String resName){
		if(CheckUtilities.isEmptyString(resName)){
			return false;
		}
		Iterator<String> iter = hasResList.iterator();
		while(iter.hasNext()){
			if(iter.next().equals(resName.trim())){
				return true;
			}
		}
		return false;
	}
	
	public boolean hasRole(String roleName) {
		if(CheckUtilities.isEmptyString(roleName)){
			return false;
		}
		Iterator<String> iter = hasRoleList.iterator();
		while(iter.hasNext()){
			if(iter.next().equals(roleName.trim())){
				return true;
			}
		}
		return false;
	}
	
	public UserVO getUserVO() {
		return userVO;
	}

	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}

	public Set<String> getHasResList() {
		return hasResList;
	}

	public void setHasResList(Set<String> hasResList) {
		this.hasResList = hasResList;
	}

	public Set<String> getHasRoleList() {
		return hasRoleList;
	}

	public void setHasRoleList(Set<String> hasRoleList) {
		this.hasRoleList = hasRoleList;
	}
}
