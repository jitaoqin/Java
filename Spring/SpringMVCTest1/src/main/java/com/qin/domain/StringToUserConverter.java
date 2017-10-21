package com.qin.domain;


import org.springframework.core.convert.converter.Converter;

public class StringToUserConverter implements Converter<String,User> {

	@Override
	public User convert(String source) {
		User user = new User();
		if(source!=null){
			String[] items  =  source.split(":");
			user.setUserName(items[0]);
			user.setuId(items[1]);
			user.setTel(items[2]);
		}
		return user;
	}

}
