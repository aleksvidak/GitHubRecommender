package fon.tags.github;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class UrlReader {
	public static String readUrl(String urlString) throws IOException {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 

	        return buffer.toString();
	    }
	    
	    catch (IOException e) {
	    	return null;
	    }
	    
	    finally {
	        if (reader != null)
	            reader.close();
	    }
	}
}
