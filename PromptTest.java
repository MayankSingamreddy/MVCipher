/**
* promptTest.java
* Tests the Prompt c lass.
*  @authohr Mayank
* @version 8/28/17
*/

public class PromptTest {
		public static void main(String [] args) {
		
		String str = Prompt.getString("Give me a string");
		System.out.println("Here it is -> " + str);
			
		int a = Prompt.getInt("Give me an int");
		System.out.println("Here it is ->" + a);
		}
}