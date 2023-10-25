import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { LivroComponent } from './livro/livro.component';
import { LoginComponent } from './login/login.component';
import {ReactiveFormsModule} from "@angular/forms";
import { TokenInterceptor } from './services/interceptors/token.interceptor';
import { ClienteComponent } from './cliente/cliente.component';
import { SenhaComponent } from './senha/senha.component';
import { EmprestimoComponent } from './emprestimo/emprestimo.component';
import { SolicitarComponent } from './solicitar/solicitar.component';

@NgModule({
  declarations: [
    AppComponent,
    LivroComponent,
    LoginComponent,
    ClienteComponent,
    SenhaComponent,
    EmprestimoComponent,
    SolicitarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
