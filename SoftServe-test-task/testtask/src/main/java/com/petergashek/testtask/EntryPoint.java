package com.petergashek.testtask;

import java.util.List;

public class EntryPoint {

	public static void main(String[] args) {

		List<String> topFiveOutput = WordsInFileUtil
				.topFiveOutput("/Users/ghost/Documents/words.txt");
		for (String string : topFiveOutput) {
			System.out.println(string);

		}
	}
}
