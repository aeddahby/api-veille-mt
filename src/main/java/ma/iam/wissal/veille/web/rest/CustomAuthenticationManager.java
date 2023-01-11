package ma.iam.wissal.veille.web.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import ma.iam.wissal.veille.domain.Authority;
import ma.iam.wissal.veille.domain.User;
import ma.iam.wissal.veille.service.UserService;
import ma.iam.wissal.veille.service.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

@Repository
public class CustomAuthenticationManager implements AuthenticationManager {

    private final Logger log = LoggerFactory.getLogger(CustomAuthenticationManager.class);

    @Autowired
    private UserService userService;

    @Autowired
    private LdapTemplate wissalLdapTemplate;

    @Value("${spring.ldap.enabled}")
    private Boolean ldapEnabled;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            log.debug("---> authenticate {}", authentication);
            String login = authentication.getPrincipal().toString();
            String password = authentication.getCredentials().toString();
            ldapEnabled = Boolean.TRUE.equals(ldapEnabled);
            Boolean autoAuth = false;
            if (!ldapEnabled || !login.startsWith("user_") || login.equals("admin")) { // FIXME: remove after tests are finished
                log.warn("Warning.. automatically authenticating user WITHOUT ldap:" + login);
                autoAuth = true;
            }

            boolean isSuccess = autoAuth || wissalLdapTemplate.authenticate("", "(sAMAccountName=" + login + ")", password);
            if (isSuccess) {
                Optional<User> userOptional = userService.getUserWithAuthoritiesByLogin(login);
                if (!userOptional.isPresent()) {
                    log.error("authenticate ERROR Utilisateur " + login + " non paramétré");
                    throw new BadCredentialsException("Utilisateur " + login + " non paramétré");
                }
                if (!userOptional.get().isActivated()) {
                    log.error("authenticate ERROR Utilisateur " + login + " inactif");
                    throw new BadCredentialsException("Utilisateur " + login + " inactif");
                }
                User user = userOptional.get();
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                for (Authority perm : user.getAuthorities()) {
                    authorities.add(new SimpleGrantedAuthority(perm.getName()));
                }
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user.getLogin(), "", authorities);
                log.debug("<--- authenticate {}", auth);
                return auth;
            } else {
                log.error("authenticate ERROR Utilisateur " + login + " Invalid username or password");
                throw new BadCredentialsException("Invalid username or password");
            }
        } catch (Exception e) {
            log.error("authenticate ERROR Utilisateur Failed to login", e);
            throw new AuthenticationServiceException("Failed to login", e);
        }
    }

    public UserDTO getInfosLdap(String login) throws Exception {
        log.info("---> getInfosLdap {}", login);
        if (login.startsWith("test-")) {
            UserDTO result = new UserDTO();
            result.setLogin(login);
            if (login.startsWith("test-")) {
                login = login.substring("test-".length());
            }
            result.setFirstName(login);
            result.setLastName(login);
            result.setEmail(login + "@iam.ma");
            log.info("<--- getInfosLdap - TEST {}", result);
            return result;
        }
        EqualsFilter filter = new EqualsFilter("sAMAccountName", login);
        List<UserDTO> results;
        try {
            results = wissalLdapTemplate.search("", filter.encode(), new PersonAttributesMapper());
        } catch (Exception e) {
            throw new Exception("Erreur connexion LDAP " + e.getMessage());
        }
        if (results == null || results.size() != 1) {
            return null;
        }
        log.info("<--- getInfosLdap {}", results.get(0));
        return results.get(0);
    }

    public class PersonAttributesMapper implements AttributesMapper<UserDTO> {

        public UserDTO mapFromAttributes(Attributes attrs) throws NamingException {
            // FIXME: REMOVE LATER
            // StringBuilder res = new StringBuilder();
            // for (Enumeration vals = attrs.getAll(); vals.hasMoreElements();) {
            //     Attribute nextElement = (Attribute)vals.nextElement();
            //     res.append("\nAttr : ").append(nextElement.getID()).append(" " + attrs.get(nextElement.getID())).append(";");
            // }
            // String result = (String)attrs.get("cn").get() + ";" + (String)attrs.get("sn").get() +";"+res;
            // log.info("ALL ATTRIBUTES: {}", result);
            // END FIXME: REMOVE LATER

            UserDTO user = new UserDTO();
            user.setEmail((String) attrs.get("mail").get());
            user.setLogin((String) attrs.get("sAMAccountName").get());
            user.setFirstName((String) attrs.get("sn").get());
            user.setLastName((String) attrs.get("givenName").get());
            return user;
        }
    }
}
