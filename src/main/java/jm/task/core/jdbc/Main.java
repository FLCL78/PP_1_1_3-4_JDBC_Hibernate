package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Никита", "Клоков", (byte) 31);
        System.out.println("User с именем — Никита добавлен в базу данных");
        userService.saveUser("Мария", "Клокова", (byte) 31);
        System.out.println("User с именем — Мария добавлен в базу данных");
        userService.saveUser("Ева","Клокова", (byte) 2);
        System.out.println("User с именем — Ева добавлен в базу данных");
        userService.saveUser("Валентина","Клокова", (byte) 87);
        System.out.println("User с именем — Валентина добавлен в базу данных");
        userService.getAllUsers();
        for (User a : userService.getAllUsers()) {
            System.out.println(a.toString());
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();



    }
}
