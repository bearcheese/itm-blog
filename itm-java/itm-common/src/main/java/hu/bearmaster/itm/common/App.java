package hu.bearmaster.itm.common;

import hu.bearmaster.itm.common.dao.UserDao;
import hu.bearmaster.itm.common.dao.impl.UserDaoImpl;
import hu.bearmaster.itm.common.dao.model.UserDO;
import hu.bearmaster.itm.common.exception.ItmException;
import hu.bearmaster.itm.common.model.UserVO;
import hu.bearmaster.itm.common.services.UserService;
import hu.bearmaster.itm.common.services.impl.UserServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
   
   public static void main(String[] args) {
      
      EntityManagerFactory emf = Persistence.createEntityManagerFactory("itm-persistence");

      EntityManager em = emf.createEntityManager();
      
      UserDao userDao = new UserDaoImpl();
      ((UserDaoImpl)userDao).setEntityManager(em);
      
      UserService userService = new UserServiceImpl();
      ((UserServiceImpl)userService).setUserDao(userDao);
      
      UserVO user = null;
      
      try {
         user = userService.authenticateUser("ilyen_nincs.test", "wrong_test");
      } catch (ItmException e) {
         System.out.println(e);
      }
      
      System.out.println("Login: " + user);
      
      System.out.println("Done!");
   }

   /*public static void main(String[] args) {
      EntityManagerFactory emf = Persistence.createEntityManagerFactory("itm-persistence");

      EntityManager em = emf.createEntityManager();
      
      UserDao userDao = new UserDaoImpl();
      ((UserDaoImpl)userDao).setEntityManager(em);
      
      UserService userService = new UserServiceImpl();
      ((UserServiceImpl)userService).setUserDao(userDao);
      
      UserVO user = new UserVO();
      user.setName("registered.test");
      user.setPassword("secret_passw0rd");
      user.setEmail("user@registered.com");
      user.setAdmin(false);
      
      try {
         em.getTransaction().begin();
         userService.registerUser(user);
         em.getTransaction().commit();
      } catch (ItmException e) {
         System.out.println(e);
      }
      
      System.out.println("Done!");
   }*/
}
