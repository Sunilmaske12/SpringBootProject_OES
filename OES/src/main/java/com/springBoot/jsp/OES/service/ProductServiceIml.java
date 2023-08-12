package com.springBoot.jsp.OES.service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBoot.jsp.OES.dao.CategoryRepository;
import com.springBoot.jsp.OES.dao.ProductRepository;
import com.springBoot.jsp.OES.entity.Category;
import com.springBoot.jsp.OES.entity.Product;

@Service
public class ProductServiceIml implements ProductService{
	
	@Autowired
	ProductRepository theProductRepository;
	
	@Autowired
	CategoryRepository theCategoryRepository;
	
	@Override
	public List<Product> getAllProduct() {
		List<Product> allProduct =theProductRepository.findAll(); 
		return allProduct;
	}

	@Override
	public List<Category> getAllCategories() {
		List<Category> category= theCategoryRepository.findAll();
		return category;
	}

	@Override
	public List<Product> getProductByCategory(int i) {
		return theProductRepository.findBycid(i);
	}

	@Override
	public Product getProductById(int id) {
		Optional<Product> product = theProductRepository.findById(id);
		Product theProduct = product.get();
		return theProduct;
	}

	@Override
	public void deleteUserById(int productId) {
		theProductRepository.deleteById(productId);		
	}

	@Override
	public void deleteCategoryById(int cid) {
		theCategoryRepository.deleteById(cid);
		
	}

	@Override
	public float[] getCategoryWiseProductionPer() {
		long[] allCatProduction=new long[4];
			allCatProduction[0] =  theProductRepository.getProductionByCatId(12);
			allCatProduction[1] =theProductRepository.getProductionByCatId(10);
			allCatProduction[2] =theProductRepository.getProductionByCatId(15);
			allCatProduction[3] =theProductRepository.getProduction();
		float[] allCatPer=new float[4];
		allCatPer[0] = (float) (((double)allCatProduction[0]/(double)allCatProduction[3])*100); //appliances
		allCatPer[1] = (float) (((double)allCatProduction[1]/(double)allCatProduction[3])*100);//fan
		allCatPer[2] = (float) (((double)allCatProduction[2]/(double)allCatProduction[3])*100);//tool
		allCatPer[3] = 100 -allCatPer[0]-allCatPer[1]-allCatPer[2]; //other
		return allCatPer;
	}

	@Override
	public String getTotalProduction() {
		
		NumberFormat formatter = NumberFormat. getCurrencyInstance(new Locale("en", "IN")); 
		return formatter.format(theProductRepository.getProduction());
	}

	@Override
	public List<Product> getRecentProducts() {
		List<Product> p1= theProductRepository.findAll();
		List<Product> p2=new ArrayList<Product>();
		for(int i=p1.size()-4; i<p1.size(); i++) {
			p2.add(p1.get(i));
		}
		return p2;
	}

	
	
}
