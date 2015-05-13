package com.petergashek.testtask;

import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;


/**
 * @author pgashek
 *
 */
public class JunitTest {

	@Test
	public void empltyFile() {
		List<String> topFiveOutput = WordsInFileUtil
				.topFiveOutput("/Users/ghost/Documents/emptyFile.txt");
		assertEquals(topFiveOutput.size(), 0);
	}
	
	@Test
	public void twoSameNames() {
		List<String> topFiveOutput = WordsInFileUtil
				.topFiveOutput("/Users/ghost/Documents/SameNamesFile.txt");
		assertEquals(topFiveOutput.size(), 1);
	}
	
	@Test
	public void differentNames() {
		List<String> topFiveOutput = WordsInFileUtil
				.topFiveOutput("/Users/ghost/Documents/words.txt");
		assertEquals(topFiveOutput.size(), 5);
	}
	
	@Test
	public void largeFile() {
		List<String> topFiveOutput = WordsInFileUtil
				.topFiveOutput("/Users/ghost/Documents/LargeFile.txt");
		assertEquals(topFiveOutput.size(), 5);
	}
}