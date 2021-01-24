package com.name.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.name.security.service.SecurityService;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	SecurityService sService;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    	
		String name = "";
		try {
			name = sService.getUserByName(userName).getUserName();
		} catch (Exception e) {
			System.out.println(e);
			name = "foo";
		}

		return new User(name, "foo", new ArrayList<>());
    }
}