package ro.alinvlad.webalinvlad.database;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Product> findAll() {
        return jdbcTemplate.query("select * from product", new ProductRowMapper());
    }

    public Product findById(Integer id) {
        return jdbcTemplate.query("select * from product where id = " + id, new ProductRowMapper()).get(0);
    }

    public void deleteProduct(Integer id) {
        jdbcTemplate.update("delete from product where id = ?", id);
    }

    public void saveProduct(String model, String about_product, double price) {
        jdbcTemplate.update("INSERT INTO product (id, model, about_product, category_id, price, quantity, picture, operating_system, battery, dxomark_score) VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                model, about_product, null, price, null, null, null, null, null
        );
    }

}
