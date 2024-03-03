package org.jsp.MP.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.MP.dao.MerchantDao;
import org.jsp.MP.dao.ProductDao;
import org.jsp.MP.dto.Merchant;
import org.jsp.MP.dto.Product;

public class Merchant_Product {

	public static void main(String[] args) {
		
		MerchantDao dao=new MerchantDao();
		ProductDao dao1=new ProductDao();
		Scanner sc=new Scanner(System.in);
		boolean f=true;
		
		while(f) {
			System.out.println("<============== WELCOME TO MERCHANT_PRODUCT MANAGEMENT SYSTEM ==============>");
			System.out.println("<*********** 1.Save Merchant ***********>");
			System.out.println("<*********** 2.Update Merchant ***********>");
			System.out.println("<*********** 3.Find Merchant by Id ***********>");
			System.out.println("<*********** 4.Verify Merchant by phone & password ***********>");
			System.out.println("<*********** 5.Add Product ***********>");
			System.out.println("<*********** 6.Update Product ***********>");
			System.out.println("<*********** 7.Find Product by Merchant Id ***********>");
			System.out.println("<*********** 8.Filter Product by cost ***********>");
			System.out.println("<*********** 9.Fetch All the Merchant ***********>");
			System.out.println("<*********** 10.Fetch All the Product ***********>");
			System.out.println("<*********** 11.Exit... ***********>");
			
			switch(sc.nextInt()) {
			
			case 1: {
				System.out.println("Enter name, phone, email, gst, password to save: ");
				Merchant m=new Merchant();
				m.setName(sc.next());
				m.setPhone(sc.nextLong());
				m.setEmail(sc.next());
				m.setGst(sc.next());
				m.setPassword(sc.next());
				m=dao.saveMerchant(m);
				System.out.println("Merchant saved successfully..........");
				break;
			}
				
			case 2: {
				System.out.println("Enter Merchant id to update: ");
				int id=sc.nextInt();
				System.out.println("Enter name, phone, email, gst, password to save: ");
				Merchant m=new Merchant();
				m.setId(id);
				m.setName(sc.next());
				m.setPhone(sc.nextLong());
				m.setEmail(sc.next());
				m.setGst(sc.next());
				m.setPassword(sc.next());
				m=dao.updateMerchant(m);
				
				if(m!=null) {
					System.out.println("Merchant updated succesfully..........");
				}else {
					System.err.println("You have entered invalid id..........!!!");
				}
				break;
			}
				
			case 3: {
				System.out.println("Enter Merchant id to find: ");
				int id=sc.nextInt();
				Merchant m=dao.findBYId(id);
				
				if(m!=null) {
					System.out.println("Merchant found successfully...........");
					System.out.println("Merchant id: "+m.getId());
					System.out.println("Merchant name: "+m.getName());
					System.out.println("Merchant phone: "+m.getPhone());
					System.out.println("Merchant email: "+m.getEmail());
					System.out.println("Merchant gst: "+m.getGst());
					System.out.println("==========================");
				}else {
					System.err.println("You have entered invalid id..........!!!");
				}
				break;
			}
				
			case 4: {
				System.out.println("Enter phone & password to verify: ");
				long phone=sc.nextLong();
				String password=sc.next();
				Merchant m=dao.verifyMerchant(phone, password);
				
				if(m!=null) {
					System.out.println("Merchant found successfully...........");
					System.out.println("Merchant id: "+m.getId());
					System.out.println("Merchant name: "+m.getName());
					System.out.println("Merchant phone: "+m.getPhone());
					System.out.println("Merchant email: "+m.getEmail());
					System.out.println("Merchant gst: "+m.getGst());
					System.out.println("==========================");
				}else {
					System.err.println("You have entered invalid id..........!!!");
				}
				break;
			}
				
			case 5: {
				System.out.println("Enter Merchant id to save Product: ");
				int merchant_id=sc.nextInt();
				System.out.println("Enter name, brand, category, description, cost, image_url to save product: ");
				Product p=new Product();
				p.setName(sc.next());
				p.setBrand(sc.next());
				p.setCategory(sc.next());
				p.setDescription(sc.next());
				p.setCost(sc.nextDouble());
				p.setImage_url(sc.next());
				p=dao1.saveProduct(p, merchant_id);
				
				if(p!=null) {
					System.out.println("Product values saved successfully along with Merchant.......");
				}else {
					System.err.println("You have entered wrong merchant_id........!!!!");
				}
				break;
			}
				
			case 6: {
				System.out.println("Enter id to update Product: ");
				int id=sc.nextInt();
				System.out.println("Enter name, brand, category, description, cost, image_url to save product: ");
				Product p=new Product();
				p.setId(id);
				p.setName(sc.next());
				p.setBrand(sc.next());
				p.setCategory(sc.next());
				p.setDescription(sc.next());
				p.setCost(sc.nextDouble());
				p.setImage_url(sc.next());
				p=dao1.updateProduct(p);
				
				if(p!=null) {
					System.out.println("Product values saved successfully along with Merchant.......");
				}else {
					System.err.println("You have entered wrong merchant_id........!!!!");
				}
				break;
			}
				
			case 7: {
				System.out.println("Enter Merchant id to find Product: ");
				int merchant_id=sc.nextInt();
				List<Product> product=dao1.findByMerchantId(merchant_id);
				
				if(product.size()>0) {
					for(Product p:product) {
						System.out.println("Product found successfully..........");
						System.out.println("Product id: "+p.getId());
						System.out.println("product name: "+p.getName());
						System.out.println("Product brand: "+p.getBrand());
						System.out.println("Product category: "+p.getCategory());
						System.out.println("Product description: "+p.getDescription());
						System.out.println("Product Cost: "+p.getCost());
						System.out.println("Product Image_url: "+p.getImage_url());
						System.out.println("==================================");
					}
				}else {
					System.err.println("You have entered wrong merchant_id........!!!!");
				}
				break;
			}
				
			case 8: {
				List<Product> products=dao1.filter();
				for(Product p:products) {
					System.out.println("==========================");
					System.out.println("Product id: "+p.getId());
					System.out.println("product name: "+p.getName());
					System.out.println("Product brand: "+p.getBrand());
					System.out.println("Product category: "+p.getCategory());
					System.out.println("Product description: "+p.getDescription());
					System.out.println("Product Cost: "+p.getCost());
					System.out.println("Product Image_url: "+p.getImage_url());
				}
				break;
			}
			
			case 9: {
				List<Merchant> merchant=dao.fetchAll();
				for(Merchant m:merchant) {
					System.out.println("==========================");
					System.out.println("Merchant id: "+m.getId());
					System.out.println("Merchant name: "+m.getName());
					System.out.println("Merchant phone: "+m.getPhone());
					System.out.println("Merchant email: "+m.getEmail());
					System.out.println("Merchant gst: "+m.getGst());
				}
				break;
			}
			
			case 10: {
				List<Product> product=dao1.fetchAll();
				for(Product p:product) {
					System.out.println("==========================");
					System.out.println("Product id: "+p.getId());
					System.out.println("product name: "+p.getName());
					System.out.println("Product brand: "+p.getBrand());
					System.out.println("Product category: "+p.getCategory());
					System.out.println("Product description: "+p.getDescription());
					System.out.println("Product Cost: "+p.getCost());
					System.out.println("Product Image_url: "+p.getImage_url());
				}
				break;
			}
				
			case 11: {
				f=false;
				System.out.println(dao1.exit());
				break;
			}
				
			default: System.err.println("<=======***** INVALID CHOICE *****=======>");
			}
		}
	}
}
