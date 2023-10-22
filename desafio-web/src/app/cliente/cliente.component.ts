import { Component, OnInit  } from '@angular/core';
import { Cliente } from '../models/cliente';
import { ClienteService } from '../services/cliente.service';
import {Router} from "@angular/router";
import { NgForm } from '@angular/forms';


@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.css']
})
export class ClienteComponent implements OnInit{
  cliente = {} as Cliente;
  clientes: Cliente[]=[];

  constructor(private clienteService: ClienteService ,public router: Router) {}
  ngOnInit(): void {
  }

  saveCliente(form: NgForm) {
      this.clienteService.cadastrarClientes(this.cliente).subscribe(() => {
        this.cleanForm(form);
        this.router.navigateByUrl('');
        alert("Cadastro salvo com sucesso!")
      });
  }

  // limpa o formulario
  cleanForm(form: NgForm) {
    form.resetForm();
    this.cliente = {} as Cliente;
    this.router.navigateByUrl('');
  }
}


