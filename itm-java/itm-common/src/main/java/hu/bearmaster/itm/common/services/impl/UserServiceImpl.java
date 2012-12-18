package hu.bearmaster.itm.common.services.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.bearmaster.itm.common.dao.UserDao;
import hu.bearmaster.itm.common.dao.model.UserDO;
import hu.bearmaster.itm.common.exception.ItmException;
import hu.bearmaster.itm.common.exception.ItmException.ErrorCode;
import hu.bearmaster.itm.common.model.UserVO;
import hu.bearmaster.itm.common.services.UserService;

public class UserServiceImpl implements UserService {

   private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

   private UserDao userDao;

   public void setUserDao(UserDao dao) {
      this.userDao = dao;
   }

   @Override
   public UserVO getUser(String username) throws ItmException {
      try {
		return userDao.findByUsername(username).getVo();
	} catch (Exception e) {		
		throw new ItmException(ErrorCode.USER_NOT_FOUND, "User not found with name " + username, e);
	}
   }

   @Override
   public UserVO getUser(Long id) throws ItmException {
      try {
		return userDao.find(id).getVo();
	} catch (Exception e) {
		throw new ItmException(ErrorCode.USER_NOT_FOUND, "User not found with id " + id, e);
	}
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
   public UserVO registerUser(UserVO user) throws ItmException {

      try {
         UserDO userDo = new UserDO();
         userDo.setVo(user);

         String salt = generateSalt(userDo);
         String hashedPassword = generateHashedPassword(user.getPassword(), salt);
         user.setPassword("");
         userDo.setHashedPassword(hashedPassword);
         userDo.setSalt(salt);

         return userDao.create(userDo).getVo();
      } catch (NoSuchAlgorithmException e) {
         logger.error("SHA-256 digest algorith is not available!", e);
         throw new ItmException(ErrorCode.USER_REGISTRATION_FAILED, "Can't register user: " + user, e);
      } catch (UnsupportedEncodingException e) {
         logger.error("UTF-8 is not supported encoding!", e);
         throw new ItmException(ErrorCode.USER_REGISTRATION_FAILED, "Can't register user: " + user, e);
      } catch (Exception e) {
         logger.warn("Can't register user: {}", user, e);
         throw new ItmException(ErrorCode.USER_REGISTRATION_FAILED, "Can't register user: " + user, e);
      }
   }

   private String generateSalt(UserDO userDo) {
      return String.valueOf(userDo.hashCode()) + Long.toHexString(new Random(System.currentTimeMillis()).nextLong());
   }

   private String generateHashedPassword(String cleanText, String salt)
         throws NoSuchAlgorithmException, UnsupportedEncodingException {

      String inputString = cleanText + "Pr4tty_W0m@n" + salt; // TODO refactor secret element of hash

      logger.debug("Input string: '{}'", inputString);

      MessageDigest md = MessageDigest.getInstance("SHA-256");

      byte[] hash = md.digest(inputString.getBytes("UTF-8"));

      StringBuilder sb = new StringBuilder();
      for (byte b : hash) {
         sb.append(String.format("%02x", b));
      }
      logger.debug("Output hash: '{}'", sb.toString());
      return sb.toString();

   }

   @Override
   public UserVO authenticateUser(String username, String password) throws ItmException {
      
      try {
         UserDO userDo = userDao.findByUsername(username);
         String hashedPassword = generateHashedPassword(password,
               userDo.getSalt());

         logger.debug("Calculated hashed password: '{}'", hashedPassword);

         if (hashedPassword.equals(userDo.getHashedPassword())) {
            return userDo.getVo();
         } // else the authentication failed, exception will be raised at the
           // end of this method
      } catch (NoSuchAlgorithmException e) {
         logger.error("SHA-256 digest algorit is not available!", e);
         throw new ItmException(ErrorCode.USER_AUTHENTICATION_FAILED, "Can't authenticate user: " + username, e);
      } catch (UnsupportedEncodingException e) {
         logger.error("UTF-8 is not supported encoding!", e);
         throw new ItmException(ErrorCode.USER_AUTHENTICATION_FAILED, "Can't authenticate user: " + username, e);
      } catch (NoResultException e) {
         throw new ItmException(ErrorCode.USER_AUTHENTICATION_FAILED, "Can't authenticate user: " + username, e);
      }

      throw new ItmException(ErrorCode.USER_AUTHENTICATION_FAILED,
            "Can't authenticate user: " + username);
   }
}