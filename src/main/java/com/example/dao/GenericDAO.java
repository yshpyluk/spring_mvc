package com.example.dao;

import java.util.List;

/**
 * Created by yshpyluk on 4/5/17.
 */
public interface GenericDAO<T> {
	List<T> getAll();
	T get(int i);
	void add(T entity);
	void remove(T entity);
	T update(T entity);
}
