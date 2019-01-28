package ru.ivanov.todoproject.api;

public interface Serializer {

    void saveApplicationDataInBinary(ServiceLocator serviceLocator);

    void loadApplicationDataFromBinary(ServiceLocator serviceLocator);
}
