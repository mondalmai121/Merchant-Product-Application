package org.jsp.MP.controller;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Test {

	public static void main(String[] args) {
		
		EntityManagerFactory fact=Persistence.createEntityManagerFactory("dev");
		System.out.println(fact);
	}
}
