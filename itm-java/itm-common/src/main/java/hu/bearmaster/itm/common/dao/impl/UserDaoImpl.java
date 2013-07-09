package hu.bearmaster.itm.common.dao.impl;

import hu.bearmaster.itm.common.dao.UserDao;
import hu.bearmaster.itm.common.dao.model.UserDO;

import javax.persistence.TypedQuery;

public class UserDaoImpl extends GenericDaoImpl<UserDO, Long> implements UserDao {

   @Override
   public UserDO findByUsername(String username) {
      TypedQuery<UserDO> query = getEntityManager().createNamedQuery("findByUsername", UserDO.class);

      query.setParameter("name", username);

      return query.getSingleResult();

   }

}