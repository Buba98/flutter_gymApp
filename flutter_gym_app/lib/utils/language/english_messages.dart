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
      case MESSAGE_TYPE.ACCESS:
        return 'Access';
      case MESSAGE_TYPE.WELCOME:
        return 'Welcome to ';
      case MESSAGE_TYPE.SCAN_HERE:
        return 'Scan here';
      case MESSAGE_TYPE.SCAN:
        return 'Scan';
      case MESSAGE_TYPE.LETS_START:
        return 'Let\'s start';
      case MESSAGE_TYPE.DETAILS:
        return 'Details';
      case MESSAGE_TYPE.DONE:
        return 'Done';
      case MESSAGE_TYPE.OK:
        return 'Ok';
      case MESSAGE_TYPE.ERROR:
        return 'Error';
      case MESSAGE_TYPE.ERROR_LOGIN:
        return 'Please check your email and/or your password';
      case MESSAGE_TYPE.ENTRANCE_CARD:
        return 'Entrance card';
      case MESSAGE_TYPE.REMAINING_DAYS:
        return 'Remaining days: ';
      case MESSAGE_TYPE.INSERT_UID:
        return 'Enter user ID';
      case MESSAGE_TYPE.TRAINING_SCHEDULES:
        return 'Training schedules';
      case MESSAGE_TYPE.EXERCISE:
        return 'Exercise';
      case MESSAGE_TYPE.REPS:
        return 'Reps';
      case MESSAGE_TYPE.SETS:
        return 'Series';
    default:
        return '';
    }
  }
}