import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LivroComponent } from './livro/livro.component';
import { LoginComponent } from './login/login.component';
import { UsuarioAutenticadoGuard } from './services/guards/usuario-autenticado.guard';
import { ClienteComponent } from './cliente/cliente.component';
import { SenhaComponent } from './senha/senha.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'livro', component: LivroComponent , canActivate: [UsuarioAutenticadoGuard]},
  { path: 'cadastro', component: ClienteComponent},
  { path: 'senha', component: SenhaComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
