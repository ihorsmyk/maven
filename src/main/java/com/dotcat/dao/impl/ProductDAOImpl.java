package com.dotcat.dao.impl;

import com.dotcat.config.MySQLConnector;
import com.dotcat.dao.ProductDAO;
import com.dotcat.model.Product;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    @Override
    public int create(Product product) {
        int status = 400;
        String SQL = "INSERT INTO product(name, price) VALUES (?, ?)";
        try (
                Connection connection = MySQLConnector.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        ) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setBigDecimal(2, product.getPrice());

            preparedStatement.execute();
            status = 201;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

    @Override
    public Product getById(long id) {
        Product product = null;
        String SQL = "SELECT * FROM product WHERE id = ?";

        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL);
        ) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                product = new Product();
                product.setId(id);
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getBigDecimal(("price")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public int update(Product product) {
        int status = 400;
        String SQL = "UPDATE product SET name=?, price=? WHERE id=?";

        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL);
        ) {
            statement.setLong(3, product.getId());
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            statement.execute();
            status = 200;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public int deleteById(long id) {
        int status = 400;
        String SQL = "DELETE FROM product WHERE id=?";

        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL);
        ) {
            statement.setLong(1, id);
            statement.execute();
            status = 202;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
}
