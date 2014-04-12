package rickguyton.examples.MadLibs;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import org.junit.Test;

public class MadLibUnitTest {
	
	@Test
	public void testSetSentence(){
		String sentence = "I am trying this jUnit thing out.";
		
		MadLib test = new MadLib();
		test.setSentence(sentence);
		
		assertEquals(sentence, test.getSentence());
	}
	
	@Test
	public void testSetHashMap(){
		HashMap<String, String> responses = new HashMap<String, String>();
		responses.put("Key", "Answer");
		responses.put("((an extreme word))", "radical");
		
		MadLib test = new MadLib();
		test.setResponses(responses);
		
		assertEquals(responses, test.getResponses());
	}
	
	@Test
	public void testSimpleMadLib(){
		String sentence = "I want to work for ((a business))!";
		String expected = "I want to work for Asynchrony!";

		HashMap<String, String> response = new HashMap<String, String>(); 
		
		response.put("((a business))", "Asynchrony");
		
		MadLib test = new MadLib();
		assertEquals(expected, test.createMadLib(sentence, response).toString());
	}
	
	@Test
	public void testMadLibWithNamedAnswer() {
		String sentence = "I like ((beer:a beer brand)).  I really like ((a beer brand)), but my favorite is ((beer)).";
		String expected = "I like Urban Chestnut Schnickelfritz.  I really like Stone IPA, but my favorite is Urban Chestnut Schnickelfritz.";
		
		HashMap<String, String> response = new HashMap<String, String>();
		response.put("((beer:a beer brand))", "Urban Chestnut Schnickelfritz");
		response.put("((beer))", "Urban Chestnut Schnickelfritz");
		response.put("((a beer brand))", "Stone IPA");
		
		MadLib test = new MadLib(sentence);
		assertEquals(sentence, test.getSentence());
		assertEquals(expected, test.createMadLib(sentence, response).toString());
	}
	
	@Test
	public void testMultipleMadLib(){
		String sentence = "I had a ((an adjective)) sandwich for lunch today. It dripped all over my ((a body part)) and ((a noun)).";
		String expected = "I had a stinky sandwich for lunch today. It dripped all over my small toe and flip flops.";
		
		//Ideally I'd figure out a way to pass this in as an input stream instead of using System.in and System.out in MadLib.java
		HashMap<String, String> response = new HashMap<String, String>();
		response.put("((an adjective))", "stinky");
		response.put("((a body part))", "small toe");
		response.put("((a noun))", "flip flops");
		
		MadLib test = new MadLib();
		
		assertEquals(expected, test.createMadLib(sentence, response).toString());
	}
	
	@Test
	public void testNoMadlib(){
		String sentence = "I would walk 500 miles.";
		String expected = "I would walk 500 miles.";
		
		HashMap<String, String> response = new HashMap<String, String>();
		
		MadLib test = new MadLib();
		test.setSentence(sentence);
		
		
		assertEquals(expected, test.createMadLib(sentence, response).toString());
	}
	
	@Test 
	public void testAskQuestion(){
		String question = "((hello))";
		String expected = "hi";
		String input = "hi\n";
		
		HashMap<String, String> response = new HashMap<String, String>();
		response.put(question, expected);
		
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		
		MadLib test = new MadLib(in);
		test.askQuestion(question);
		
		assertEquals(response, test.getResponses());
	}
	
	@Test
	public void testAskQuestionWithColon(){
		String question = "((salutation:greeting))";
		String expected = "hi";
		String input = "hi\n";
		
		HashMap<String, String> response = new HashMap<String, String>();
		response.put(question, expected);
		response.put("((salutation))", expected);
		
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		
		MadLib test = new MadLib(in);
		test.askQuestion(question);
		
		assertEquals(response, test.getResponses());
	}
	
	@Test
	public void testParseSentence(){
		String sentence = "I want to work for ((a business))!";
		String input = "Asynchrony";
		String expected = "I want to work for Asynchrony!";
		
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		MadLib test = new MadLib(in);
			
		assertEquals(expected, test.parseSentence(sentence).toString());
	}
	
	@Test(expected= ArrayIndexOutOfBoundsException.class)
	public void testAskBadQuestion(){
		String question = "((test:))";
		String input = "test\n";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		
		MadLib test = new MadLib(in);
		test.askQuestion(question);	
	}

	@Test (expected= StringIndexOutOfBoundsException.class)
	public void testAskEmptyQuestion(){
		String question = "";
		String input = "test\n";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		
		MadLib test = new MadLib(in);
		test.askQuestion(question);	
	}
}
