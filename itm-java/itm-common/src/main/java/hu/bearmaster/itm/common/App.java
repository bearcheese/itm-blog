package hu.bearmaster.itm.common;

import hu.bearmaster.itm.common.dao.UserDao;
import hu.bearmaster.itm.common.dao.impl.UserDaoImpl;
import hu.bearmaster.itm.common.dao.model.UserDO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {

   public static void main(String[] args) {
      EntityManagerFactory emf = Persistence.createEntityManagerFactory("itm-persistence");

      EntityManager em = emf.createEntityManager();
      
      UserDao userDao = new UserDaoImpl();
      ((UserDaoImpl)userDao).setEntityManager(em);
      
      System.out.println("Finduser: " + userDao.findByUsername("timestamp"));
      
      for (UserDO user : userDao.findAll()) {
         System.out.println(user);
      }
      
      System.out.println("Total user count: " + userDao.countAll());
      
      System.out.println("Done!");
   }
}
