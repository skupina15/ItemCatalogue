package si.fri.rso.skupina15.beans.CDI;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.rso.skupina15.entities.Item;

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
public class ItemBean {
    private Logger log = Logger.getLogger(ItemBean.class.getName());

    @PostConstruct
    private void init(){
        log.info("Initialization " + ItemBean.class.getSimpleName());
    }

    @PreDestroy
    private void destroy(){
        log.info("Deinitialization"+ ItemBean.class.getSimpleName());
    }

    @PersistenceContext(unitName = "climb-jpa")
    private EntityManager em;

    public List<Item> findAllItems(QueryParameters query) {
        List<Item> items = JPAUtils.queryEntities(em, Item.class, query);
        return items;
    }

    public Long itemsCount(QueryParameters query) {
        Long count = JPAUtils.queryEntitiesCount(em, Item.class, query);
        return count;
    }

    @Transactional
    public Item createItem(Item item){
        if(item != null) {
            if(item.getId_item() == null) {
                log.info("Can't create new item. ID is not defined.");
                return null;
            }
            if(item.getTitle() == null) {
                log.info("Can't create new item. Name is not defined.");
                return null;
            }
            if(item.getDaily_price() == null) {
                log.info("Can't create new item. Daily price is not defined.");
                return null;
            }
            if(item.getDescription() == null) {
                log.info("Can't create new item. Description is not defined.");
                return null;
            }
            if(item.getOwner() == null) {
                log.info("Can't create new item. Owner is not defined.");
                return null;
            }
            if(item.getTag() == null) {
                log.info("Can't create new item. Tags are not defined.");
                return null;
            }
            em.persist(item);
        }
        return item;
    }

    public Item findItem(int id_item){
        try {
            return em.find(Item.class, id_item);
        }
        catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public Item updateItem(int id_item, Item item){
        Item i = findItem(id_item);
        if(i == null){
            return null;
        }
        item.setId_item(i.getId_item());
        em.merge(item);
        return item;
    }

    @Transactional
    public int deleteItem(int id_item){
        Item i = findItem(id_item);
        if(i != null){
            em.remove(i);
        }
        return id_item;
    }
}
