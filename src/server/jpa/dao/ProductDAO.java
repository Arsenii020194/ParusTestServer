package server.jpa.dao;

import server.ifaces.Manager;
import server.jpa.entity.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

/**
 * @author Arsenii
 */
@Stateless(mappedName="ProductBean", name = "ProductBean")
public class ProductDAO implements Manager, Serializable {

    @PersistenceContext(unitName = "NewPersistenceUnit")
    EntityManager em;

    @Override
    public Product getById(Integer i) {
        return em.find(Product.class, i);
    }

    @Override
    public List<Product> getAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Product.class));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public void add(Product o) {
        em.merge(o);
    }

    public void delete(Product o) {
        em.remove(em.merge(o));
    }

}