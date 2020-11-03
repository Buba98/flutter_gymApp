import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_gym_app/constants.dart';
import 'package:flutter_gym_app/handler/helperFunctions.dart';
import 'package:flutter_gym_app/screen/authentication/signUp.dart';
import 'package:flutter_gym_app/screen/info.dart';

import 'entrance.dart';

class HomeScreen extends StatefulWidget {
  @override
  _HomeScreenState createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {

  _HomeScreenState();
  bool isOwner = false;

  Future<void> _isOwnerHandler() async {

    bool _isOwner = await HelperFunctions.isUserOwner();

    setState(() {
      isOwner = _isOwner;
    });
  }


  @override
  void initState(){
    super.initState();
    _isOwnerHandler();
  }

  List<Widget> _createListOfWidget() {

    return <Widget>[
      _customTile(context: context, icon: Icons.card_membership, text: "Tessara d'ingresso", destination: Entrance()),
      isOwner ?
      _customTile(context: context, icon: Icons.app_registration, text: "Registra nuovo utente", destination: SignUp())
          :
          _customTile(context: context, icon: Icons.app_registration, text: "Registra", destination: SignUp()),
      _customTile(context: context, icon: Icons.info_outline, text: "Info", destination: Info())
    ];
  }

  GridTile _customTile(
      {BuildContext context, IconData icon, String text, Widget destination}) {
    return GridTile(
        child: GestureDetector(
          child: Padding(
              padding: EdgeInsets.all(15),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Container(
                    height: 150,
                    width: 200,
                    decoration: BoxDecoration(
                      color: Colors.white,
                      borderRadius: BorderRadius.all(Radius.circular(20)),
                    ),
                    child: SizedBox(
                      height: 150,
                      width: 200,
                      child: IconButton(
                        iconSize: 100,
                        padding: new EdgeInsets.all(0.0),
                        color: Colors.white,
                        icon: Icon(icon),
                        onPressed: null,
                      ),
                    ),
                  ),

                  SizedBox(height: 13,),
                  Center(
                    child: Text(
                      text,
                      style: TextStyle(color: Colors.white,
                          fontSize: 18,
                          fontFamily: 'Rowdies'),),
                  )
                ],
              )
          ),
          onTap: () =>
              Navigator.push(context,
                  MaterialPageRoute(builder: (context) => destination)),
        )
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: new AppBar(
        title: Text("Homepage"),
        automaticallyImplyLeading: false,
      ),
     backgroundColor: backgroundColor,
     body: Padding(
       padding: EdgeInsets.only(top: 20),
       child: GridView(
         gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
           crossAxisCount: 2,
           childAspectRatio: 0.9
         ),
         children: _createListOfWidget(),
       ),
     ),
    );
  }
}
