import 'package:flutter/cupertino.dart';
import 'package:flutter_gym_app/handler/helperFunctions.dart';
import 'package:flutter_gym_app/screen/authentication/signUp.dart';

import 'authentication/signIn.dart';
import 'home.dart';

class RootPage extends StatefulWidget{
  @override
  _RootPageState createState() => new _RootPageState();
}

class _RootPageState extends State<RootPage> {
  bool userIsLoggedIn = false;

  @override
  void initState() {
    getLoggedInState();
    super.initState();
  }

  getLoggedInState() async {
    await HelperFunctions.isUserLoggedIn().then((value) {
      setState(() {
        userIsLoggedIn = value;
      });
    });
  }

  onSignIn() {
    setState(() {
      userIsLoggedIn = true;
    });
  }

  @override
  Widget build(BuildContext context) {
    switch (userIsLoggedIn) {
      case true:
        return HomeScreen();
      case false:
        // return SignIn();
        return HomeScreen();

      default:
        return SignIn();
    }
  }
}