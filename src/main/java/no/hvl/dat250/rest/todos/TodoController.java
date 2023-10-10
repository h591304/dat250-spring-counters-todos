package no.hvl.dat250.rest.todos;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Rest-Endpoint for todos.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/todos")
public class TodoController {

  public static final String TODO_WITH_THE_ID_X_NOT_FOUND = "Todo with the id %s not found!";
  private Map<Long, Todo> todos = new HashMap<>();
  private long id = 0L;

  // implement createTodo
  @PostMapping
  public Todo createTodo(@RequestBody Todo todo) {
    todo.setId(id++);
    todos.put(todo.getId(), todo);
    return todo;
  }

  // implement readTodo
  @GetMapping("/{id}")
  public Todo readTodo(@PathVariable long id) {
    Todo existingTodo = todos.get(id);
    if (existingTodo == null) {
      throw new RuntimeException(TODO_WITH_THE_ID_X_NOT_FOUND.formatted(id));
    }
    return existingTodo;
  }

  // implement updateTodo
  @PutMapping("/{id}")
  public Todo updateTodo(@RequestBody Todo updatedTodo, @PathVariable long id) {
    Todo existingTodo = todos.get(id);
    if (existingTodo == null) {
      throw new RuntimeException(TODO_WITH_THE_ID_X_NOT_FOUND.formatted(id));
    }
    existingTodo.setDescription(updatedTodo.getDescription());
    existingTodo.setSummary(updatedTodo.getSummary());
    return existingTodo;
  }

  // implement deleteTodo
  @DeleteMapping("/{id}")
  public Todo deleteTodo(@PathVariable Long id) {
    Todo existing = todos.get(id);
    if (existing == null) {
      throw new RuntimeException(TODO_WITH_THE_ID_X_NOT_FOUND.formatted(id));
    }
    return todos.remove(id);
  }

  // implement getAllTodos
  @GetMapping
  public List<Todo> getAllTodos() {
    return new ArrayList<>(todos.values());
  }

}