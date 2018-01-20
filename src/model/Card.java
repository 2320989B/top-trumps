/** Defines an object representing a single card with 
 * a description (name) and arbitrary category values
 */

package model;

import java.util.*;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.io.*;


public class Card {
	
	/**
	 * The instance variables for the Card class
	 */
	private String description = "";
	private int size = 0;
	private int speed = 0;
	private int range = 0;
	private int firepower = 0;
	private int cargo = 0;
	
	//implement the hashmap as an alternative
	Map<String, Integer> cardProperties;
	
	
	/**
	 * The constructor of the Card class 
	 */
	public Card(String descr, int a_size, int a_speed, int a_range, int a_firepower, int a_cargo) {
		
		description = descr;
		size = a_size;
		speed = a_speed;
		range = a_range;
		firepower = a_firepower;
		cargo = a_cargo;
	
	}
	
	//a potential other constructor so that the Card can extract values from the String
	public Card(String categories, String values) {
		
		/*String[] car = cardDetails.split(" ");
		description = cardInfo[0];
		size = Integer.parseInt(cardInfo[1]);
		speed = Integer.parseInt(cardInfo[2]);
		range = Integer.parseInt(cardInfo[3]);
		firepower = Integer.parseInt(cardInfo[4]);
		cargo = Integer.parseInt(cardInfo[5]);*/
		
		//initialise the Linked HashMap
		String[] cardCategories = categories.split(" ");
		String[] cardValues = values.split(" ");
		description = cardValues[0];
		cardProperties = new LinkedHashMap<String, Integer>();
		//loop to allow game to use any deck with any number of categories/values
		for(int i = 1; i < cardCategories.length; i++) {
			cardProperties.put(cardCategories[i], Integer.parseInt(cardValues[i]));
		}
		//print out
		for (Map.Entry<String, Integer> entry : cardProperties.entrySet()) {
			System.out.println(entry.getKey() + entry.getValue());
		}
	}
	
	
	/**
	 * Getter and setter methods 
	 */
	//get a value from the hashmap by passing in its category name (key)
		public int getCardPropertyValue(String category) {
			int value = cardProperties.get(category);
			return value; 
		}
		
		
	public String getName() {
		return description;
	}
	public void setName(String description) {
		this.description = description;
	}
	
	/*public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	
	public int getFirepower() {
		return firepower;
	}
	public void setFirepower(int firepower) {
		this.firepower = firepower;
	}

	public int getCargo() {
		return cargo;
	}
	public void setCargo(int cargo) {
		this.cargo = cargo;
	}*/
	

	
	
	
	/**
	 * This method will select the name of the 
	 category that holds the highest value
	 in a card object and will return it as a sting.
	 It will be used for selecting a category as an
	 AI player.
	 */
	
	//reworked to get best category for AI player from hashmap
		public String getBestCategory() {
			
			//iterate through the hashmap to see which category is best based on value
			int max = 0;
			String bestCategory = "";
			for (Map.Entry<String, Integer> entry : cardProperties.entrySet()) {
				if(max < entry.getValue()) {
					bestCategory = entry.getKey();
					max = entry.getValue();
				}
			}
			return bestCategory;
		}
			
			
	/*public String getBestCategory() {
		
		// Making an array for the values of the card's attributes
		int[] categories = {this.getSize(), this.getSpeed(), this.getRange(), this.getFirepower(), this.getCargo()};
		
		//Getting the max value 
		IntSummaryStatistics stat = Arrays.stream(categories).summaryStatistics();
		int max = stat.getMax();
		
		//The conditionals will determine 
		// which of this attributes is the max 
		// value referring to. When this is found
		// it will return the category name as a string.
		if (max == this.getSize()) {
			return "size"; 
		}
		else if (max == this.getSpeed()) {
			return "speed";
		}
		else if (max == this.getRange()) {
			return "range";
		}
		else if (max == this.getFirepower()) {
			return "firepower";
		}
		else {
			return "cargo";
		}
		
	}*/
		
		
	


	/*
	*//**
	 * FOR TESTING ONLY, IT WILL BE DELETED
	 *//*

	public static void main(String[] args) {
		Card r = new Card("50r", 1, 9, 20, 3, 0);
		Card Avenger = new Card("Avenger", 2,5,4,3,2);
		
		System.out.println(r.getName());
		
		System.out.println(r.getBestCategory());
	}
	*/
	

	
}
