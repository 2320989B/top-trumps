package commandline;

/**
 * A definition of unicode values used to represent block elements via System
 * .out.
 */
enum CharCodes {
   // Box elements
//   HORIZONTAL ("\u2501"),
//   TOP_LEFT ("\u250F"),
//   TOP_RIGHT ("\u2513"),
//   VERTICAL ("\u2503"),
//   TEE_RIGHT("\u2523"),
//   TEE_LEFT("\u252B"),
//   BOTTOM_LEFT ("\u2517"),
//   BOTTOM_RIGHT ("\u251B"),
//   TEE_DOWN ("\u2533"),
//   TEE_UP ("\u253B"),

   HORIZONTAL ("\u2500"),
   TOP_LEFT ("\u250C"),
   TOP_RIGHT ("\u2510"),
   VERTICAL ("\u2502"),
   TEE_RIGHT("\u251C"),
   TEE_LEFT("\u2524"),
   BOTTOM_LEFT ("\u2514"),
   BOTTOM_RIGHT ("\u2518"),
   TEE_DOWN ("\u252C"),
   TEE_UP ("\u2534"),

   // Misc Symbols
//   PLAYER_ACTIVE ("\u25CF"),
//   PLAYER_WAITING ("\u25CB"),
//   PLAYER_OUT_GAME ("\u25CC");

   PLAYER_ACTIVE ("\u2611"),
   PLAYER_WAITING ("\u2610"),
   PLAYER_OUT_GAME ("\u2612");

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