package server.ifaces;

import server.jpa.entity.Product;

import javax.ejb.Remote;
import java.util.List;

/**
 * @author Arsenii
 */
@Remote
public interface Manager {

    Product getById(Integer i);

    List<Product> getAll();

    void add(Product o);

    void delete(Product o);
}
