package com.springboot.mywebapp.todo;


import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springboot.mywebapp.login.WelcomeController;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoController {
	private TodoService todoService;
	private WelcomeController welcomeController;
	
	
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}


	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model) {
		String userName = getLoggedInUsername(model);
		List<Todo> todos = todoService.findByUsername(userName);
		model.addAttribute("todos", todos);
		return "listTodos";
	}


	private String getLoggedInUsername(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	
	@RequestMapping(value="add-todo", method=RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {
		String userName = getLoggedInUsername(model);
		Todo todo = new Todo(0,userName,"", (LocalDate)model.get("targetDate"), false);
		model.put("todo", todo);
		return "todo";
	}
	
	@RequestMapping(value="add-todo", method=RequestMethod.POST)
	public String addNewTodoPage(ModelMap model,@Valid Todo todo, BindingResult result) {
		if(result.hasErrors())
			return "todo";
		String userName = getLoggedInUsername(model);
		todoService.addTodo(userName, todo.getDescription(), todo.getTargetDate(), false);
		return "redirect:list-todos";
	}
	
	@RequestMapping(value="delete-todo", method=RequestMethod.GET)
	public String addNewTodoPage(@RequestParam int id) {
			todoService.deleteById(id);
		return "redirect:list-todos";
	}
	
	@RequestMapping(value="update-todo", method=RequestMethod.GET)
	public String showUpdateTodo(@RequestParam int id,ModelMap model) {
		Todo todo = todoService.findById(id);
		model.addAttribute("todo", todo);
		return "todo";
	}
	
	@RequestMapping(value="update-todo", method=RequestMethod.POST)
	public String updateTodo(ModelMap model,@Valid Todo todo, BindingResult result) {
		if(result.hasErrors())
			return "todo";
		String userName = getLoggedInUsername(model);
		todo.setUsername(userName);
		todoService.updateTodo(todo);
		return "redirect:list-todos";
	}
	
	
}
