package hu.bearmaster.itm.common.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import hu.bearmaster.itm.common.dao.UserDao;
import hu.bearmaster.itm.common.dao.model.UserDO;
import hu.bearmaster.itm.common.exception.ItmException;
import hu.bearmaster.itm.common.exception.ItmException.ErrorCode;
import hu.bearmaster.itm.common.model.UserVO;
import hu.bearmaster.itm.common.services.UserService;

public class UserServiceImpl implements UserService {

   private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
   private static final String DEFAULT_SECRET = "To0+M4ny@Secr3ts!";
   
   
   private StandardPasswordEncoder passwordEncoder;

   private UserDao userDao;
   
   public UserServiceImpl() {
      this.passwordEncoder = new StandardPasswordEncoder(DEFAULT_SECRET); 
   }
   
   public UserServiceImpl(final String secret) {
      this.passwordEncoder = new StandardPasswordEncoder(secret); 
   }

   public void setUserDao(final UserDao dao) {
      this.userDao = dao;
   }

   @Override
   public UserVO getUser(final String username) {
      return userDao.findByUsername(username).getVo();
   }

   @Override
   public UserVO getUser(final Long id) {
      return userDao.find(id).getVo();
   }

   @Override
   public List<UserVO> listUsers() {
      List<UserVO> userVoList = new ArrayList<UserVO>();

      List<UserDO> userDoList = userDao.findAll();

      for (UserDO userDo : userDoList) {
         userVoList.add(userDo.getVo());
      }

      return userVoList;
   }

   @Override
   public UserVO registerUser(final UserVO user) throws ItmException {

      try {
         UserDO userDo = new UserDO();
         userDo.setVo(user);

         String salt = generateSalt(userDo);
         String hashedPassword = passwordEncoder.encode(user.getPassword() + salt); //if we have already salt lets use it, even if Spring salts the password as well
         user.setPassword("");
         user.setPasswordConfirmation("");
         userDo.setHashedPassword(hashedPassword);
         userDo.setSalt(salt);

         return userDao.create(userDo).getVo();
         
      } catch (Exception e) { //TODO Distinguish between different failure reasons (username or email address already exists or other)
         LOGGER.warn("Can't register user: {}", user, e);
         throw new ItmException(ErrorCode.USER_REGISTRATION_FAILED, "Can't register user: " + user, e);
      }
   }

   private String generateSalt(final UserDO userDo) {
      return Integer.toHexString(userDo.hashCode()) + Long.toHexString(new Random(System.currentTimeMillis()).nextLong());
   }

   @Override
   public UserVO authenticateUser(final String username, final String password) throws ItmException {
      
      try {
         UserDO userDo = userDao.findByUsername(username);

         if (passwordEncoder.matches(password + userDo.getSalt(), userDo.getHashedPassword())) {
                        
            return userDo.getVo();
         } // else the authentication failed, exception will be raised at the
           // end of this method
      } catch (NoResultException e) {
         LOGGER.info("No user found with name {}", username);
         throw new ItmException(ErrorCode.USER_AUTHENTICATION_FAILED, "Can't authenticate user: " + username, e);
      }

      throw new ItmException(ErrorCode.USER_AUTHENTICATION_FAILED, "Can't authenticate user: " + username);
   }
}