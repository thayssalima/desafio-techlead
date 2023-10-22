import { Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import { AutenticacaoService } from '../services/autenticacao.service';
import { MensagemService } from '../services/mensagem.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  formulario = this.formBuilder.group({
    cpf: ['', [Validators.required]],
    senha: ['', [Validators.required]]
  })

  constructor(
    public formBuilder: FormBuilder,
    public autenticacaoService: AutenticacaoService,
    public mensagemService: MensagemService,
    public router: Router
  ) { }

  ngOnInit(): void {
  }

  enviar(): void {
    this.autenticacaoService.autenticar(this.formulario.value).subscribe({
      next: (response) => {
        this.autenticacaoService.salvarStorage(response);
        this.router.navigateByUrl('livro');
      }
    });
  }

  cadastroCliente(): void{
    this.router.navigateByUrl('cadastro');
  }

  recuperandoSenha(): void{
    this.router.navigateByUrl('senha');
  }
}
