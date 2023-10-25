export interface Emprestimo {
  idEmprestimo: number;
  data: Date;
  diasEmprestados: number;
  emprestimoAtivo: Boolean;
  emprestimoAtivoAceite: Boolean;
  nomeUsuario: string;
  nomeLivro: string;
  statusEmprestimo: string;
}
