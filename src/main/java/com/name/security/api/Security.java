package com.name.security.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.name.security.service.SecurityService;

@Component
public class Security {

	@Autowired
	SecurityService SecurityService;
	
	public boolean hasAccess(String name, String userName) {

		if(hasRESTAccess(name, userName)) {
			return true;
		}
		else if(hasNavAccess()) {
			return true;
		}
		
		return false;
	}
	
	private boolean hasRESTAccess(String restName, String userName) {

		
		List<String> restResourcesList = SecurityService.checkRESTAccess(restName, userName);
		System.out.println(restResourcesList.toString());
		System.out.println(restName.replace("/api/shop/v1/", ""));
		return restResourcesList.contains(restName.replace("/api/shop/v1/", ""));
	}
	
	private boolean hasNavAccess() {
		
		return false;
	}

}
