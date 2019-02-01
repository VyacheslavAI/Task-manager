package ru.ivanov.todoproject.api;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import ru.ivanov.todoproject.entity.User;

public interface IUserRepository extends IRepository<User> {

    @Select("select * from user where login = #{login}")
    User findByLogin(final String login);

    User findBySession(String sessionId);

    @Insert("insert into user (id, login, passwordhash, created) " +
            "values (#{id}, #{login}, #{passwordHash}, #{created})")
    User createUser(User user);

    @Update("update user set login = #{login}")
    User updateUser(User user);

    @Delete("delete from user where id = #{id}")
    User deleteUser(User user);

    @Delete("delete * from user")
    boolean deleteAllUser();
}
