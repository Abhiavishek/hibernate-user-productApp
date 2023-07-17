package org.jsp.userproductApp.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.userproductApp.dao.ProductDao;
import org.jsp.userproductApp.dao.UserDao;
import org.jsp.userproductApp.dto.Product;
import org.jsp.userproductApp.dto.User;

public class UserController {
	
	static Scanner scn = new Scanner(System.in);
	static ProductDao pDao = new ProductDao();
	static UserDao uDao = new UserDao();

	public static void main(String[] args) {
		// TODO Auto-generated method stubAd
		
		System.out.println("1.Register");
		System.out.println("2.Update");
		System.out.println("3.Verify User By Phone and Password");
		System.out.println("4.Verify User By email and Password");
		System.out.println("5.Add Product");
		System.out.println("6.View Product By User Id");
		System.out.println("7.View Product By Category");
		System.out.println("8.View Product By Brand");
		System.out.println("9.Delete User");
		System.out.println("10.Update Product ");
		System.out.println("11.Update User");
		System.out.println("12.Delete Product");
		System.out.println("13.Fetch Product by Price");
		
		int choice = scn.nextInt();
		switch(choice) {
		case 1:{
			save();
			break;
		}
		case 2:{
			update();
			break;
		}
		case 3:{
			VerifyUserByPhoneAndPassword();
			break;
		}
		case 4:{
			VerifyUserByEmailAndPassword();
			break;
		}
		case 9:{
			DeleteUser();
			break;
		}
		
		case 5:{
			AddProduct();
			break;
		}
		
		case 6:{
			FetchProductByUserId();
			break;
		}
		
		case 7:{
			FetchProductByCategory();
			break;
		}
		
		case 8:{
			FetchProductByBrand();
			break;
		}
		
		case 10:{
			updateProductById();
			break;
		}
		
		case 11:{
			updateUser();
			break;
		}
		case 12:{
			DeleteProduct();
			break;
		}
		case 13:{
			FetchProductByPrice();
			break;
		}
		}

	}

	public static void save() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter the name,email,phone and password to register");
		String name = scn.next();
		String email = scn.next();
		long phone = scn.nextLong();
		String password = scn.next();
		
		User u = new User();
		u.setPassword(password);
		u.setEmail(email);
		u.setName(name);
		u.setPhone(phone);
		u=uDao.saveUser(u);
		System.out.println("You are registered with Id:"+u.getId());
		
	}
	
	public static void update() {
		System.out.println("Enter the Id");
		int id = scn.nextInt();
		System.out.println("Enter the email ,phone, name, password to update");
		String email=scn.next();
		long phone = scn.nextLong();
		String name = scn.next();
		String password = scn.next();
		
		User u = new User();
		u.setEmail(email);
		u.setPhone(phone);
		u.setName(name);
		u.setPassword(password);
//		u=uDao.updateUser(u);
		System.out.println("Your details are Updated");
	}
	
	public static void VerifyUserByPhoneAndPassword() {
		Scanner scn =new Scanner(System.in);
		long phone = scn.nextLong();
		String password = scn.next();
		User u = new User();
//		u.setPhone(phone);
//		u.setPassword(password);
		u=uDao.verifyUser(phone, password);
		System.out.println("Welcome "+u.getName());
	}
	
	
	public static void VerifyUserByEmailAndPassword() {
		Scanner scn = new Scanner(System.in);
		String email = scn.next();
		String password = scn.next();
		User u = new User();
		u=uDao.verifyUser(email, password);
		System.out.println("Welcome "+u.getName());
	}
	
	public static void DeleteUser() {
		Scanner scn =new Scanner(System.in);
		System.out.println("Enter the Id");
		int id = scn.nextInt();
		User u = new User();
		u=uDao.deleteUser(id);
		System.out.println("Recored deleted");
	}
	
	public static void AddProduct() {
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter the User id");
		int id = scn.nextInt();
		System.out.println("Enter the name, category, brand, price");
		String name = scn.next();
		String category = scn.next();
		String brand =scn.next();
		double price = scn.nextDouble();
		
		Product p = new Product();
		p.setBrand(brand);
		p.setName(name);
		p.setPrice(price);
		p.setCategory(category);
		p=pDao.SaveProduct(p, id);
		if(p!=null) {
			System.out.println("Product added with Id :"+p.getId());
		}
		else {
			System.err.println("Product not added");
		}
	}
	
	public static void FetchProductByUserId() {
		Scanner scn = new Scanner(System.in);
		int id = scn.nextInt();
		List<Product>products= pDao.FindProductByUserId(id);
		
			for(Product p: products) {
				System.out.println(p);
			}
		

	}
	
	public static void FetchProductByCategory() {
		Scanner scn = new Scanner(System.in);
		String category = scn.next();
		List<Product>products= pDao.FindProductByCategory(category);
		
			for(Product p: products) {
				System.out.println(p);
			}
		

	}
	
	public static void FetchProductByBrand() {
		Scanner scn = new Scanner(System.in);
		String brand = scn.next();
		List<Product>products= pDao.FindProductByBrand(brand);
		
			for(Product p: products) {
				System.out.println(p);
			}
		

	}
	
	public static void updateProductById() {
		System.out.println("Enter the user id");
		int id = scn.nextInt();
		System.out.println("Enter the brand, price,category");
//		String name = scn.next();
//		String brand = scn.next();
//		double price = scn.nextDouble();
		String category = scn.next();
		
		Product p =pDao.UpdateProduct(id, category);
		System.out.println("Product Updated"+p.getId());
	}
	
	
	public static void updateUser() {
		System.out.println("Enter the user id");
		int id = scn.nextInt();
		System.out.println("Enter the phone, email, password");
		long phone = scn.nextLong();
		String email = scn.next();
		User u = uDao.updateUser(id,phone, email);
		System.out.println("User updated "+u.getId());
		
	}
	
	public static void DeleteProduct() {
		System.out.println("Enter the product id");
		int id = scn.nextInt();
		Product p = pDao.DeleteProduct(id);
		System.out.println("delete that product");
	}
	
	public static void FetchProductByPrice() {
		System.out.println("Enter the price");
		double price = scn.nextDouble();
		List<Product>p = pDao.FindProductByPrice(price);
		for(Product pro:p) {
			System.out.println(pro);
		}
	}

	

}
