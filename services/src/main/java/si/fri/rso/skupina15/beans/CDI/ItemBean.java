package si.fri.rso.skupina15.beans.CDI;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.rso.skupina15.entities.Item;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class ItemBean {
    private Logger log = Logger.getLogger(ItemBean.class.getName());

    @PostConstruct
    private void init(){
        log.info("Inicializacija " + ItemBean.class.getSimpleName());
    }

    @PreDestroy
    private void destroy(){
        log.info("Deinicializacija " + ItemBean.class.getSimpleName());
    }

    @PersistenceContext(unitName = "razpis-jpa")
    private EntityManager em;

    public List<Item> findAllProfesor(QueryParameters query) {
        List<Item> items = JPAUtils.queryEntities(em, Item.class, query);
        return items;
    }
}
