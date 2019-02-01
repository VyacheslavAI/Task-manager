package ru.ivanov.todoproject.config;

import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.datasource.pooled.PooledDataSource;

import javax.sql.DataSource;
import java.util.Properties;


public class MyDataSourceFactory implements DataSourceFactory {

    private Properties properties;

    @Override
    public DataSource getDataSource() {
        final PooledDataSource pooledDataSource = new PooledDataSource();
        pooledDataSource.setDriver(properties.getProperty("driver"));
        pooledDataSource.setUrl(properties.getProperty("url"));
        pooledDataSource.setUsername(properties.getProperty("username"));
        pooledDataSource.setPassword(properties.getProperty("password"));
        return pooledDataSource;
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
