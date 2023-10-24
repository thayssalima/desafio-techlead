import { Injectable } from "@angular/core";
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { SolicitaEmprestimo } from "../models/solicita-emprestimo";
import { Observable , throwError} from "rxjs";
import { retry, catchError } from 'rxjs/operators';
import { Emprestimo } from "../models/emprestimo";
import { Devolucao } from "../models/devolucao-emprestimo";

@Injectable({
  providedIn: 'root'
})
export class EmprestimoService {
  url = 'http://localhost:8080/desafio-api/emprestimo';

  constructor(private httpClient: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }

  solicitarEmprestimo(solicitaEmprestimo: SolicitaEmprestimo): Observable<SolicitaEmprestimo> {
    return this.httpClient.post<SolicitaEmprestimo>(this.url, JSON.stringify(solicitaEmprestimo),this.httpOptions)
    .pipe(
      retry(2),
      catchError(this.handleError)
    )
  }

  listar(): Observable<Emprestimo[]> {
    return this.httpClient.get<Emprestimo[]>(this.url + '/lista')
      .pipe(
        retry(2),
        catchError(this.handleError))
  }

  aceitarSolitacoes(emprestimos: Emprestimo): Observable<Emprestimo> {
    return this.httpClient.put<Emprestimo>(this.url + '/' + emprestimos.idEmprestimo, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  devolucaoEmprestimo(devolucaoEmprestimo: Devolucao): Observable<Emprestimo> {
    return this.httpClient.put<Emprestimo>(this.url +'/devolucao/'+ devolucaoEmprestimo.id, JSON.stringify(devolucaoEmprestimo), this.httpOptions)
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
