package com.example.MyBookShopApp.config;

import com.example.MyBookShopApp.data.TestEntity;
import com.example.MyBookShopApp.data.TestEntityDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.util.logging.Logger;

@Configuration
public class CommandLineRunnerImpl implements CommandLineRunner {

    EntityManagerFactory entityManagerFactory;
    TestEntityDao testEntityDao;

    public CommandLineRunnerImpl(EntityManagerFactory entityManagerFactory, TestEntityDao testEntityDao) {
        this.entityManagerFactory = entityManagerFactory;
        this.testEntityDao = testEntityDao;
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 5; i++) {
            createTestEntity(new TestEntity());
        }

        TestEntity readTestEntity = testEntityDao.findOne(3L);
        if (readTestEntity != null) {
            Logger.getLogger(CommandLineRunnerImpl.class.getSimpleName()).info("read " + readTestEntity.toString());
        }
        
        TestEntity updatedTestEntity = updateTestEntityById(5L);
        if (updatedTestEntity != null) {
            Logger.getLogger(CommandLineRunnerImpl.class.getSimpleName()).info("update " + updatedTestEntity.toString());
        }

        deleteTestEntityById(4L);
    }

    private void deleteTestEntityById(Long id) {
        Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            TestEntity findEntity = readTestEntityById(id);
            TestEntity merged = (TestEntity) session.merge(findEntity);
            session.delete(merged);

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            } else {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
    }

    private TestEntity updateTestEntityById(Long id) {
        Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
        Transaction transaction = null;
        TestEntity result = null;

        try {
            transaction = session.beginTransaction();
            TestEntity findEntity = readTestEntityById(id);
            findEntity.setData("NEW DATA UPDATE");
            result = (TestEntity) session.merge(findEntity);

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            } else {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }

        return result;
    }

    private TestEntity readTestEntityById(Long id) {
        Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
        Transaction transaction = null;
        TestEntity result = null;

        try {
            transaction = session.beginTransaction();

            result = session.find(TestEntity.class, id);

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            } else {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }

        return result;
    }

    private void createTestEntity(TestEntity entity) {
        Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            entity.setData(entity.getClass().getSimpleName() + entity.hashCode());
            session.save(entity);

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            } else {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
    }
}
