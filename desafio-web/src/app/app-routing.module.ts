import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LivroComponent } from './livro/livro.component';
import { LoginComponent } from './login/login.component';
import { UsuarioAutenticadoGuard } from './services/guards/usuario-autenticado.guard';
import { ClienteComponent } from './cliente/cliente.component';
import { SenhaComponent } from './senha/senha.component';
import { EmprestimoComponent } from './emprestimo/emprestimo.component';
import { SolicitarComponent } from './solicitar/solicitar.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'livro', component: LivroComponent , canActivate: [UsuarioAutenticadoGuard]},
  { path: 'cadastro', component: ClienteComponent},
  { path: 'senha', component: SenhaComponent},
  { path: 'emprestimo', component: EmprestimoComponent, canActivate: [UsuarioAutenticadoGuard]},
  { path: 'solicitacoes', component: SolicitarComponent, canActivate: [UsuarioAutenticadoGuard]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
