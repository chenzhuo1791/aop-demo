package com.cop.test;

import com.cop.dao.DaoProxy;
import com.cop.dao.PersonDao;
import com.cop.dao.PersonDaoImpl;

public class ProxyTest {

	public static void main(String[] args) {
		DaoProxy daoProxy = new DaoProxy() ;
		
		PersonDao personDao = new PersonDaoImpl() ;
		
		PersonDao userDao = (PersonDao)daoProxy.createProxy(personDao);
		
		userDao.addUser();
		
	}
	
}
