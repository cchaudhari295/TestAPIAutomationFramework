package com.database;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvRunner {

	public static void main(String[] args) {	
		Dotenv dotenv=Dotenv.load();
		String dbPassword=dotenv.get("DB_PASSWORD");
		System.out.println(dbPassword);
	}

}
