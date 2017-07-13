package gob.osinergmin.consulta.service.dao.impl;

import gob.osinergmin.consulta.service.dao.CrudDAO;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("crudDAO")
public class CrudDAOImpl implements CrudDAO{

    private static final Logger log = LoggerFactory.getLogger(CrudDAOImpl.class);
    @PersistenceContext(unitName = "gob.osinergmin.consultas:default")
    private EntityManager em;        

    @Override
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override   
    public <T> T create(T t) {
        log.info("inserting " + t.getClass().getName());
        this.em.persist(t);
        this.em.flush();
        this.em.refresh(t);
        return t;
    }

    @Override
    public <T> T find(Object id, Class<T> type) {
        log.info("finding " + type.getName() + " by id=" + id.toString());
        return (T) this.em.find(type, id);
    }


    @Override
    public void delete(Object t) {
        log.info("deleting " + t.getClass().getName());
//        Object ref = this.em.getReference(t.getClass(), t);
        this.em.remove(t);
    }
    
    @Transactional
    public void deleteAll( Object t ) {
	log.info("deleting " + t.getClass().getName());
	this.em.remove(em.merge(t));
    }
    
    @Override
    public <T> T deleteCompuesto(Class<T> type,Object o) {
        log.info("deleting clave compuesta" + type.getClass().getName());
        Object ref = this.em.find(type, o);
        this.em.remove(ref);  
        //this.em.flush();
        return null;     
    }
   
    @Override
    public <T> T update(T t) {
        log.info("updating " + t.getClass().getName());
        return (T) this.em.merge(t);
        
    }

    @Override
    public List<Object> findByNamedQuery(String namedQueryName) {
        log.info("finding by named query :" + namedQueryName);
        return this.em.createNamedQuery(namedQueryName).getResultList();
    }

    @Override
    public List<Object> findByNamedQuery(String namedQueryName,
            Map<String, Object> parameters) {

        return findByNamedQuery(namedQueryName, parameters, 0);
    }

    @Override
    public List<Object> findByNamedQuery(String namedQueryName, int resultLimit) {
        log.info("finding by named query :" + namedQueryName);
        return this.em.createNamedQuery(namedQueryName).
                setMaxResults(resultLimit).
                getResultList();
    }

    @Override
    public List<Object> findByNamedQuery(String namedQueryName,
            Map<String, Object> parameters, int resultLimit) {
        Set<Entry<String, Object>> rawParameters = parameters.entrySet();
        log.info("finding by named query :" + namedQueryName);
        Query query = this.em.createNamedQuery(namedQueryName);
        if (resultLimit > 0) {
            query.setMaxResults(resultLimit);
        }
        for (Entry<String, Object> entry : rawParameters) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }

    @Override
    public List<Object> findByNativeQuery(String nativeQuery, Map<String, Object> parameters) {
        return findByNativeQuery(nativeQuery, parameters, 0);
    }

    @Override
    public List<Object> findByNativeQuery(String nativeQuery, Map<String, Object> parameters, int resultLimit) {
        Set<Entry<String, Object>> rawParameters = parameters.entrySet();
        log.info("finding by natived query :" + nativeQuery);
        Query query = this.em.createNativeQuery(nativeQuery);
        if (resultLimit > 0) {
            query.setMaxResults(resultLimit);
        }
        for (Entry<String, Object> entry : rawParameters) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }
    
    @Override
    public Integer actualizarByNamedQuery(String namedQueryName,
            Map<String, Object> parameters) {
        Integer registrosActualizados=new Integer(0);
	Set<Entry<String, Object>> rawParameters = parameters.entrySet();
        log.info("actualizando by named query :" + namedQueryName);
        Query query = this.em.createNamedQuery(namedQueryName);       
        for (Entry<String, Object> entry : rawParameters) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        registrosActualizados =query.executeUpdate();
        return registrosActualizados;
    }
}
