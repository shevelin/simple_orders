package com.scotch.test.jpa.hibernate;

import com.scotch.test.jpa.hibernate.entity.Category;
import com.scotch.test.jpa.hibernate.entity.Commodity;
import com.scotch.test.jpa.hibernate.entity.Customer;
import com.scotch.test.jpa.hibernate.entity.Indent;
import com.scotch.test.jpa.hibernate.service.CustomerService;
import com.scotch.test.jpa.hibernate.util.FactoryHolder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by sutupin on 25.12.2014.
 */
public class JpaTest {
    public static void main(String[] args) throws Exception {
        CustomerService service = new CustomerService();

        List<Customer> customers = service.getAll();

//        System.out.println("num of castomers:" + customers.size());
//        for (Customer next : customers) {
//            System.out.println("next castomer: " + next);
//        }

//        Customer newCustomer = service.create("Givi", "password");
//        System.out.println(newCustomer);

//        Customer customer = customers.get(customers.size()-1);
//        customer.setName("Olga");
//        customer = service.update(customer);

//        Customer customer = service.getById(6);
//        customer.setName("Grisha");
//        customer = service.update(customer);

//        Customer customer = service.getByName("Helga");
//        System.out.println(customer);

//**********************************************************************************************************
/*
        List<Commodity> goods;

        EntityManager manager = FactoryHolder.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        try {
            goods = manager.createQuery("SELECT c FROM Commodity c", Commodity.class).getResultList();

            System.out.println("num of goods:" + goods.size());
            for (Commodity next : goods) {
                System.out.println("next Commodity: " + next);
            }

            Commodity someCommodity = goods.get(0);
            System.out.println(someCommodity);

            Category category = someCommodity.getCategory();
            System.out.println(category);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new Exception(e);
        } finally {
            manager.close();
        }
*/
//**********************************************************************************************************

/*
        List<Commodity> goods;

        EntityManager manager = FactoryHolder.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        try {

            Category category = manager.find(Category.class, 1);
            goods = category.getCommodities();

            for (Commodity next : goods) {
                System.out.println("next Commodity: " + next);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new Exception(e);
        } finally {
            manager.close();
        }
*/

        EntityManager manager = FactoryHolder.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        try {

            Indent indent = manager.find(Indent.class, 1);
            System.out.println(indent);

            List<Commodity> goods = indent.getCommodities();
            for (Commodity next : goods) {
                System.out.println("next Commodity: " + next);
            }

            Customer customer = indent.getCustomer();
            System.out.println( customer);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new Exception(e);
        } finally {
            manager.close();
        }

        System.out.println(".. done");
        FactoryHolder.closeFactory();
    }

}
