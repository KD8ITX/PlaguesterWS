package com.kd8itx.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class OpenIdUserDetailsService implements UserDetailsService {
	 
    public UserDetails loadUserByUsername(String openIdIdentifier) {
    	UserAccount userAccount = new UserAccount();
    	/*;//userAccountRepositoryService.findUserAccountsByOpenIdIdentifier(openIdIdentifier).getSingleResult();
        if (userAccount == null) {
            throw new UsernameNotFoundException(openIdIdentifier);
        } else {
            if (!userAccount.isEnabled()) {
                throw new DisabledException("User is disabled");
            }
        }
        */
        return userAccount;
    }
 
}
