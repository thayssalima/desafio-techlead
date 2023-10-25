import { Component , OnInit} from '@angular/core';
import { EmprestimoService } from '../services/emprestimo.service';
import { UsuarioAutenticadoDto } from '../models/usuario-autenticado';
import { AutenticacaoService } from '../services/autenticacao.service';
import { Router } from '@angular/router';
import { Emprestimo } from '../models/emprestimo';
import { Devolucao } from '../models/devolucao-emprestimo';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-emprestimo',
  templateUrl: './emprestimo.component.html',
  styleUrls: ['./emprestimo.component.css']
})
export class EmprestimoComponent implements OnInit{

  emprestimo = {} as Emprestimo;
  emprestimos: Emprestimo[]=[];

  devolucao = {id: null, perdaDano: false} as Devolucao;

  usuario: UsuarioAutenticadoDto | null = null
  constructor(private emprestimoService: EmprestimoService ,public autenticacaoService: AutenticacaoService,public router: Router) {}

  ngOnInit() {
    this.getEmprestimo();
    this.usuario = this.autenticacaoService.retornarStorage();
  }

  getEmprestimo() {
    this.emprestimoService.listar().subscribe((emprestimos: Emprestimo[]) => {
      this.emprestimos = emprestimos;
    });
  }

  aceitarSolitacoes(emprestimo: Emprestimo) {
      this.emprestimoService.aceitarSolitacoes(emprestimo).subscribe({
        next: (v) => {
          alert("Solicitação aceita!")
          this.getEmprestimo();
        },
        error: (e) => console.error(e),
        complete: () => console.info('complete')
    });
  }

  devolucaoEmprestimo(emprestimo: Emprestimo, dialog : any) {
    this.devolucao.id= emprestimo.idEmprestimo;
    this.emprestimoService.devolucaoEmprestimo(this.devolucao).subscribe(() => {
      dialog.close()
      alert("Livro devolvido à biblioteca!")
      this.getEmprestimo();
    });
  }

  desaprovaSolicitacao(emprestimo: Emprestimo) {
    this.emprestimoService.desaprovaSolicitacao(emprestimo).subscribe({
      next: (v) => {
        alert("Solicitação de empréstimo negada!")
        this.getEmprestimo();
      },
      error: (e) => console.error(e),
      complete: () => console.info('complete')
  });
}

  rotaLivros(): void {
    this.router.navigateByUrl('livro');
  }

  cleanForm() {
    this.getEmprestimo();
    this.emprestimo = {} as Emprestimo;
  }

  deslogar() {
    localStorage.clear();
    this.router.navigate(['']);
  }
}
