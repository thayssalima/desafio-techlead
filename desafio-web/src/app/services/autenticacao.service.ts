import { Injectable } from "@angular/core";
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import {Observable, catchError, retry, throwError} from "rxjs";
import { UsuarioAutenticadoDto } from "../models/usuario-autenticado";

@Injectable({
    providedIn: 'root'
  })

export class AutenticacaoService  {
    url = 'http://localhost:8080/desafio-api/usuarios';

    constructor(private httpClient: HttpClient) { }

    autenticar(body: any): Observable<UsuarioAutenticadoDto> {
        return this.httpClient.post<UsuarioAutenticadoDto>(this.url + '/autenticar', body)
        .pipe(
          retry(1),
          catchError(this.handleError)
        );
      }

      salvarStorage(usuario: UsuarioAutenticadoDto) {
        localStorage.setItem('desafio', JSON.stringify(usuario));
      }

      retornarStorage(): UsuarioAutenticadoDto {
        return <UsuarioAutenticadoDto>JSON.parse(<string>localStorage.getItem('desafio'));
      }

      get logado(): boolean {
        return localStorage.getItem('desafio') ? true : false;
      }


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
