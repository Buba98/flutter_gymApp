import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_gym_app/constants.dart';
import 'package:flutter_gym_app/handler/backendApi.dart';
import 'package:flutter_gym_app/handler/helperFunctions.dart';
import 'package:flutter_gym_app/screen/home.dart';
import 'package:qr_flutter/qr_flutter.dart';
import 'package:qrscan/qrscan.dart' as scanner;


class Entrance extends StatefulWidget{
  @override
  _EntranceState createState() => _EntranceState();
}

class _EntranceState extends State<Entrance> {

  bool isOwner = false;
  String message = '';
  String uid = '';
  
  @override
  void initState() {
    super.initState();
    _initState();
  }
  
  Future<void> _initState() async {
    bool _isOwner = await HelperFunctions.isUserOwner();
    String _uid = await HelperFunctions.getUUIDUser();

    setState(() {
      isOwner = _isOwner;
      uid = _uid;
    });
  }



  @override
  Widget build(BuildContext context) {
    if (isOwner)
      return ownerEntrance();

    else
      return userEntrance();
  }

  Scaffold ownerEntrance() {
    return Scaffold(
      backgroundColor: backgroundColor,
      appBar: AppBar(
        backgroundColor: foregroundColor,
        title: Text(
            "Scansione entrata"
        )
      ),
      drawer: mainDrawer(context),
      body: Center(
        child: SingleChildScrollView(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Padding(
                padding: EdgeInsets.only(left: 30, right: 30),
                child: Column(
                  children: [
                    SizedBox(
                      height: 20,
                    ),
                    Container(
                      child: Text(message,
                          style: TextStyle(color: Colors.red, fontSize: 16),
                          textAlign: TextAlign.right),
                    ),
                    SizedBox(
                      height: 20,
                    ),
                    Container(
                        width: 300,
                        child: RaisedButton(
                          elevation: 5.0,
                          onPressed: () => _scan(),
                          padding: EdgeInsets.all(15.0),
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(30.0),
                          ),
                          color: foregroundColor,
                          child:
                          Center(
                            child: Text(
                              "Scansiona",
                              style: TextStyle(
                                color: Colors.white,
                                letterSpacing: 1.5,
                                fontSize: 15.0,
                                fontWeight: FontWeight.bold,
                                fontFamily: 'OpenSans',
                              ),
                            ),
                          ),
                        )
                    ),
                  ],
                ),
              )
            ],
          ),
        ),
      ),
    );
  }

  Scaffold userEntrance() {
    return Scaffold(
        backgroundColor: backgroundColor,
        appBar: AppBar(
          backgroundColor: foregroundColor,
          title: Text(
              "Tessara di ingresso"),
        ),
        drawer: mainDrawer(context),
        body: Center(
          child: SingleChildScrollView(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Padding(
                  padding: EdgeInsets.only(left: 30, right: 30),
                  child: Column(
                    children: [
                      Container(
                        decoration: BoxDecoration(
                            borderRadius: BorderRadius.circular(10.0),
                            color: Colors.white
                        ),
                        child: QrImage(
                          data: uid,
                          version: QrVersions.auto,
                          errorCorrectionLevel: QrErrorCorrectLevel.H,
                        ),
                      ),
                      SizedBox(
                        height: 20,
                      ),
                      Container(
                        width: 300,
                        child: RaisedButton(
                          elevation: 5.0,
                          onPressed: () => Navigator.push(context, MaterialPageRoute(
                              builder: (context) => HomeScreen())),
                          padding: EdgeInsets.all(15.0),
                          shape: RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(30.0)
                          ),
                          color: foregroundColor,
                          child: Text(
                            "Dettagli iscrizione",
                            style: TextStyle(
                                color: Colors.white,
                                letterSpacing: 1.5,
                                fontSize: 15.0,
                                fontWeight: FontWeight.bold,
                                fontFamily: 'OpenSans'
                            ),
                          ),
                        ),
                      )
                    ],
                  ),
                ),
              ],
            ),
          ),
        )
    );
  }

  Future _scan() async {
    switch(await BackendApi.entranceRegistration(await scanner.scan())){
      case 0 :
        setState(() {
          message = 'Entrata eseguita con successo';
        });
        break;
      case 1:
        setState(() {
          message = 'Nessuna iscrizione valida';
        });
        break;
      case -1:
        setState(() {
          message = 'Errore del server';
        });
        break;
    }
  }
}