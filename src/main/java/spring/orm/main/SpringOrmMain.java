package spring.orm.main;

import java.util.Arrays;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;

import spring.orm.model.Product;
import spring.orm.service.ProductService;

public class SpringOrmMain {
	
	public static void main(String[] args) {
		
		//Create Spring application context
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/spring.xml");
		
		//Get service from context. (service's dependency (ProductDAO) is autowired in ProductService)
		ProductService productService = ctx.getBean(ProductService.class);
		
		//Do some data operation
		
		productService.add(new Product(1, "Test 1"));
		productService.add(new Product(2, "Test 2"));
		
		System.out.println("listAll: " + productService.listAll());
		
		//Test transaction rollback (duplicated key)
		
		try {
			productService.addAll(Arrays.asList(new Product(3, "Book"), new Product(4, "Soap"), new Product(1, "Computer")));
		} catch (DataAccessException dataAccessException) {
		}
		
		//Test element list after rollback
		System.out.println("listAll: " + productService.listAll());
		
		ctx.close();
		
	}
}
