package ro.alinvlad.webalinvlad.database;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt("id"));
        order.setAddress(resultSet.getString("address"));
        order.setPrice(resultSet.getDouble("total_price"));
        order.setUserId(resultSet.getInt("user_id"));

        return order;
    }
}
