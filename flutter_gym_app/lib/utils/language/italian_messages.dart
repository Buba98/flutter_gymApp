import 'messages.dart';

class ItalianMessages implements Messages {
  @override
  String message({type: MESSAGE_TYPE}) {
    switch (type) {
      case MESSAGE_TYPE.LOGIN:
        return 'Accedi';
      case MESSAGE_TYPE.EMAIL:
        return 'Email';
      case MESSAGE_TYPE.PASSWORD:
        return 'Password';
      case MESSAGE_TYPE.FORGOTTEN_PASSWORD:
        return 'Password dimenticata?';
      case MESSAGE_TYPE.ACCESS:
        return 'Accedi';
      case MESSAGE_TYPE.WELCOME:
        return 'Benvenuto in ';
      case MESSAGE_TYPE.SCAN_HERE:
        return 'Scannerizza qui';
      case MESSAGE_TYPE.LETS_START:
        return 'Iniziamo';
      case MESSAGE_TYPE.DETAILS:
        return 'Dettagli';
      case MESSAGE_TYPE.DONE:
        return 'Fatto';
      case MESSAGE_TYPE.OK:
        return 'Ok';
      case MESSAGE_TYPE.ERROR:
        return 'Errore';
      case MESSAGE_TYPE.ERROR_LOGIN:
        return 'Per favore ricontrolla email e/o password';
      default:
        return '';
    }
  }
}