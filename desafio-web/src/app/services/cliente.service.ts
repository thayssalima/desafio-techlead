import { Cliente } from './../models/cliente';
import { HttpClient,HttpHeaders,HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable,throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { EsqueceuSenha } from '../models/esqueceu-senha';


@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  url = 'http://localhost:8080/desafio-api/usuario';

  constructor(private httpClient: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }

  cadastrarClientes(cliente: Cliente): Observable<Cliente> {
    return this.httpClient.post<Cliente>(this.url, JSON.stringify(cliente),this.httpOptions)
    .pipe(
      retry(2),
      catchError(this.handleError)
    )
  }

  recuperandoSenha(esqueceuSenha: EsqueceuSenha): Observable<EsqueceuSenha> {
    return this.httpClient.put<EsqueceuSenha>(this.url, JSON.stringify(esqueceuSenha),this.httpOptions)
    .pipe(
      retry(2),
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
