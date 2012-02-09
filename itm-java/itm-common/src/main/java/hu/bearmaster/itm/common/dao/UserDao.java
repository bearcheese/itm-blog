package hu.bearmaster.itm.common.dao;

import hu.bearmaster.itm.common.dao.model.UserDO;

public interface UserDao extends GenericDao<UserDO, Long> {
   
   UserDO findByUsername(String name);

}
