import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_gym_app/utils/language/english_messages.dart';
import 'package:flutter_gym_app/utils/language/italian_messages.dart';

import 'language/messages.dart';

class Constants {
  static const String LANGUAGE = ; //todo get language from the system
  static Messages MESSAGES = _MESSAGES[Constants.LANGUAGE];
  static const String LOG_STATUS = 'loginStatus';
  static const String UID = 'userId';
  static const String USER_OR_OWNER = 'role';
  static const String GYM_NAME = 'Body Power'; //todo this will be your gym name
  static const String GYM_LOGO = 'assets/gymLogo.png'; //todo here your logo has to be
  static const String LOGIN_TITLE = 'Login';

  static const Map<int, Color> COLOR_CODES = {
    0: Color.fromRGBO(18, 05, 16, 1), //background
//    1 : Color.fromRGBO(r, g, b, opacity);
  };

  static Map<String, Messages> _MESSAGES = {
    'italian' : new ItalianMessages(),
    'english' : new EnglishMessages(),
  };

}