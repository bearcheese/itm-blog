package hu.bearmaster.itm.web.providers;

import hu.bearmaster.itm.common.exception.ItmException;
import hu.bearmaster.itm.common.model.UserVO;
import hu.bearmaster.itm.common.services.UserService;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class UserAuthenticationProvider implements AuthenticationProvider {
   
   private static final Logger logger = LoggerFactory.getLogger(UserAuthenticationProvider.class);
   
   public static final String ROLE_USER = "ROLE_USER";
   public static final String ROLE_ADMIN = "ROLE_ADMIN";
   
   private static final List<GrantedAuthority> USER_AUTHS;
   private static final List<GrantedAuthority> ADMIN_AUTHS;
   
   private UserService userService;
   
   public UserAuthenticationProvider(UserService userService) {
      this.userService = userService;
   }
   
   static {
      USER_AUTHS = new ArrayList<GrantedAuthority>();
      USER_AUTHS.add(new SimpleGrantedAuthority(ROLE_USER));
      
      ADMIN_AUTHS = new ArrayList<GrantedAuthority>(USER_AUTHS);
      ADMIN_AUTHS.add(new SimpleGrantedAuthority(ROLE_ADMIN));
      
   }

   @Override
   public Authentication authenticate(Authentication authentication)
         throws AuthenticationException {
      try {
   
         logger.info("Authenticating agains username {}", authentication.getName());
         UserVO user = userService.authenticateUser(authentication.getName(), authentication.getCredentials().toString());
         
         List<GrantedAuthority> authorities;
         
         if (user.isAdmin()) {
            logger.info("Granting admin privilige to {}", user.getName());
            authorities = ADMIN_AUTHS;
         } else {
            authorities = USER_AUTHS;
         }
         
         
         UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword(), authorities);

         return token;
      } catch (ItmException e) {         
         throw new BadCredentialsException("Incorrect name/password!");
      } catch (Exception e) { //Exception to catch runtime exceptions as well (like NPE)  
         logger.error("Exception raised during user authentication!", e);
         throw new BadCredentialsException("Incorrect name/password!");
      }
   }

   @Override
   public boolean supports(Class<?> authentication) {
      logger.debug("Authentication class={}", authentication);
      return true;
   }

}
