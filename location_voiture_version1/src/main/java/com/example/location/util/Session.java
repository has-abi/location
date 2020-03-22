package com.example.location.util;

import java.util.ArrayList;

import com.example.location.bean.User;
import com.example.location.bean.Voiture;

public class Session{
	
	public static ArrayList<SessionDetail> sessionDetails = new ArrayList<SessionDetail>() ;
		
	public static Object setSession(Object obj , String name) {
		SessionDetail sessionDetail = new SessionDetail();
		sessionDetail.setObjectToStore(obj);
		sessionDetail.setKey(name);
		sessionDetails.add(sessionDetail);
		return sessionDetails;
	}
	
	public static Object getSession(String name) {
		for(SessionDetail sd : sessionDetails) {
			if(sd.getKey().equals(name)) return sd.getObjectToStore();
		}
		return null;
	}
	
	public static void SetSessionVoiture(Voiture voiture) {
		setSession(voiture, "voiture");
	}
	
	public static Voiture getSessionVoiture(String name) {
		return (Voiture) getSession(name);
	}
	
	public static void setSessionUser(User user,String type) {
		setSession(user,type);
	}
	
	public static User getSessionUser(String type) {
		return (User) getSession(type);
	}
	public static Object removeSessionVoiture() {
		for(SessionDetail st : sessionDetails) {
			if(st.getKey().equals("voiture")) {
				sessionDetails.remove(st);
				return sessionDetails;
			}
		}
		return null;
	}
	
	public static void clear() {
		sessionDetails.clear();
	}
	
}
