package hu.bearmaster.itm.common.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


import hu.bearmaster.itm.common.dao.GenericDao;

public abstract class GenericDaoImpl<T, ID> implements GenericDao<T, ID> {
   
   protected static final String FIND_ALL_QUERY_TEMPLATE = "select o from %s o";
   protected static final String COUNT_ALL_QUERY_TEMPLATE = "select count(o) from %s o";

   protected EntityManager em;

   private Class<T> type;
   
   private String findAllQuery;
   private String countAllQuery;
   
   public EntityManager getEntityManager() {
      return em;
   }
   
   @PersistenceContext
   public void setEntityManager(EntityManager em) {
      this.em = em;
   }

   @SuppressWarnings("unchecked")
   public GenericDaoImpl() {
      Type t = getClass().getGenericSuperclass();
      ParameterizedType pt = (ParameterizedType) t;
      type = (Class<T>) pt.getActualTypeArguments()[0];
      initQueries();
   }
   
   protected void initQueries() {
      findAllQuery = String.format(FIND_ALL_QUERY_TEMPLATE, type.getSimpleName());
      countAllQuery = String.format(COUNT_ALL_QUERY_TEMPLATE, type.getSimpleName());
   }

   @Override
   public T create(T t) {
      em.persist(t);
      return t;
   }
   
   @Override
   public T find(ID id) {
      return em.find(type, id);
   }
   
   @Override
   public List<T> findAll() {            
      TypedQuery<T> query = em.createQuery(findAllQuery, type);

      return query.getResultList();
   }
   
   @Override
   public long countAll() {      
      TypedQuery<Long> query = em.createQuery(countAllQuery, Long.class);

      return query.getSingleResult();
   }

   @Override
   public T update(T t) {
      return em.merge(t);
   }
   
   @Override
   public void delete(ID id) {
      em.remove(em.getReference(type, id));
   }
}
