import 'package:flutter/material.dart';
import 'package:flutter_gym_app/constants.dart';
import 'package:flutter_gym_app/screen/root.dart';
import 'package:flutter_statusbarcolor/flutter_statusbarcolor.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: gymName,
      theme: ThemeData(
        canvasColor: backgroundColor,
        primarySwatch: materialColorForeground,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ).copyWith(
        pageTransitionsTheme: const PageTransitionsTheme(
          builders: <TargetPlatform, PageTransitionsBuilder>{
            TargetPlatform.android: ZoomPageTransitionsBuilder(),
            TargetPlatform.iOS: ZoomPageTransitionsBuilder(),
          },
        ),
      ),
      home: RootPage(),
    );
  }
}