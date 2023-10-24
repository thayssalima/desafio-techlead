export class Alert {
  id: string = '';
  type: AlertType = AlertType.Info;
  message: string = '';
  titulo: string = '';
  autoClose: boolean = true;
  keepAfterRouteChange: boolean | undefined = true;
  fade: boolean = false;

  constructor(init?:Partial<Alert>) {
    Object.assign(this, init);
  }
}

export enum AlertType {
  Success,
  Error,
  Info,
  Warning
}
