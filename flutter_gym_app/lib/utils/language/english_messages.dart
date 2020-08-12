import 'messages.dart';

class EnglishMessages implements Messages {
  @override
  String message({type: MESSAGE_TYPE}) {
    switch (type) {
      case MESSAGE_TYPE.LOGIN:
        return 'Login';
      case MESSAGE_TYPE.EMAIL:
        return 'Email';
      case MESSAGE_TYPE.PASSWORD:
        return 'Password';
      case MESSAGE_TYPE.FORGOTTEN_PASSWORD:
        return 'Forgotten password?';
      default:
        return '';
    }
  }
}