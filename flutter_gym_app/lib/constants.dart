import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:flutter_gym_app/screen/authentication/signUp.dart';
import 'package:flutter_gym_app/screen/entrance.dart';
import 'package:flutter_gym_app/screen/home.dart';
import 'package:flutter_gym_app/screen/info.dart';

final String gymName = 'GYM_NAME';

final String serverUrl= 'http://localhost:8080/';

final String defaultPassword = '1a2b3c4d5e';

final String gymLogo = "assets/logo.png";


final Color backgroundColor = Color.fromRGBO(18, 05, 16, 1.0); //background
final Color foregroundColor = Color.fromRGBO(225, 127, 80, 1.0); //foreground

final kLabelStyle = TextStyle(
  color: Colors.black,
  fontWeight: FontWeight.bold,
  fontFamily: 'OpenSans',
);

final kBoxDecorationStyle = BoxDecoration(

  color: Color(0xFFB2DFDB),
  borderRadius: BorderRadius.circular(10.0),
  boxShadow: [
    BoxShadow(
      color: Colors.black12,
      blurRadius: 6.0,
      offset: Offset(0, 2),
    ),
  ],
);

final kHintTextStyle = TextStyle(
  color: Colors.blueGrey,
  fontFamily: 'OpenSans',
);

final Map<String, Map<String, String>> dictionary = {
  "it":{
    "enter_email":"Inserisci email",
  },
  "en":{
    "enter_email":"Enter email",
  }
};


Drawer mainDrawer(BuildContext context) {
  return Drawer(
      child: ListView(
        padding: EdgeInsets.zero,
        children: <Widget>[
          DrawerHeader(
            decoration: BoxDecoration(
              color: foregroundColor,
            ),
            child: GestureDetector(
              onTap: () =>
                  Navigator.push(context,
                      MaterialPageRoute(
                          builder: (context) => HomeScreen())),
              child: Container(
                decoration: BoxDecoration(
                  image: DecorationImage(
                      image: AssetImage(gymLogo)),
                ),
              ),),
          ),
          _drawerTile(text: "Tessera di ingresso",
              context: context,
              destination: Entrance(),
              icon: Icons.card_membership),
          _drawerTile(text: "Registranzione utente",
              context: context,
              destination: SignUp(),
              icon: Icons.app_registration),
          _drawerTile(text: "Info",
            context: context,
            destination: Info(),
            icon: Icons.info_outline
          )
        ],
      )
  );
}

ListTile _drawerTile({String text, BuildContext context, Widget destination, IconData icon}) {
  return ListTile(
      title: Text(
        text,
        style: TextStyle(color: Colors.white),),
      leading: Icon(icon, color: Colors.white,),
      onTap: () {
        debugPrint(context.toString());
        if (context.widget.toString() == destination.toString())
          Navigator.pop(context);
        else
          Navigator.push(context,
              MaterialPageRoute(
                  builder: (context) => destination));
      }
  );
}

Map<int, Color> colorCodesForeground = {
  50: foregroundColor.withOpacity(.1),
  100: foregroundColor.withOpacity(.2),
  200: foregroundColor.withOpacity(.3),
  300: foregroundColor.withOpacity(.4),
  400: foregroundColor.withOpacity(.5),
  500: foregroundColor.withOpacity(.6),
  600: foregroundColor.withOpacity(.7),
  700: foregroundColor.withOpacity(.8),
  800: foregroundColor.withOpacity(.9),
  900: foregroundColor
};

MaterialColor materialColorForeground = new MaterialColor(0xFFff7f50, colorCodesForeground);

Map<int, Color> colorCodesBackGround = {
  50: backgroundColor.withOpacity(.1),
  100: backgroundColor.withOpacity(.2),
  200: backgroundColor.withOpacity(.3),
  300: backgroundColor.withOpacity(.4),
  400: backgroundColor.withOpacity(.5),
  500: backgroundColor.withOpacity(.6),
  600: backgroundColor.withOpacity(.7),
  700: backgroundColor.withOpacity(.8),
  800: backgroundColor.withOpacity(.9),
  900: backgroundColor
};

MaterialColor materialColorBackground = new MaterialColor(0xFF120510, colorCodesBackGround);
