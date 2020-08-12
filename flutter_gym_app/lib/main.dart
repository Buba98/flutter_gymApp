import 'package:flutter/material.dart';
import 'package:flutter_gym_app/screen/home_page.dart';
import 'package:flutter_gym_app/utils/constants.dart';
import 'package:flutter_gym_app/utils/custom_material_color.dart';
import 'package:flutter_gym_app/utils/language/messages.dart';
import 'package:shared_preferences/shared_preferences.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {

  bool isLogged;
  bool normalUser;

  void initialCheck() async {
    SharedPreferences preferences = await SharedPreferences.getInstance();

    debugPrint(preferences.get(Constants.LOG_STATUS));

    isLogged = preferences.get(Constants.LOG_STATUS) == true;

    if (preferences.get(Constants.USER_OR_OWNER) == null) {
      isLogged = false;
    } else {
      normalUser = preferences.get(Constants.USER_OR_OWNER);
    }
  }

  @override
  Widget build(BuildContext context) {
    initialCheck();

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