package com.josetechblog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.josetechblog.model.Product;

public class ProductDaoImpl {

	public List<Product> getProductDetails() {

		List<Product> productData = new ArrayList<>();

		JDBCConnection jdbcConnection = new JDBCConnection();

		Connection connection = jdbcConnection.getConnnection();

		try {
			PreparedStatement ps = connection.prepareStatement(
					"select productCode,productName,productLine,productScale from classicmodels.products");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Product product = new Product();
				product.setProductCode(rs.getString("productCode"));
				product.setProductLine(rs.getString("productName"));
				product.setProductName(rs.getString("productLine"));
				product.setProductScale(rs.getString("productScale"));
				productData.add(product);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return productData;
	}

}
