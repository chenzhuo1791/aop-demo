package com.cop.dao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.cop.aspect.MyAspect;

public class DaoProxy implements InvocationHandler {

	private PersonDao personDao;

	/**
	 * 所有动态代理类的方法调用，都会交由invoke()方法去处理 proxy 被代理后的对象 method 将要执行的方法 args 执行方法时需要的参数
	 **/
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		MyAspect myAspect = new MyAspect();// 声明切面

		myAspect.start();// 指定位置程序执行前执行这个方法

		Object object = method.invoke(personDao, args);// 在目标类上调用方法

		myAspect.end();// 指定位置程序执行结束后执行

		return object;
	}

	public Object createProxy(PersonDao personDao) {// 创建代理方法

		this.personDao = personDao;

		ClassLoader classLoader = DaoProxy.class.getClassLoader(); // 1.类加载器
		
		@SuppressWarnings("rawtypes")
		Class[] clazz = personDao.getClass().getInterfaces();// 2.被代理对象实现的所有接口

		return Proxy.newProxyInstance(classLoader, clazz, this);// 3.使用代理类，进行增强，返回的是代理后的对象
	}

}
