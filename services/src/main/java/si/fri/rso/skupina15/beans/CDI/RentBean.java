package si.fri.rso.skupina15.beans.CDI;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.rso.skupina15.entities.Rent;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class RentBean {
    private Logger log = Logger.getLogger(RentBean.class.getName());

    @PostConstruct
    private void init(){
        log.info("Initialization " + RentBean.class.getSimpleName());
    }

    @PreDestroy
    private void destroy(){
        log.info("Deinitialization"+ RentBean.class.getSimpleName());
    }

    @PersistenceContext(unitName = "climb-jpa")
    private EntityManager em;

    public List<Rent> findAllRent(QueryParameters query) {
        List<Rent> rent = JPAUtils.queryEntities(em, Rent.class, query);
        return rent;
    }

    public Long rentCount(QueryParameters query) {
        Long count = JPAUtils.queryEntitiesCount(em, Rent.class, query);
        return count;
    }

    @Transactional
    public Rent createRent(Rent rent){
        if(rent != null) {
            if(rent.getRegistration_date() == null) {
                log.info("Can't create new rent. Registration date is not defined.");
                return null;
            }
            if(rent.getPersone() == null) {
                log.info("Can't create new rent. Persone is not defined.");
                return null;
            }
            if(rent.getItem() == null) {
                log.info("Can't create new rent. Item is not defined.");
                return null;
            }
            em.persist(rent);
        }
        return rent;
    }

    public Rent findRent(int id_rent){
        try {
            return em.find(Rent.class, id_rent);
        }
        catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public Rent updateRent(int id_rent, Rent rent){
        Rent i = findRent(id_rent);
        if(i == null){
            return null;
        }
        rent.setId_rent(i.getId_rent());
        em.merge(rent);
        return rent;
    }

    @Transactional
    public int deleteRent(int id_rent){
        Rent i = findRent(id_rent);
        if(i != null){
            em.remove(i);
        }
        return id_rent;
    }
}
