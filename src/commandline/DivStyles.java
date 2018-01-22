package commandline;

/**
 * A definition of unicode values used to represent block elements via System
 * .out.
 */
enum DivStyles {
   HORIZONTAL ("\u2501"),
   TOP_LEFT ("\u250F"),
   TOP_RIGHT ("\u2513"),
   VERTICAL ("\u2503"),
   TEE_RIGHT("\u2523"),
   TEE_LEFT("\u252B"),
   BOTTOM_LEFT ("\u2517"),
   BOTTOM_RIGHT ("\u251B"),
   TEE_DOWN ("\u2533"),
   TEE_UP ("\u253B");

   String code;

   DivStyles(String code) {
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