package com.springboot.mywebapp.todo;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.validation.Valid;

@Service
public class TodoService {
	private static int todoCount=3;
	private static List<Todo> todos = new ArrayList<>();
	static {
		todos.add(new Todo(++todoCount,"admin","Learn AWS",LocalDate.now(),false));
		todos.add(new Todo(++todoCount,"rishabh","Learn Devops",LocalDate.now().plusYears(1),false));
		todos.add(new Todo(++todoCount,"rishabh","Learn Cloud",LocalDate.now().plusYears(3),true));
		todos.add(new Todo(++todoCount,"amit","Learn Java",LocalDate.now().plusYears(2),false));
	}

	public List<Todo> findByUsername(String userName){
		Predicate<?super Todo> predicate = todo->todo.getUsername().equalsIgnoreCase(userName);
		return todos.stream().filter(predicate).toList();
	}

	public void addTodo(String userName, String description, LocalDate targetDate, boolean done){
		Todo todo = new Todo(++todoCount, userName, description, targetDate, done);
		todos.add(todo);
	}

	public void deleteById(int id){
		Predicate<?super Todo> predicate = todo->todo.getId()==id;
		todos.removeIf(predicate);
	}

	public Todo findById(int id) {
		Predicate<?super Todo> predicate = todo->todo.getId()==id;
		return todos.stream().filter(predicate).findFirst().get();
	}

	public void updateTodo(@Valid Todo todo) {
		deleteById(todo.getId());
		todos.add(todo);
	}
}
