package com.samej.sample;

import java.io.File;

import org.apache.log4j.Logger;

public class HelloTest {
	private static final Logger LOG = Logger.getLogger(HelloTest.class);

	public static void main(String[] args) {
		LOG.info("Helo");
		String file = Thread.currentThread().getContextClassLoader().getResource("log4j.properties").getFile();
		System.out.println(file);
		File f = new File(file);
		if (f.exists()) {
			System.out.println("Yes existssss!!");
		}
	}
}
