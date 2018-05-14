import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

public class AuthenticatorWhiteBoxTest {
	

	
	
	@Test
	public void testAuthenticate() {
		//Testing the correct password and userName
		String user1 = "user1";
		String pass1 = "pass1";
		
		Authenticator result = new Authenticator();
		
		try {
			result.authenticate(user1, pass1);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAuthenticateError() {
		//Testing the correct password and userName
		String user1 = "wronguser";
		String pass1 = "pass123";
		
		Authenticator result = new Authenticator();
		
		try {
			result.authenticate(user1, pass1);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
