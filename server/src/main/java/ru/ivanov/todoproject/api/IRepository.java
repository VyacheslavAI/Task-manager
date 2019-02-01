package ru.ivanov.todoproject.api;

import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IRepository<E> {

    E merge(final E entity);

    E delete(final E entity);

    List<E> findAll();

    E findById(final String id);

    boolean deleteAll();

    boolean addAll(final List<E> entityList);

    void setConnection(Connection connection);

    void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory);
}
