package com.kd8itx.palguester.domain;

import java.util.Random;

import org.apache.log4j.Logger;

public class DNA {
	private static final Logger log = Logger.getLogger(DNA.class);
	
	private DNAType dnaType;
	private String dna;
	
	public DNA() {}
	
	public DNA(DNAType dnaType) {
		
		this.dna = "";
		for (int i=0; i<100; i++) {
			String binNumber = binNumber();
			
			// Pad if needed
			if (binNumber.length() == 1) {
				binNumber = "00" + binNumber;
			} else if (binNumber.length() == 2) {
				binNumber = "0" + binNumber;
			}
			dna += binNumber;
			
			//log.debug("DNA Chunk [" + binNumber + "]");
		}
		this.dnaType = dnaType;
	}
	
	public String binNumber() {
	    Random rg = new Random();
	    int n = rg.nextInt(7);
	    return Integer.toBinaryString(n);
	}

	public DNAType getDnaType() {
		return dnaType;
	}

	public void setDnaType(DNAType dnaType) {
		this.dnaType = dnaType;
	}

	public String getDna() {
		return dna;
	}

	public void setDna(String dna) {
		this.dna = dna;
	}
}
