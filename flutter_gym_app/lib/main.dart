import 'package:flutter/material.dart';
import 'package:flutter_gym_app/screen/home_page.dart';
import 'package:flutter_gym_app/screen/login_page.dart';
import 'package:flutter_gym_app/utils/constants.dart';
import 'package:flutter_gym_app/utils/custom_material_color.dart';
import 'package:flutter_gym_app/utils/language/messages.dart';
import 'package:shared_preferences/shared_preferences.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {

  bool isLogged;

  void initialCheck() async {

    Constants.PREFERENCES = await SharedPreferences.getInstance();

    isLogged = Constants.PREFERENCES.get(Constants.LOG_STATUS) == true;

    if (Constants.PREFERENCES.get(Constants.USER_OR_OWNER) == null) {
      isLogged = false;
    }
  }

  @override
  Widget build(BuildContext context) {

    initialCheck();

    Constants.LANGUAGE = Localizations.localeOf(context).languageCode;

    if (isLogged) {
      return MaterialApp(
        title: Constants.GYM_NAME,
        theme: ThemeData(
          primarySwatch: CustomColor().materialBackground(),
          visualDensity: VisualDensity.adaptivePlatformDensity,
        ),
        home: HomePage(),
      );
    } else {
      return MaterialApp(
          title: Constants.MESSAGES.message(type: MESSAGE_TYPE.LOGIN),
        theme: ThemeData(
          primarySwatch: CustomColor().materialBackground(),
          visualDensity: VisualDensity.adaptivePlatformDensity,
        ),
        home:  LoginPage(),
      );
    }
  }
}