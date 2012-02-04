package hu.bearmaster.itm.common;

import java.util.Collection;

import hu.bearmaster.itm.common.dao.model.UserDO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class App {

   public static void main(String[] args) {
      EntityManagerFactory emf = Persistence.createEntityManagerFactory("itm-persistence");

      EntityManager em = emf.createEntityManager();

      try {
         UserDO newUser = new UserDO("java-őű-teszt", "majom@bananfa.cc", "talald_ki", "sok_so", false);
         em.getTransaction().begin();
         em.persist(newUser);         
         em.getTransaction().commit();
      } catch (Exception e) {
         e.printStackTrace();
         em.getTransaction().rollback();
      }

      Query query = em.createQuery("SELECT u FROM UserDO u");
      Collection<UserDO> users = (Collection<UserDO>) query.getResultList();

      System.out.println("List of users:");

      for (UserDO u : users) {
         System.out.println(u);
      }

      System.out.println("Done!");
   }
}
