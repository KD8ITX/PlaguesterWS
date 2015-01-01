package com.kd8itx.plaguester.domain;

import java.security.SecureRandom;
import java.util.Date;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;

import com.kd8itx.plaguester.dao.PersonDAO;


@Entity("Person")
public class Person {
	private static final Logger log = Logger.getLogger(Person.class);
	
	@Id
	private ObjectId id;
	
	private ObjectId userId;
	private Gender gender;
	private Date birthDate;
	private String name;
	private ObjectId fatherId;
	private ObjectId motherId;
	
	@Indexed
	private DNA[] dna;
	
	public Person() { }
	
	public Person(
			ObjectId userId,
			Gender gender,
			String name,
			ObjectId fatherId,
			ObjectId motherId,
			DNA[] dna) {
		
		this.userId = userId;
		this.gender = gender;
		this.name = name;
		this.fatherId = fatherId;
		this.motherId = motherId;
		this.dna = dna;
		
		this.birthDate = new Date();
	}
	
	public static DNA[] createNewDNA(ObjectId fatherId, ObjectId motherId) throws Exception {
		log.debug("Start generating new DNA");
		DNA[] generatedDna = new DNA[DNAType.values().length];
		
		Person mother = PersonDAO.get(motherId);
		Person father = PersonDAO.get(fatherId);
		SecureRandom random = new SecureRandom();
		
		// Counter to keep track of what DNAType enum we are on
		int dnaCount = 0;
		
		// Loop through the DNA enum
		for (DNAType dnaType : DNAType.values()) {
			log.debug("Generate dna for [" + dnaType + "]");
			// The generated dna for this DNAType
			String dna = "";
			
			// Where we are in this chunk of DNA
			int dnaPointer = 0;
			
			String motherDna = mother.getDna(dnaType).getDna();
			String fatherDna = father.getDna(dnaType).getDna();
			log.debug("Mother DNA     [" + motherDna + "]");
			log.debug("Father DNA     [" + fatherDna + "]");
			
			int dnaLength = fatherDna.length();
			//log.debug("Total length of the DNA chain is [" + dnaLength + "]");
			
			// Loop over the dna, through to the end
			boolean continueLooping = true;
			while(continueLooping) {
				int dnaPointerEnd = dnaPointer + 2;
				//log.debug("DNA Pointer Start [" + dnaPointer + "] End [" + dnaPointerEnd + "]");
				
				// Check that we don't overrun the end of our string
				if (dnaPointerEnd <= dnaLength) {
					// Here is the magic merge, based off from a SecureRandom boolean
					if (random.nextBoolean() == true) {
						String selection = motherDna.substring(dnaPointer, dnaPointer + 3);
						dna += selection;
						//log.debug("[" + selection + "] selected from Mother");
					} else {
						String selection = fatherDna.substring(dnaPointer, dnaPointer + 3);
						dna += selection;
						//log.debug("[" + selection + "] selected from Father");
					}
					
					// Reset the pointer
					dnaPointer = dnaPointerEnd + 1;
				} else {
					// If we hit the end, break out of this infinite loop
					continueLooping = false;
				}
				
			}
			log.debug("Generated DNA [" + dna + "]");
			generatedDna[dnaCount] = new DNA(dnaType);
			generatedDna[dnaCount].setDna(dna);
			dnaCount++;
		}
		
		return generatedDna;
	}
	
	
	public DNA getDna(DNAType dnaType) throws Exception {
		for(DNA thisDna : dna) {
			if (thisDna.getDnaType() == dnaType) {
				return thisDna;
			}
		}
		
		throw new Exception("couldn't find dna");
	}

	public void setDna(DNA[] dna) {
		this.dna = dna;
	}

	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ObjectId getFatherId() {
		return fatherId;
	}
	public void setFatherId(ObjectId fatherId) {
		this.fatherId = fatherId;
	}
	public ObjectId getMotherId() {
		return motherId;
	}
	public void setMotherId(ObjectId motherId) {
		this.motherId = motherId;
	}
	public ObjectId getUserId() {
		return userId;
	}
	public void setUserId(ObjectId userId) {
		this.userId = userId;
	}
	
	
}
