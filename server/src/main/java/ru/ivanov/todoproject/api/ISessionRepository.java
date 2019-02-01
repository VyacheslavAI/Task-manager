package ru.ivanov.todoproject.api;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import ru.ivanov.todoproject.entity.Session;

public interface ISessionRepository extends IRepository<Session> {

    @Insert("insert into session (id, created, timestamp, user_id, signature) " +
            "values (#{id}, #{created}, #{timestamp}, #{userId}, #{signature})")
    Session createSession(Session session);

    @Update("update project set created = #{created}, timestamp = #{timestamp}, user_id = #{userId}, signature = #{signature}")
    Session updateSession(Session session);

    @Delete("delete from session where id = #{id}")
    Session deleteSession(Session session);

    @Delete("delete * from session")
    boolean deleteAllSession();
}
