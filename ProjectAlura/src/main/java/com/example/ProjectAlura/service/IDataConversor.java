package com.example.ProjectAlura.service;

public interface IDataConversor {
    <T> T getData(String json, Class<T> clase);
}
