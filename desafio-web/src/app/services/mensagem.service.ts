import { Injectable } from '@angular/core';
import { MensagemErroDTO } from '../models/error-dto';
import {Alert, AlertType} from '../models/alerta-dto';
import {filter, Observable, Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class MensagemService {

  private subject = new Subject<Alert>();
  private defaultId = 'default-alert';
  alert(alert: Alert) {
    alert.id = alert.id || this.defaultId;
    this.subject.next(alert);
  }

  error(error: MensagemErroDTO) {
    let mensagem = 'Sistema temporariamente indisponivel'
    if (error.error != null) {
      mensagem = error.error.message;
    }
    if (error.error.exception == "SESSAO") {
      mensagem = "Sua sessão expirou";
    }
    this.alert(new Alert({type: AlertType.Error, message: mensagem, titulo: 'Erro' }));
  }
}
