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
      default:
        return '';
    }
  }
}