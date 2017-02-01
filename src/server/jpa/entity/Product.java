package server.jpa.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

/**
 * @author Arsenii
 */
@Entity
@Table(name = "Product")
public class Product implements Serializable {

    private static final long serialVersionUID = 235285204444780405L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Integer id;

    @Column(name = "category")
    private String category;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private long price;

    @Column(name = "date")
    private Calendar date;

    @Column(name = "location")
    private String location;

    public Product(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (price != product.price) return false;
        if (category != null ? !category.equals(product.category) : product.category != null) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (date != null ? !date.equals(product.date) : product.date != null) return false;
        return location != null ? location.equals(product.location) : product.location == null;
    }

    @Override
    public int hashCode() {
        int result = category != null ? category.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (price ^ (price >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return  "category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", date=" + date.get(Calendar.YEAR)+"."+date.get(Calendar.DAY_OF_MONTH)+"."+date.get(Calendar.MONTH)+
                ", location='" + location;
    }
}

