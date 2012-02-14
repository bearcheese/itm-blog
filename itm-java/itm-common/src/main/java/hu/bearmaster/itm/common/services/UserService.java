package hu.bearmaster.itm.common.services;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.bearmaster.itm.common.exception.ItmException;
import hu.bearmaster.itm.common.model.UserVO;

public interface UserService {
   
   UserVO getUser(String username);
   
   UserVO getUser(Long id);
   
   List<UserVO> listUsers();
   
   @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {ItmException.class})
   UserVO registerUser(UserVO user) throws ItmException;
   
   UserVO authenticateUser(String username, String password) throws ItmException;

}