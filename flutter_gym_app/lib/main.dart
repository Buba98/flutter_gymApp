import 'package:flutter/material.dart';
import 'package:flutter_gym_app/constants.dart';
import 'package:flutter_gym_app/screen/root.dart';
import 'package:flutter_statusbarcolor/flutter_statusbarcolor.dart';
import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';

void main() async{
  WidgetsFlutterBinding.ensureInitialized();
  final Future<Database> database = openDatabase(
    join(await getDatabasesPath(), 'gym_app.db'),
    onCreate: (db, version){
      db.execute("CREATE TABLE exercise(id INTEGER PRIMARY KEY, name TEXT, imageOrGif TEXT, typeOfExercise INTEGER, name TEXT)");
      db.execute("CREATE TABLE exerciseInTraining(id INTEGER PRIMARY KEY, exerciseId INTEGER, setsId TEXT, description TEXT)");
      db.execute("CREATE TABLE set()");
      db.execute("CREATE TABLE ()");
      db.execute("CREATE TABLE ()");
      db.execute("CREATE TABLE ()");
      db.execute("CREATE TABLE ()");
      db.execute("CREATE TABLE ()");
      db.execute("CREATE TABLE ()");
      return;
    }
  );
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