package com.zy.dao;

import java.util.List;
import java.util.Map;

import com.zy.domain.Category;
import com.zy.domain.Product;

public interface ProductMapper {
	
	public Product queryProduct(String id);

	public List<Product> queryProducts();
	
	public int insertProduct(Product product);
	public int insertProductCategory(Map<String, Object> map);
	
	public int deleteProduct(String id);
	public int deleteProductCategory(String pid);
	
	public int updateProduct(Product product);
	
	public List<Category> queryCategories(Map<String, Object> map);
	
	public List<Category> queryAllCategory();
}
