package com.josetechblog.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import com.google.gson.Gson;
import com.josetechblog.dao.ProductDaoImpl;
import com.josetechblog.model.Product;

@Path("/product")
public class ProductService {

	@GET
	@Path("/productdetails")
	@Produces("application/json")
	public String getProductDetails() {
		String productDetails = null;
		List<Product> productList = null;

		ProductDaoImpl productDaoImpl = new ProductDaoImpl();

		productList = productDaoImpl.getProductDetails();

		Gson gson = new Gson();
		productDetails = gson.toJson(productList);
		return productDetails;
	}

}
