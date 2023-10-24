import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AutenticacaoService } from '../autenticacao.service';
@Injectable({
  providedIn: 'root'
})

export class  UsuarioAutenticadoGuard implements CanActivate {
  constructor(
    private autenticacaoService: AutenticacaoService,
    private router: Router) { }
  canActivate(){
    if (this.autenticacaoService.logado) {
      return true;
    }
    this.router.navigate(['login']);
    return false;
  }
};
