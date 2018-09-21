package com.zy.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.dao.ProductMapper;
import com.zy.domain.Category;
import com.zy.domain.Product;

@Service
public class ProductService {

	@Autowired
	private ProductMapper mapper;
	
	public Product queryProduct(String id) {
		Product product = mapper.queryProduct(id);
		return product;
	}
	
	public List<Product> queryProducts(){
		List<Product> products = mapper.queryProducts();
		return products;
	}
	
	public int insertProduct(Product product) {
		int result = mapper.insertProduct(product);//1.��Ʒ�����
		if(result>0) {//2.�м�����
			String cids = product.getCids();
			if(cids!=null) {
				String[] ids = cids.split(",");
				Map<String, Object> map=new HashMap<>();
				map.put("pid", product.getId());
				map.put("cids", ids);
				mapper.insertProductCategory(map);
			}
		}
		
		return result;
	}
	
	public int deleteProduct(String id) {
		mapper.deleteProductCategory(id);//1.�м��ɾ��
		int result=mapper.deleteProduct(id);//2.��Ʒ��ɾ��
		
		return result;
	}
	
	public int updateProduct(Product product) {
		mapper.deleteProductCategory(product.getId());//1.�м��ɾ��
		//2.�м�����
		String cids = product.getCids();
		if(cids!=null) {
			String[] ids = cids.split(",");
			Map<String, Object> map=new HashMap<>();
			map.put("pid", product.getId());
			map.put("cids", ids);
			mapper.insertProductCategory(map);
		}
		
		return mapper.updateProduct(product);//3.��Ʒ���޸�
	}
	
	public List<Category> queryAllCategories(){
		return mapper.queryAllCategory();
	}
}
