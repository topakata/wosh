package repositories;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.xml.sax.SAXException;

import models.Participant;
import xml.XMLWorker;

@XmlRootElement(name="participants")
@XmlAccessorType(XmlAccessType.FIELD)
public class Repository {
	private static Repository instance = null;
	
	@XmlElement(name="participant")
	private static Set<Participant> collection = new HashSet<Participant>();
	
	static int index=1;
	private static String pathToXml = "C:\\Users\\FERKO\\eclipse-workspace\\shirt-test-finals\\src\\main\\webapp\\xml\\storage.xml";
	private static String pathToSchema = "C:\\Users\\FERKO\\eclipse-workspace\\shirt-test-finals\\src\\main\\webapp\\xml\\schema11.xsd";
	
	public static Repository getInstance() {
		if(instance == null) {
			XMLWorker worker = new XMLWorker();

			try {
				instance = worker.readFromXml(pathToXml,pathToSchema);
				index=collection.size()+1;
			} catch (FileNotFoundException | UnsupportedEncodingException | JAXBException | SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		return instance;
	}
	
	public Participant getParticipantByName(String name) {
		for (Participant p : collection) {
			if (p.getFirstName().equals(name)) {
				return p;
			}
		}
		return null;
	}
	
	public void addParticipant(Participant participant) {
		participant.setId(index++);
		collection.add(participant);
		update();
	}
	
	public void update() {
		XMLWorker worker = new XMLWorker();
		try {
			worker.writeToXMLFile(pathToXml, this);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public static Set<Participant> getCollection() {
		return collection;
	}
}
