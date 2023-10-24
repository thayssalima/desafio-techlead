export interface Livro {
    id: number;
    nome: string;
    autor: string;
    dataCadastro: Date;
    usuarioCadastrou: Boolean;
    usuarioBibliotecario: Boolean;
}
