import { Livro } from '../models/livro';
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class LivroService {

  url = 'http://localhost:8080/desafio-api/livros';

  constructor(private httpClient: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }

  // Obtem todos os livros
    listar(): Observable<Livro[]> {
    return this.httpClient.get<Livro[]>(this.url + '/lista')
      .pipe(
        retry(2),
        catchError(this.handleError))
  }

   // Obtem um livro pelo id
    findById(id: number): Observable<Livro> {
    return this.httpClient.get<Livro>(this.url + '/' + id)
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  // salva um livro
  cadastrar(livros: Livro): Observable<Livro> {
    return this.httpClient.post<Livro>(this.url, JSON.stringify(livros),this.httpOptions)
    .pipe(
      retry(2),
      catchError(this.handleError)
    )
  }

  // autualiza um livro
  editar(livros: Livro): Observable<Livro> {
    return this.httpClient.put<Livro>(this.url + '/' + livros.id, JSON.stringify(livros), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  // deleta um livro
  excluir(livros: Livro) {
    return this.httpClient.delete<Livro>(this.url + '/' + livros.id, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

    // Manipulação de erros
    handleError(error: HttpErrorResponse) {
      let errorMessage = '';
      if (error.error != null) {
        errorMessage = error.error.message;
      } else {
        errorMessage = `Código do erro: ${error.status}, ` + `menssagem: ${error.message}`;
      }
      alert(errorMessage);
      return throwError(errorMessage);
    };
}
