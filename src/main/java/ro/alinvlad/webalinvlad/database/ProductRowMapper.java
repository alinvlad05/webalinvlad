package ro.alinvlad.webalinvlad.database;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setModel(resultSet.getString("model"));
        product.setAbout_product(resultSet.getString("about_product"));
        product.setPrice(resultSet.getDouble("price"));
        product.setQuantity(resultSet.getInt("quantity"));
        product.setPicture(resultSet.getString("picture"));
        product.setOperating_system(resultSet.getString("operating_system"));
        product.setBattery(resultSet.getString("battery"));
        product.setDxomark_score(resultSet.getInt("dxomark_score"));

        return product;
    }
}
