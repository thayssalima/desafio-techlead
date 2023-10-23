import { Component, OnInit  } from '@angular/core';
import { EsqueceuSenha } from '../models/esqueceu-senha';
import { ClienteService } from '../services/cliente.service';
import {Router} from "@angular/router";
import { NgForm } from '@angular/forms';


@Component({
  selector: 'app-senha',
  templateUrl: './senha.component.html',
  styleUrls: ['./senha.component.css']
})
export class SenhaComponent implements OnInit{
  esqueceuSenha = {} as EsqueceuSenha;
  senhas: EsqueceuSenha[]=[];

  constructor(private clienteService: ClienteService ,public router: Router) {}

  ngOnInit(): void {
  }

  trocaSenha(form: NgForm) {
    this.clienteService.recuperandoSenha(this.esqueceuSenha).subscribe(() => {
      this.cleanForm(form);
      this.router.navigateByUrl('');
      alert("Senha alterada com sucesso!")
    });
  }

  // limpa o formulario
  cleanForm(form: NgForm) {
    form.resetForm();
    this.esqueceuSenha = {} as EsqueceuSenha;
    this.router.navigateByUrl('');
  }
}
