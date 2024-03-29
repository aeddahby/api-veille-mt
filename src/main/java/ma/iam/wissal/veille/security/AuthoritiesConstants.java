package ma.iam.wissal.veille.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

	  public static final String ADMIN = "ROLE_ADMIN";

	  public static final String CONTRIBUTEUR = "ROLE_CONTRIBUTEUR";

	  public static final String ANIMATEUR_CENTRAL = "ROLE_ANIMATEUR_CENTRAL";
	    
	  public static final String RELAIS_CENTRAL = "ROLE_RELAIS_CENTRAL";
	   
	  public static final String RELAIS_REGIONAL = "ROLE_RELAIS_REGIONAL";

	  public static final String USER = "ROLE_USER";

	  public static final String ANONYMOUS = "ROLE_ANONYMOUS";

	  private AuthoritiesConstants() {}
}
