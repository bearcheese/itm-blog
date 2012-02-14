package hu.bearmaster.itm.web.providers;

import java.util.ArrayList;
import java.util.List;

import hu.bearmaster.itm.common.exception.ItmException;
import hu.bearmaster.itm.common.model.UserVO;
import hu.bearmaster.itm.common.services.UserService;

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
   
   private static final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
   
   static {
      authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
   }
   
   private UserService userService;
   
   

   public UserService getUserService() {
      return userService;
   }

   public void setUserService(UserService userService) {
      this.userService = userService;
   }
   
   @Override
   public Authentication authenticate(Authentication authentication) throws AuthenticationException {
      logger.info("Authenticating agains username {}", authentication.getName());
      try {
         UserVO user = userService.authenticateUser(authentication.getName(), authentication.getCredentials().toString());
         return new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword(), authorities);
      } catch (ItmException e) {
         throw new BadCredentialsException("Incorrect name/password!");
      }
   }

   @Override
   public boolean supports(Class<?> authentication) {
      logger.debug("Authentication class={}", authentication);
      return true;
   }

}
