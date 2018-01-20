/** Defines an object representing a single player
 * The class is responsible for storing the player's data
 * and current band
 */

package model;

import java.util.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;



public class Player {
	
	
	
	/**
	 * The constant representing the max number of cards in an initial deck
	 */
	private final int MAX_CARDS = 40;
	
	
	/**
	 * The instance variables for the Player class
	 * They consist of the players name, the number of winning rounds,
	 * and the player's individual hand of cards as an array populated with objects of
	 * type Card
	 */
	private String name;
	private Boolean isHuman; //added this in, Joe
	private int roundsWon = 0;	
	
	
	ArrayList<Card> hand = new ArrayList<>();
	
	
	/**
	 *  constructor for the Class 
	 */
	public Player(String playerName, ArrayList<Card> aHand ) {
		this.name = playerName;
		this.hand = aHand;
	}
	
	//possible other constructor so that I can create the players, amke human or AI, and then 'deal' to them
	//from the deck I have created in Game. Joe
	public Player(String name, boolean isHuman ) {
		this.name = name;
		this.isHuman = isHuman;
	}
	
	
	/**
	 * Getter method for player name
	 */
	public String getName() {
		return name;
	}

	public ArrayList<Card> getList(){
		return hand;
	}
	



	
	/**
	 * This method will return the object of type
	 * Card, that lives at the top of the
	 * players hand of cards
	 * @return
	 */
	public Card getTopMostCard() {
		
		try {
			if (hand.size() != 0) {
				return hand.get(0);
			}
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("No cards in the player's hand");
			return null;
		}	
		return hand.get(0);
		
	}
	
	
	
	

	/**
	 * This method will be called after each round for
	 * the players that are currently in a game
	 * It will submit their round cards to the pile 
	 * of cards. Then the winner can collect the cards
	 * from there. Apply to every player, after every round
	 * @param aDeck
	 */
	public void submitActiveCard(ArrayList<Card> aDeck) {
		try {
			if (hand.size() != 0) {
				aDeck.add(hand.get(0));
				hand.remove(hand.get(0));
			}
			
		}
		catch(NullPointerException n) {
			System.out.println("No card to submit in player's hand");
		}
	}

	//possible method to add a card to the player's deck - Joe
	//useful for dealing at the start
	public void receiveCard(Card card) {
		hand.add(card);
	}
	
	
	/**
	 * It will be called on a winner player object , 
	 * so that the winner gets the all the cards, i.e.
	 * the round cards if there was not a draw before,
	 * or draw and pile cards if there was a draw previously
	 * and the pile had already some cards in it
	 */
	public void takeAllCards(ArrayList<Card> aDeck) {
		
		try {
			if (!aDeck.isEmpty()) {
				for (int i =0 ; i < aDeck.size(); i++) {						
					hand.add(aDeck.get(i));
				}
			}
			//Pile is cleared from cards
			aDeck.clear();
			
			//increment the number of rounds won, since taking all cards
			//means that it was a winning round for the player 
			roundsWon++;
			
		}
		catch (IndexOutOfBoundsException i) {
			System.out.println("No cards in the draw pile");
		}
		
	}
	
	
	

	
	
	/**
	 * ONLY FOR TESTING. 
	 * 
	 * FOR A HYPOTHETICAL  CARD, THE HAND WAS CREATED AS EMPTY 
	 * AND THE CARD WAS PUT IN THE DRAW DECK. CHECKED THE METHODS TO SEE THE OUTPUTS
	 * CAN BE DELETED IN THE END
	 * @param args
	 */
	public static void main(String[] args) {
	
		//make a hand for the player
		ArrayList<Card> hand = new ArrayList<>();
		
		//make a player
		Player yannis = new Player("Yannis", hand);
		
		//make a card
		Card c = new Card("helloCard", 3,5,7,90,1);
		Card d = new Card("goodByeCard", 0,4,8,3,140);
		
		//add the card to players hand to check if visible
		hand.add(c);
		hand.add(d);
		
		//make a new deck/ draw pile 
		ArrayList<Card> aDeck = new ArrayList<>();
		
			
		//Check runs to determine if the going around of cards works ok
		System.out.println("The pile contains: " + aDeck.size() + " cards");
		System.out.println("The cards I have in hand are: " + hand.get(0).getName() + " and " +  hand.get(1).getName());
		System.out.println("-------------------");
		
		System.out.println("The best category for the first card is: " + hand.get(0).getBestCategory());
		System.out.println("Player will submit the top card after a round");
		yannis.submitActiveCard(aDeck);
		System.out.println("Will check if the top card is out of his hand");
		System.out.println("-------------------");
		
		System.out.println("My hand contains : " + hand.size() + " cards");
		System.out.println("Now the hand has: " + hand.get(0).getName());
		
		
		System.out.println("-------------------");
		System.out.println("The pile of cards now contains: " + aDeck.size() + " cards"); 
		System.out.println("The name of the card in pile is : " + aDeck.get(0).getName());
		
		System.out.println("-------------------");
		System.out.println("Suppose the player won, he will collect the pile");
		yannis.takeAllCards(aDeck);
		System.out.println("Now will check the contents of the pile and hand");
		System.out.println("-------------------");
		
		System.out.println("The pile contains: " + aDeck.size() + " cards");
		System.out.println("Player's hand contains : " + hand.size() + " cards");
		System.out.println("Namely : " + hand.get(0).getName() + " and " + hand.get(1).getName());
		
		System.out.println("The player Yannis has " + yannis.roundsWon  + " wins");
		
	}
	
	
}
