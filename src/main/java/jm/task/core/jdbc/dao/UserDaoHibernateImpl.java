package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {

        try(Session session = Util.getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
             String sql = "CREATE TABLE IF NOT EXISTS Users (" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "last_name VARCHAR(255) NOT NULL," +
                    "age TINYINT UNSIGNED NOT NULL)";

            session.createNativeQuery(sql).executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            System.out.println("Проблема с сессией при создании таблицы:" + e);
            e.printStackTrace();
        }

    }

    @Override
    public void dropUsersTable() {

        try(Session session = Util.getSessionFactory().openSession()) {
            String sql = "DROP TABLE IF EXISTS Users";
            Transaction transaction = session.beginTransaction();

            session.createNativeQuery(sql).executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            System.out.println("Проблема с сессией при удалении таблицы: " + e);
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Session session = Util.getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            session.save(new User(name,lastName,age));
            transaction.commit();

        } catch (Exception e) {
            System.out.println("Проблема с сессией при сохранении данных в БД:" + e);
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try(Session session = Util.getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if(user != null) {
                session.remove(user);
            }
            transaction.commit();

        } catch (Exception e) {
            System.out.println("Проблема с сессией при удалении строки из БД" + e);
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try(Session session = Util.getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            userList = session.createQuery("from User",User.class).getResultList();
            transaction.commit();

        } catch (Exception e) {
            System.out.println("Проблема с сессией при получении данных из БД:" + e);
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            System.out.println("Проблема с сессией при удалении данных из БД:" + e);
            e.printStackTrace();
        }

    }
}
