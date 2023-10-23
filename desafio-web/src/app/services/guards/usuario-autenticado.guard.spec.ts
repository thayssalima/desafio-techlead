import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { UsuarioAutenticadoGuard } from './usuario-autenticado.guard';

describe('usuarioAutenticadoGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) =>
      TestBed.runInInjectionContext(() => UsuarioAutenticadoGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
