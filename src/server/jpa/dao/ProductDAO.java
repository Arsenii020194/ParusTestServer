package server.jpa.dao;

import com.google.gson.Gson;
import server.jpa.entity.Product;

import javax.ejb.Stateless;
import javax.json.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.util.List;

/**
 * @author Arsenii
 */
@ServerEndpoint("/Product")
@Stateless(mappedName = "ProductBean", name = "ProductBean")
public class ProductDAO implements Serializable {

    @PersistenceContext(unitName = "NewPersistenceUnit")
    EntityManager em;

    @OnMessage
    public void onMessage(String message, Session session) throws IOException, InterruptedException {

        JsonReader jsonReader = Json.createReader(new StringReader(message));
        JsonObject messageJson = jsonReader.readObject();
        String act = messageJson.getString("action");
        switch (act) {

            case "delete": {
                delete(getById(messageJson.getInt("id")));
                break;
            }
            case "add": {
                String obj = messageJson.getString("object");
                Gson gson = new Gson();
                Product p = gson.fromJson(obj, Product.class);
                add(p);
                break;
            }
            case "getAll": {
                List<Product> allProducts = getAll();
                Gson gson = new Gson();
                JsonObject out = Json.createObjectBuilder().add("action", "getAll").add("objects", gson.toJson(allProducts)).build();
                session.getBasicRemote().sendText(out.toString());
                break;
            }
        }
    }

    public Product getById(Integer i) {
        return em.find(Product.class, i);
    }

    public List<Product> getAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Product.class));
        return em.createQuery(cq).getResultList();
    }

    public void add(Product o) {
        em.merge(o);
    }

    public void delete(Product o) {
        em.remove(em.merge(o));
    }

    @OnOpen
    public void onOpen() {
        System.out.println("Client connected");
    }

    @OnClose
    public void onClose() {
        System.out.println("Connection closed");
    }

}