import 'package:devicelocale/devicelocale.dart';
import 'package:flutter/material.dart';
import 'package:flutter_gym_app/screen/home_page.dart';
import 'package:flutter_gym_app/screen/login_page.dart';
import 'package:flutter_gym_app/utils/constants.dart';
import 'package:flutter_gym_app/utils/language/messages.dart';
import 'package:shared_preferences/shared_preferences.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {

  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp>{

  bool isLogged = false;

  @override
  Widget build(BuildContext context) {
    initialCheck();

    if (isLogged) {
      return MaterialApp(
        title: Constants.GYM_NAME,
        theme: ThemeData(
          visualDensity: VisualDensity.adaptivePlatformDensity,
        ),
        home: HomePage(),
      );
    } else {
      return MaterialApp(
        title: Constants.MESSAGES.message(type: MESSAGE_TYPE.LOGIN),
        theme: ThemeData(
          visualDensity: VisualDensity.adaptivePlatformDensity,
        ),
        home:  LoginPage(),
      );
    }
  }

  void initialCheck() async {
    Constants.PREFERENCES = await SharedPreferences.getInstance();

    String locale = await Devicelocale.currentLocale;

    setState(() {
      isLogged = Constants.PREFERENCES.get(Constants.LOG_STATUS) == true && Constants.PREFERENCES.get(Constants.USER_OR_OWNER) != null;
      Constants.setLanguage(language: locale);
    });
  }
}