import 'package:flutter_gym_app/utils/language/english_messages.dart';
import 'package:flutter_gym_app/utils/language/italian_messages.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'language/messages.dart';

class Constants {
  static SharedPreferences PREFERENCES;
  static String LANGUAGE; //todo get language from the system
  static Messages MESSAGES = _MESSAGES[LANGUAGE];
  static const String LOG_STATUS = 'loginStatus';
  static const String UID = 'userId';
  static const String USER_OR_OWNER = 'role';
  static const String GYM_NAME = 'Body Power'; //todo this will be your gym name
  static const String GYM_LOGO = 'assets/gymLogo.png'; //todo here your logo has to be
  static const String LOGIN_TITLE = 'Login';
  static const String SERVER_ADDRESS = '';
  static Map<String, Messages> _MESSAGES = {
    'it': new ItalianMessages(),
    'en': new EnglishMessages(),
  };
}