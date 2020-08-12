import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class HomePage extends StatefulWidget{
  HomePage();

  @override
  State<StatefulWidget> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {

  _HomePageState();

  @override
  void initState() async {
    if ()
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Color.fromRGBO(18, 5, 16, 1),
      body: Padding(
        padding: EdgeInsets.only(top: 20),
        child: GridView(
          gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
            crossAxisCount: 2,
            childAspectRatio: 0.9,
          ),
          children: <GridTile>[
            IconCustom().tileFunctionSelection(title: 'Tessera di ingresso', image: '', context: context,  )
          ],
        ),
      ),
    );
  }
}