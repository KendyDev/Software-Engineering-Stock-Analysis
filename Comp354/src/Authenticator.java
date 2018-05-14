

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Authenticator {
		String fileName = "users.xml";
		String path = System.getProperty("user.dir");
	
	public Authenticator() {
		
	}
	
	public Authenticator(String fileName, String path){
		this.fileName = fileName;
		this.path = path;
	}
	
	public String authenticate(String username, String password) throws ParserConfigurationException, SAXException, IOException{
		File inputXML = null;
		try{
			inputXML = new File(path + "\\" + fileName);
			
		}catch (Exception e){
			return "exception";
		}
		DocumentBuilderFactory dbF = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbF.newDocumentBuilder();
		Document doc = dBuilder.parse(inputXML);
		Element ele = doc.getDocumentElement();
		NodeList nodeList = ele.getChildNodes();
		
		for (int i = 0; i<nodeList.getLength(); i++){
			if(nodeList.item(i).getNodeType() == Node.ELEMENT_NODE){
				Element el = (Element) nodeList.item(i);
				
				if(el.getElementsByTagName("username").item(0).getTextContent().equals(username) && el.getElementsByTagName("password").item(0).getTextContent().equals(password))
				return "success";
			}
		}	
		
		return "failure";
	}
}
