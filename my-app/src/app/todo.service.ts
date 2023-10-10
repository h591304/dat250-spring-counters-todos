import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Todo } from './todo';
import { Injectable } from "@angular/core";


@Injectable({
    providedIn: 'root',
  })
export class TodoService {
    
    private _url: string = "http://localhost:8080/todos"

    constructor(private http: HttpClient) {}

    getTodos(): Observable<Todo[]> {
        return this.http.get<Todo[]>(this._url)
    }

    addTodo(todo: Todo): Observable<Todo> {
        return this.http.post<Todo>(this._url, todo)
    }

    deleteTodo(todo: Todo): Observable<Todo> {
        return this.http.delete<Todo>(this._url + "/" + todo.id)
    }

}