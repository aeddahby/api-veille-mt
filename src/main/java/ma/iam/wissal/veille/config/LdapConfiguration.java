package ma.iam.wissal.veille.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

@Configuration
public class LdapConfiguration {

    private final Logger log = LoggerFactory.getLogger(LdapConfiguration.class);

    @Value("${spring.ldap.urls}")
    private String ldapUrl;

    @Value("${spring.ldap.base}")
    private String ldapBase;

    @Value("${spring.ldap.user}")
    private String user;

    @Value("${spring.ldap.password}")
    private String password;

    @Bean
    public LdapContextSource contextSource() {
        log.info("LDAP ldapUrl {} ldapBase {}", ldapUrl, ldapBase);
        log.debug("LDAP user {} password {}", user, password);
        LdapContextSource contextSource = new LdapContextSource();
        String[] urls = ldapUrl.split(",");
        contextSource.setUrls(urls);
        contextSource.setBase(ldapBase);
        contextSource.setUserDn(user);
        contextSource.setPassword(password);
        contextSource.setReferral("follow");
        return contextSource;
    }

    @Bean
    public LdapTemplate grcLdapTemplate() {
        return new LdapTemplate(contextSource());
    }
}
