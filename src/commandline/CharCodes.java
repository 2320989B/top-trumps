package commandline;

/**
 * A definition of unicode values used to represent block elements via System
 * .out.
 */
enum CharCodes {
   // Box elements

   HORIZONTAL ("\u2500"),
   TOP_LEFT ("\u250C"),
   TOP_RIGHT ("\u2510"),
   VERTICAL ("\u2502"),
   TEE_RIGHT ("\u251C"),
   TEE_LEFT ("\u2524"),
   BOTTOM_LEFT ("\u2514"),
   BOTTOM_RIGHT ("\u2518"),
   TEE_DOWN ("\u252C"),
   TEE_UP ("\u2534"),

   // Misc Symbols
//   PLAYER_ACTIVE ("\u25CF"),
//   PLAYER_WAITING ("\u25CB"),
//   PLAYER_OUT_GAME ("\u25CC");

   PLAYER_ACTIVE ("\u25C9"),
   PLAYER_WAITING ("\u25CE"),
   PLAYER_OUT_GAME ("\u25CB"),
   ACTIVE_CATEGORY_LEFT ("["),
   ACTIVE_CATEGORY_RIGHT ("]"),
   ROUND_WINNER ("\uD83C\uDFC6");

   String code;

   CharCodes(String code) {
      this.code = code;
   }

   /**
    * Gets the uni(code) value.
    *
    * @return the uni(code) value.
    */
   String getCode() {
      return code;
   }
}