package rickguyton.examples.MadLibs;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MadLib {
	
	private String sentence;  //The before of the madlib that includes the text to ask the player.
	private HashMap<String, String> response = new HashMap<String, String>();  //map questions, to answers.
	private StringBuffer madLib = new StringBuffer();  //final result the actual madlib with the player responses replaced from the questions in the sentence.
	
	MadLib(String sentence){
		this.sentence = sentence;
	}
	
	MadLib(){
		
	}
	
	/**
	 * This function will read the sentence, ask the user questions based on what is in between (( )) then print out the madlib based on user input.
	 * Example: I like ((a plural animal))!
	 * Output: Please provide a plural animal
	 * Input: turtles
	 * Output: I like turtles!
	 * @param sentence
	 */
	public void parseSentence(String sentence){
		
		//Pattern pattern = Pattern.compile("\\({2}([a-zA-Z0-9: ]+)\\){2}");
		Pattern pattern = Pattern.compile("\\(\\((.*?)\\)\\)");
		Matcher matcher = pattern.matcher(sentence);
		
		while(matcher.find()){
			askQuestion(matcher.group());
			matcher.appendReplacement(madLib, response.get(matcher.group()));
		}

		matcher.appendTail(madLib);
		System.out.println(getMadLib());
	}
	
	/**
	 * This function will ask the user a question and record the input into a response map. 
	 * Logic is in place for place holders or aliases.  ((gem:a gemstone)) means the gem portion will be reused in the sentence later.
	 * The function actually maps the answer twice.  Once for [((gem:a gemstone)), answer] and once for [((gem)), answer]. 
	 * @param question
	 */
	public void askQuestion(String question){
		Scanner in = new Scanner(System.in);
		
		try{
		String stripped = question.substring(2, question.length() -2); //strip (( )) from text.
		
		if(stripped.contains(":")){
			
				String[] split = stripped.split(":");
				//should have 2 segments
				String key = split[0];  //first segment is the reference to be used later.
	
				System.out.println("Please provide " +split[1]);
				String answer = in.nextLine();
				response.put("(("+ key +"))", answer);
				response.put(question, answer);
			}else{
				if(response.containsKey(question)){
					//skip because this is a reference to previous item
				}else{
					System.out.println("Please provide " +stripped);
					response.put(question, in.nextLine());
				}
			}
		}catch (Exception e){
			System.err.println("Poorly formatted MadLib Question.");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/**T
	 * This function will ask the user a question and record the input into a response map. Using an input stream for testing purposes.
	 * @param question
	 */
	public void askQuestion(String question, InputStream inputStream) throws StringIndexOutOfBoundsException, ArrayIndexOutOfBoundsException{
		System.setIn(inputStream);
		Scanner in = new Scanner(System.in);
		
		String stripped = question.substring(2, question.length() -2); //strip (( )) from text.
	
		if(stripped.contains(":")){
		
			String[] split = stripped.split(":");
			//should have 2 segments
			String key = split[0];  //first segment is the reference to be used later.

			System.out.println("Please provide " +split[1]);
			String answer = in.nextLine();
			response.put("(("+ key +"))", answer);
			response.put(question, answer);
		}else{
			if(response.containsKey(question)){
				//skip because this is a reference to previous item
			}else{
				System.out.println("Please provide " +stripped);
				response.put(question, in.nextLine());
			}
		}
		
		in.close();
	}
	
	/**
	 * Mainly used for testing.  This will create a madlib based on the sentence and response map you provide.
	 * @param sentence
	 * @param responses
	 * @return StringBuffer madlib
	 */
	public StringBuffer createMadLib(String sentence, HashMap<String, String> response){
		
		Pattern pattern = Pattern.compile("\\(\\((.*?)\\)\\)");
		Matcher matcher = pattern.matcher(sentence);
		
		while(matcher.find()){
			matcher.appendReplacement(madLib, response.get(matcher.group()));
		}
		matcher.appendTail(madLib);
		
		return getMadLib();
	}
	
	/**
	 * Returns the final output of the madlib with the answers in it.
	 * @return StringBuffer
	 **/
	public StringBuffer getMadLib(){
		return madLib;
	}
	
	public void setSentence(String sentence) {
		this.sentence = sentence;
	}
	/**
	 * Returns the original sentence
	 * @return String
	 */
	public String getSentence(){
		return sentence;
	}
	
	public void setResponses(HashMap<String, String> response){
		this.response = response;
	}
	/**
	 * Returns a map of the questions and provided answers.
	 * The key is the question asked. The value is the answer.
	 * ex: ((an animal)), tiger
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getResponses(){
		return response;
	}
	
	public static void main(String[] args) {
		
		//I had a I had a smelly sandwich for lunch today. It dripped all over my big toe and bathtub.
		
		//MadLib mad = new MadLib("Your startup name: ((startupname:a misspelled version of real word, ending in -ly or -io)).  Have you ever wanted to ((a verb)) all sorts of ((a plural noun)) with friends, family, colleagues, or even ((a minor celebrity)), but didn't know how?  Maybe you were just strolling the sidewalks of ((a gentrified neighborhood)) past your favorite ((an obscure ethnicity)) bakery when you wished you could pull out your i((a Proper noun)) and use it to quickly ((a verb ending in -ate)) all your latest ((trivial stuff you like)) and post it to your ((a social media profile no one reads))? ((startupname)) is the answer.");
		//MadLib mad = new MadLib("The quick ((a color)) ((a1:an animal)) jumped over the ((an adjective)) ((an animal)).");
		//MadLib mad = new MadLib("This will be a crazy ((:test)). ((test:)) is fun.");
		//MadLib mad = new MadLib("");
		MadLib mad = new MadLib("I like ((lang:a programming language)).  We think ((lang)) is better than ((a programming language))."); //sentence from Quiz 1
		mad.parseSentence(mad.sentence);
	}

}
