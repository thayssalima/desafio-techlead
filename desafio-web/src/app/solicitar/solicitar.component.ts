import { Component, OnInit } from '@angular/core';
import { UsuarioAutenticadoDto } from '../models/usuario-autenticado';
import { AutenticacaoService } from '../services/autenticacao.service';
import { Router } from '@angular/router';
import { EmprestimoService } from '../services/emprestimo.service';
import { SolicitaEmprestimo } from '../models/solicita-emprestimo';
import { Livro } from '../models/livro';
import { NgForm } from '@angular/forms';
import { LivroService } from '../services/livro.service';

@Component({
  selector: 'app-solicitar',
  templateUrl: './solicitar.component.html',
  styleUrls: ['./solicitar.component.css']
})
export class SolicitarComponent implements OnInit{

  livro = {} as Livro;
  livros: Livro[]=[];

  solicitaEmprestimo = {} as SolicitaEmprestimo;

  usuario: UsuarioAutenticadoDto | null = null
  constructor(private livroService: LivroService ,public autenticacaoService: AutenticacaoService,public router: Router,
                public emprestimoService: EmprestimoService) {}

  ngOnInit() {
    this.getLivros();
    this.usuario = this.autenticacaoService.retornarStorage();
  }

  getLivros() {
    this.livroService.listar().subscribe((livros: Livro[]) => {
      this.livros = livros;
    });
  }
  solicitarEmprestimo(livro: Livro, dialog : any) {
    this.solicitaEmprestimo.idLivro= livro.id;
    this.emprestimoService.solicitarEmprestimo(this.solicitaEmprestimo).subscribe(() => {
      dialog.close()
      alert("Empréstimo requerido!")
    });
  }

  rotaLivros(): void {
    this.router.navigateByUrl('livro');
  }
  cleanForm() {
    this.getLivros();
    this.livro = {} as Livro;
  }

  deslogar() {
    localStorage.clear();
    this.router.navigate(['']);
  }
}
