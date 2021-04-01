// Java program to extract the
// email ids from a given text file
  
import java.util.HashSet;
import java.util.Set;
import java.util.regex.*;
import java.io.*;
  
class Parser {
	private static Set<String> emails = new HashSet<String>();
	
    public static void extractEmailIds(BufferedReader br,PrintWriter pw,Pattern p) throws IOException{
    	
    	String line = br.readLine();
    	while (line != null) {
            Matcher m = p.matcher(line);
            // If any match
            while (m.find()) {
                // write the email id
                // to output.txt file
            	emails.add(m.group());
            }
            // Goto next line in input.txt file
            line = br.readLine();
        }
    }
  
    // Driver code
    public static void main(String[] args)throws IOException{
        // PrintWriter for writing email id
        // to output.txt file
        PrintWriter pw
            = new PrintWriter("salida.txt");
  
        // Compile() argument is the
        // regular expression for email id
        Pattern p = Pattern.compile("[a-zA-Z0-9]"+ "[a-zA-Z0-9_.]"+ "*@[a-zA-Z0-9]"+ "+([.][a-zA-Z]+)+");
  
        // BufferedReader for reading
        // from input.txt file
        BufferedReader br = new BufferedReader(new FileReader("entrada.txt"));
  
        // Calling extractEmailIds
        extractEmailIds(br, pw, p);
        
        pw.println("Numero de emails diferentes: "+emails.size());
        pw.flush();
        pw.println("Lista de emails:");
        pw.flush();
        for (String email : emails) {
        	pw.println(email);
        	pw.flush();
		}
    }
}