import { Component , OnInit} from '@angular/core';
import { SolicitaEmprestimo } from '../models/solicita-emprestimo';
import { EmprestimoService } from '../services/emprestimo.service';
import { UsuarioAutenticadoDto } from '../models/usuario-autenticado';
import { AutenticacaoService } from '../services/autenticacao.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Emprestimo } from '../models/emprestimo';

@Component({
  selector: 'app-emprestimo',
  templateUrl: './emprestimo.component.html',
  styleUrls: ['./emprestimo.component.css']
})
export class EmprestimoComponent implements OnInit{

  emprestimos: Emprestimo[]=[];

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
          alert("Empréstimo aceito!")
          this.getEmprestimo();
        },
        error: (e) => console.error(e),
        complete: () => console.info('complete')

    });
  }
}
