package org.hibernate.bugs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class JPAUnitTestCase {

    private EntityManagerFactory entityManagerFactory;

    @Before
    public void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("templatePU");
    }

    @After
    public void destroy() {
        entityManagerFactory.close();
    }

    @Test
    public void sortingShouldReturnAllProductsPlainJpa() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        VendorAddress address = entityManager.merge(new VendorAddress("FooCity", "12345", "FooStreet", "1"));
        Vendor vendor = entityManager.merge(new Vendor("ACME", address));
        entityManager.persist(new Product("FUBAR", new Product.VendorInfo(vendor, "Best buddy")));
        entityManager.flush();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        query.select(root)
                .orderBy(cb.asc(root.get("vendorInfo").get("vendor").get("address").get("street")));


        List<Product> products = entityManager.createQuery(query).getResultList();
        assertThat("Repository should return demo product", products.size(), is(1));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
