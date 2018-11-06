package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })
public class ProductServiceTest {
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	//@Test
	public void testAddProduct() throws Exception{
		
		Product product=new Product();
		product.setProdName("�׽�Ʈ");
		product.setProdDetail("������");
		product.setManuDate("20000101");
		product.setPrice(7777);
		product.setFileName("���ϳ���");
		
		productService.addProduct(product);
		
		Assert.assertEquals("�׽�Ʈ", product.getProdName());
		Assert.assertEquals("������", product.getProdDetail());
		Assert.assertEquals("20000101", product.getManuDate());
		Assert.assertEquals(7777, product.getPrice());
		Assert.assertEquals("���ϳ���", product.getFileName());
	}
	
	//@Test
	public void testGetProduct() throws Exception{
		
		Product product=new Product();
		product=productService.getProduct(10120);
		
		System.out.println(product);
	}
	
	//@Test
	public void testUpdateProduct() throws Exception{
		
		Product product=productService.getProduct(10120);
		Assert.assertNotNull(product);
		
		product.setProdName("����");
		product.setProdDetail("����������");
		product.setManuDate("19000101");
		product.setPrice(100);
		product.setFileName("�������ϳ���");
		
		productService.updateProduct(product);
		
		product=productService.getProduct(10120);
		System.out.println(product);
	}
	
	//@Test
	public void testGetProductListAll() throws Exception{
		
		Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = productService.getProductList(search);
	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	}
	
	//@Test
	public void testGetProductListByProdNo() throws Exception{
		
		Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("10108");
	 	Map<String,Object> map = productService.getProductList(search);
		
	 	List<Object> list = (List<Object>)map.get("list");
	 	
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
		
	}
	
	//@Test
	 public void testGetProductListByProdName() throws Exception{
		
		Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword("��");
	 	Map<String,Object> map = productService.getProductList(search);
		
	 	List<Object> list = (List<Object>)map.get("list");
	 	
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
		
	}
	 
	@Test
		 public void testGetProductListByPrice() throws Exception{
			
			Search search = new Search();
		 	search.setCurrentPage(1);
		 	search.setPageSize(3);
		 	search.setSearchCondition("2");
		 	search.setSearchKeyword("7777");
		 	Map<String,Object> map = productService.getProductList(search);
			
		 	List<Object> list = (List<Object>)map.get("list");
		 	
		 	System.out.println(list);
		 	
		 	Integer totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
			
		}
}
