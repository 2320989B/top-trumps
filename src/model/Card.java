/**
 * Defines an object representing a single card with
 * a description (name) and arbitrary category values
 */

package model;

import java.util.*;

class Card {

   /**
    * The instance variables for the Card class
    */
   private String description;

   //implement the hashmap as an alternative
   private Map<String, Integer> cardProperties;

   /**
    * The constructor of the Card class
    */
   //a potential other constructor so that the Card can extract values from the String
   Card(String categories, String values) {
      //initialise the Linked HashMap
      String[] cardCategories = categories.split(" ");
      String[] cardValues = values.split(" ");
      description = cardValues[0];
      cardProperties = new LinkedHashMap<String, Integer>();
      //loop to allow game to use any deck with any number of categories/values
      for (int i = 1; i < cardCategories.length; i++) {
         cardProperties.put(cardCategories[i], Integer.parseInt(cardValues[i]));
      }
   }


   /**
    * Getter and setter methods
    */
   //get a value from the hashmap by passing in its category name (key)
   int getCardPropertyValue(String category) {
      int value = cardProperties.get(category);
      return value;
   }

   Map<String, Integer> getCardProperties() {
      return cardProperties;
   }


   String getName() {
      return description;
   }

   void setName(String description) {
      this.description = description;
   }

   //reworked to get best category for AI player from hashmap
   String getBestCategory() {

      //iterate through the hashmap to see which category is best based on value
      int max = 0;
      String bestCategory = "";
      for (Map.Entry<String, Integer> entry : cardProperties.entrySet()) {
         if (max < entry.getValue()) {
            bestCategory = entry.getKey();
            max = entry.getValue();
         }
      }
      return bestCategory;
   }


   /**
    * Format: (Description=350r, Size=1, Speed=9, Range=2, Firepower=3, Cargo=0)
    *
    * @return a string containing the card description and properties.
    */
   @Override
   public String toString() {
      StringJoiner joiner = new StringJoiner(", ", "(", ")");
      joiner.add("Description=" + getName());
      for (Map.Entry<String, Integer> entry : cardProperties.entrySet()) {
         joiner.add(entry.getKey() + "=" + entry.getValue());
      }
      return joiner.toString();
   }

}
