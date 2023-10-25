import { Component, OnInit } from '@angular/core';
import { Livro } from '../models/livro';
import { LivroService } from '../services/livro.service';
import { NgForm } from '@angular/forms';
import { UsuarioAutenticadoDto } from '../models/usuario-autenticado';
import { AutenticacaoService } from '../services/autenticacao.service';
import {Router} from "@angular/router";
import { EmprestimoService } from '../services/emprestimo.service';
import { SolicitaEmprestimo } from '../models/solicita-emprestimo';

@Component({
  selector: 'app-livro',
  templateUrl: './livro.component.html',
  styleUrls: ['./livro.component.css']
})
export class LivroComponent implements OnInit{
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

  saveLivro(form: NgForm) {
    if (this.livro.id !== undefined) {
      this.livroService.editar(this.livro).subscribe({
        next: (v) => {
          this.cleanForm(form);
        },
        error: (e) => console.error(e),
        complete: () => console.info('complete')
    });

    } else {
      this.livroService.cadastrar(this.livro).subscribe(() => {
        this.cleanForm(form);
      });
    }
  }

  getLivros() {
    this.livroService.listar().subscribe((livros: Livro[]) => {
      this.livros = livros;
    });
  }

  deleteLivro(livro: Livro) {
    this.livroService.excluir(livro).subscribe(() => {
      this.getLivros();
    });
  }

  // copia o livro para ser editado.
  editLivro(livro: Livro) {
    this.livro = { ...livro };
    console.log(this.livro);
  }

  cleanForm(form: NgForm) {
    this.getLivros();
    form.resetForm();
    this.livro = {} as Livro;
  }

  deslogar() {
    localStorage.clear();
    this.router.navigate(['']);
  }

  rotaEmprestimo(): void {
      this.router.navigateByUrl('emprestimo');
  }

  rotaSolicitacoes(): void {
    this.router.navigateByUrl('solicitacoes');
}
}
