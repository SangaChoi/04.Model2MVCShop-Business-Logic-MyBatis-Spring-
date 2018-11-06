package com.model2.mvc.service.purchase.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })
public class PurchaseServiceTest {
	
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	//@Test
	public void testAddPurchase() throws Exception{
		
		User user=new User();
		user.setUserId("user01");
		user.setAddr("인천시");
		user.setUserName("최상아");
		user.setPhone("010-1234-5678");
		
		Product product=new Product();
		product.setProdNo(10151);
		
		Purchase purchase=new Purchase();
		purchase.setBuyer(user);
		purchase.setDivyAddr(user.getAddr());
		purchase.setDivyDate("20181231");
		purchase.setDivyRequest("오잉");
		purchase.setPaymentOption("2");
		purchase.setPurchaseProd(product);
		purchase.setReceiverName(user.getUserName());
		purchase.setReceiverPhone(user.getPhone());
		purchase.setTranCode("1");
		
		purchaseService.addPurchase(purchase);
		
		Assert.assertEquals("user01", purchase.getBuyer().getUserId());
		Assert.assertEquals(10151, purchase.getPurchaseProd().getProdNo());
		Assert.assertEquals("1", purchase.getTranCode());		
	}
	
	//@Test
	public void testGetPurchase() throws Exception{
		
		Purchase purchase=new Purchase();
		purchase=purchaseService.getPurchase(10180);
		
		System.out.println(purchase);	
	}
	
	//@Test
	public void testUpdatePurchase() throws Exception{
		
		Purchase purchase=purchaseService.getPurchase(10180);
		
		purchase.setPaymentOption("2");
		purchase.setReceiverName("555수정");
		purchase.setReceiverPhone("010-8888-8888");
		purchase.setDivyAddr("수정해씀시");
		purchase.setDivyRequest("리퀘스트 수정");
		purchase.setDivyDate("20190101");
		
		purchaseService.updatePurcahse(purchase);
		
		purchase=purchaseService.getPurchase(10180);
		System.out.println(purchase);	
	}
	
	@Test
	public void testGetPurchaseList() throws Exception{
		
		Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = purchaseService.getPurchaseList(search, "user02");
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	//Assert.assertEquals(3, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	}
	
	//@Test
	public void testUpdateTranCodeByProd() throws Exception{
		
		Product product=new Product();
		product.setProdNo(10149);
		
		Purchase purchase=new Purchase();
		purchase.setTranCode("2");
		purchase.setPurchaseProd(product);
		
		purchaseService.updateTranCode(purchase);	
	}
	
	//@Test
	public void testUpdateTranCode() throws Exception{
		
		Purchase purchase=new Purchase();
		purchase.setTranNo(10183);
		purchase.setTranCode("3");
		
		purchaseService.updateTranCode(purchase);
	}
	
}
