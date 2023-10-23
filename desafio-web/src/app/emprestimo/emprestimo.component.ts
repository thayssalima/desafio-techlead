import { Component , OnInit} from '@angular/core';
import { SolicitaEmprestimo } from '../models/solicita-emprestimo';
import { EmprestimoService } from '../services/emprestimo.service';
import { UsuarioAutenticadoDto } from '../models/usuario-autenticado';
import { AutenticacaoService } from '../services/autenticacao.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-emprestimo',
  templateUrl: './emprestimo.component.html',
  styleUrls: ['./emprestimo.component.css']
})
export class EmprestimoComponent implements OnInit{
  solicitaEmprestimo = {} as SolicitaEmprestimo;

  usuario: UsuarioAutenticadoDto | null = null
  constructor(private emprestimoService: EmprestimoService ,public autenticacaoService: AutenticacaoService,public router: Router) {}

  ngOnInit() {
    this.usuario = this.autenticacaoService.retornarStorage();
  }

  cleanForm(form: NgForm) {
    form.resetForm();
    this.solicitaEmprestimo = {} as SolicitaEmprestimo;
  }
}
