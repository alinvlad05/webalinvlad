package ro.alinvlad.webalinvlad.database;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class OrderDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    ProductDAO productDAO;

    public void newOrder(int userId, Map<Integer, Integer> productsToQuantity) {
        //calculam valoarea total a comenzii
        double totalPrice = 0;
        for (Map.Entry<Integer, Integer> entry : productsToQuantity.entrySet()) {
            Product product = productDAO.findById(entry.getKey());
            totalPrice = totalPrice + (product.getPrice() * entry.getValue());
        }

        jdbcTemplate.update("insert into orders values(null, ?,?,?)", userId, null, totalPrice);

        int orderId = jdbcTemplate.queryForObject("select max(id) from orders", Integer.class);

        for (Map.Entry<Integer, Integer> entry : productsToQuantity.entrySet()) {
            jdbcTemplate.update("insert into orders_product values(null, ?,?,?)", orderId, entry.getKey(), entry.getValue());
        }
    }

    public List<Order> findOrderForUser(Integer userId) {
        return jdbcTemplate.query("select * from orders where user_id =" + userId + " order by id desc", new OrderRowMapper());
    }
}
