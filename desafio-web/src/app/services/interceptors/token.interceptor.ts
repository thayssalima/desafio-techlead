import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AutenticacaoService } from '../autenticacao.service';


@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(private autenticacaoService : AutenticacaoService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const usuario = this.autenticacaoService.retornarStorage();

        if (usuario) {
            request = request.clone({
                setHeaders: {
                    Authorization: `Bearer ${usuario.token}`,
                    token: `${usuario.token}`
                }
            });
        }
        return next.handle(request);
  }
}
