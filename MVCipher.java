import java.io.PrintWriter;
import java.util.Scanner;
/**
 *	MVCipher - Add your description here
 *	Requires Prompt and OpenFile classes.
 *	
 *	@author	Mayank Singamreddy
 *	@since	12 September 2017
 */
public class MVCipher
{
	private String theString;				// String to be used as the input
	private String lineRead = "";
	private int readerIterations = 0;     // marker for iterations while reading
	private char [] convertedChar;
	
    
    //main method to run other methods
	public static void main(String[] args)
	{
		MVCipher mvc = new MVCipher();
		mvc.run();
	}
	
	/**
	 *	prompt to run this method
     *  the user enters their string using the scanner
     *  program checks for validity of statement through ascII
     *  changes to uppercase 
     *  user prompted to encrypt or decrypt
	 */
    public void run()
    {
        boolean repeatOrNot = false;
        System.out.println("\n Welcome to the MV Cipher machine!\n");
        do
        {
            repeatOrNot = false; // boolean to check whether or not to repeat
            theString = Prompt.getString("Please input a name to use as theString (letters only)");
            for(int i = 0; i < theString.length(); i++)
            {
                char c = theString.charAt(i);
                if((c < 'A' || c > 'Z') && (c < 'a' || c > 'z'))
                    repeatOrNot = true;
            }
            
        } while(repeatOrNot);
        theString = theString.toUpperCase();
        System.out.println();
        
        int choice = Prompt.getInt("Encrypt or decrypt (1-2)", 1, 2);
        if(choice == 1) 
            encrypt();
        else
            decrypt();
    }
    /*
    *  Prompt the name of the file to encrypt from the user
    *  Program runs the text in file against the algorithm to translate it
    *  System prints out the file once it has been translated
    */
    public void encrypt()
	{
		Scanner documentReader = OpenFile.openToRead(Prompt.getString("Name of file to encrypt"));
		String name = Prompt.getString("Name of output file");
		PrintWriter outfile = OpenFile.openToWrite(name);
		while(documentReader.hasNext())
		{
			String line = documentReader.nextLine();
			for(int i = 0; i < line.length(); i++)
			{
				char c = line.charAt(i);
				int shift = theString.charAt(readerIterations) - 'A' + 1;
				//System.out.println(c + " " + theString.charAt(readerIterations));
				if(c >= 'a' && c <= 'z')
				{
					int original = c - 'a';
					int current = (original + shift) % 26;
					char encoded = (char)(current + 'a');
					outfile.print(encoded);
					readerIterations++;
				}
				else if(c >= 'A' && c <= 'Z')
				{
					int original = c - 'A';
					int current = (original + shift) % 26;
					char encoded = (char)(current + 'A');
					outfile.print(encoded);
					readerIterations++;
				}
				else
					outfile.print(c);
				readerIterations %= theString.length();
			}
			outfile.println();
		}

		System.out.println("\nThe encrypted file " + name + " has been created"
							+ " using the theStringword -> " + theString + "\n");
		outfile.close();
	}

    
    /*
    *  Prompt user for pre-encrypted string
    *  While loop to confirm there are words left to read
    *  Translates the file to the opposite of the encryption (decrypt)
    *  Print the output
    */
	public void decrypt()
	{
		Scanner documentReader = OpenFile.openToRead(Prompt.getString("Name of file to decrypt")); // Prompt to take file
		String name = Prompt.getString("Name of output file"); // prompt of what to output to
		PrintWriter outfile = OpenFile.openToWrite(name); // opens the file to add to it

		while(documentReader.hasNext())
		{
			String line = documentReader.nextLine();
			for(int i = 0; i < line.length(); i++)
			{
				char c = line.charAt(i);
                // the character being read
				int shift = theString.charAt(readerIterations) - 'A' + 1;
				//System.out.println(c + " " + theString.charAt(readerIterations));
				if(c >= 'a' && c <= 'z')
				{
					int original = c - 'a';
					int current = ((original - shift) + 26) % 26;
					char encoded = (char)(current + 'a');
					outfile.print(encoded);
					readerIterations++;
				}
				else if(c >= 'A' && c <= 'Z')
				{
					int original = c - 'A';
					int current = ((original - shift) + 26) % 26;
					char encoded = (char)(current + 'A');
					outfile.print(encoded);
					readerIterations++;
				}
				else
					outfile.print(c);
				readerIterations %= theString.length();
			}
			outfile.println();
		}
		
		System.out.println("\nThe decrypted file " + name + " has been created"
							+ " using the theStringword -> " + theString + "\n");
		outfile.close();
	}

}