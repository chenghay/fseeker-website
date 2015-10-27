package com.flowerseeker.test;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String regex = "\\d{5}((,\\s*)\\d{5})*";
		System.out.println("12342, 12312,31244,  	 12345".matches(regex));
		System.out.println("12343".matches(regex));
		System.out.println("2314 2".matches(regex));
		for (String s: "12342, 12312,31244,  	 12345".split(",\\s*")) {
			System.out.println(s);
		}
	}

}
