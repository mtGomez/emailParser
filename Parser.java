// Java program to extract the
// email ids from a given text file
  
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.*;

import javax.swing.JTextPane;

import java.io.*;
  
class Parser {
	
	private String output;
	private Set<String> emails;
	
	public Parser() {
		emails = new HashSet<String>();
		output = "";
	}
	
    public void extractEmail(ArrayList<String> arrList,JTextPane panelOutput,JTextPane panelNemails) throws IOException{
    	emails.clear();
    	output = "";
    	Pattern p = Pattern.compile("[a-zA-Z0-9]"+ "[a-zA-Z0-9_.]"+ "*@[a-zA-Z0-9]"+ "+([.][a-zA-Z]+)+");
    	
    	for (Iterator<String> iterator = arrList.iterator(); iterator.hasNext();) {
			String line = (String) iterator.next();
			Matcher m = p.matcher(line);
			while (m.find()) {
            	emails.add(m.group());
            }
		}
    	showEmails(panelOutput,panelNemails);
    }
  
    private void showEmails(JTextPane panelOutput,JTextPane panelNemails) {
    	Integer nEmails = (Integer) emails.size();
    	panelNemails.setText(nEmails.toString());

    	for (String email : emails) {
        	output += email+"\n";
		}
    	panelOutput.setText(output);
    }

    public String getOutput() {
    	return output;
    }
    
}