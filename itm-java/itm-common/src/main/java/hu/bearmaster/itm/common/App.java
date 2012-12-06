package hu.bearmaster.itm.common;

import hu.bearmaster.itm.common.exception.ItmException;
import hu.bearmaster.itm.common.model.UserVO;
import hu.bearmaster.itm.common.services.UserService;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

   public static void main(String[] args) {
      ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "/hu/bearmaster/itm/common/context/spring-context.xml" });

      UserService userService = (UserService) context.getBean("userService");
      
      UserVO newUser = new UserVO();
      newUser.setName("testnewpwuser");
      newUser.setPassword("majom");
      newUser.setEmail("test@newpassword.com");
      
      try {
         
         System.out.println("Registered: " + userService.registerUser(newUser));
      } catch (ItmException e) {
         System.out.println("Exception happened: " + e);
      }
      
      try {
         System.out.println("Authenticated: " + userService.authenticateUser(newUser.getName(), "majom"));
      } catch (ItmException e) {
         System.out.println("Exception happened: " + e);
      }
      
      List<UserVO> users = userService.listUsers();
      
      for (UserVO u : users) {
         System.out.println(u);
      }
      
      System.out.println("Done!");

   }

}
