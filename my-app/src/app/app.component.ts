import { Component, OnInit } from '@angular/core';
import { TodoService } from './todo.service';
import { Todo } from './todo';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'my-app';
  todos: Todo[] = [];
  todo: Todo = { id: 0, description: '', summary: '' };

  constructor(private todoService: TodoService) {}

  ngOnInit(): void {
    this.loadTodos();
  }

  loadTodos() {
    this.todoService.getTodos().subscribe(data => {this.todos = data});
  }

  addTodo() {
      this.todoService.addTodo(this.todo).subscribe(
          newTodo => {
            this.todos.push(newTodo);
            this.todo.description = '';
            this.todo.summary = '';
          });
  }

  deleteTodo(todo: Todo) {
    this.todoService.deleteTodo(todo).subscribe(to => {
          this.todos = this.todos.filter(t => t.id !== to.id);
        });
  }
}