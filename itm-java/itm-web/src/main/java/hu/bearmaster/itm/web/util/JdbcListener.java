package hu.bearmaster.itm.web.util;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdbcListener implements ServletContextListener {
   
   private static final Logger logger = LoggerFactory.getLogger(JdbcListener.class);

   @Override
   public void contextDestroyed(ServletContextEvent arg0) {
      // This manually deregisters JDBC driver, which prevents Tomcat 7 from
      // complaining about memory leaks
      Enumeration<Driver> drivers = DriverManager.getDrivers();
      while (drivers.hasMoreElements()) {
         Driver driver = drivers.nextElement();
         try {
            DriverManager.deregisterDriver(driver);
            logger.info(String.format("Deregistering jdbc driver: %s", driver));
         } catch (SQLException e) {
            logger.info(String.format("Error deregistering driver %s", driver), e);
         }
      }
   }

   @Override
   public void contextInitialized(ServletContextEvent arg0) {
      // do nothing
   }

}
