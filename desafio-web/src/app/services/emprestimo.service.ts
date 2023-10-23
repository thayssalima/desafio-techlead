import { Injectable } from "@angular/core";
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { SolicitaEmprestimo } from "../models/solicita-emprestimo";
import { Observable , throwError} from "rxjs";
import { retry, catchError } from 'rxjs/operators';

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
