import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_gym_app/utils/constants.dart';
import 'package:flutter_gym_app/utils/custom_material_color.dart';
import 'package:flutter_gym_app/utils/language/messages.dart';
import 'package:flutter_gym_app/utils/widgets/button_with_animation.dart';
import 'package:flutter_gym_app/utils/widgets/custom_alert.dart';
import 'package:flutter_gym_app/utils/widgets/custom_drawer.dart';
import 'package:flutter_typeahead/flutter_typeahead.dart';
import 'package:qr/qr.dart';
import 'package:qr_flutter/qr_flutter.dart';
import 'package:http/http.dart' as http;
import 'package:rflutter_alert/rflutter_alert.dart';


class EntrancePage extends StatefulWidget{
  @override
  _EntrancePageState createState() => _EntrancePageState();
}

class _EntrancePageState extends State<EntrancePage> {

  String remainingDays = '';
  final TextEditingController uidController = new TextEditingController();


  @override
  Widget build(BuildContext context) {
    if (Constants.PREFERENCES.get(Constants.USER_OR_OWNER) == 'user') {
      getRemainingDays();
      return userEntrance();
    } else {
      return ownerEntrance();
    }
  }

  Scaffold ownerEntrance() {
    return Scaffold(
      backgroundColor: CustomColor().background(),
      appBar: AppBar(
        backgroundColor: CustomColor().foreground(),
        title: Text(
            Constants.MESSAGES.message(type: MESSAGE_TYPE.ENTRANCE_CARD)),
      ),
      drawer: CustomDrawer().coloredDrawer(context),
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
                        width: 300,
                        decoration: BoxDecoration(
                            borderRadius: BorderRadius.circular(10.0),
                            color: Colors.white
                        ),
                        child: TextField(
                          controller: uidController,
                          keyboardType: TextInputType.emailAddress,
                          decoration: InputDecoration(
                            border: InputBorder.none,
                            contentPadding: EdgeInsets.only(top: 14.0),
                            prefixIcon: Icon(
                              Icons.account_circle,
                              color: CustomColor().background(),
                            ),
                            hintText: Constants.MESSAGES.message(
                                type: MESSAGE_TYPE.INSERT_UID),
                          ),
                        )
                    ),
                    SizedBox(
                      height: 20,
                    ),
                    Container(
                        width: 300,
                        child: RaisedButton(
                          elevation: 5.0,
                          onPressed: () {
                            //todo add cam scan
                          },
                          padding: EdgeInsets.all(15.0),
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(30.0),
                          ),
                          color: CustomColor().foreground(),
                          child:
                          Center(
                            child: Text(
                              Constants.MESSAGES.message(
                                  type: MESSAGE_TYPE.SCAN),
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
                    SizedBox(
                      height: 30,
                    ),
                    Container(
                        width: 300,
                        child: RaisedButton(
                          elevation: 5.0,
                          onPressed: () {
                            //todo add enter
                          },
                          padding: EdgeInsets.all(15.0),
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(30.0),
                          ),
                          color: CustomColor().foreground(),
                          child:
                          Center(
                            child: Text(
                              Constants.MESSAGES.message(
                                  type: MESSAGE_TYPE.DONE),
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
        backgroundColor: CustomColor().background(),
        appBar: AppBar(
          backgroundColor: CustomColor().foreground(),
          title: Text(
              Constants.MESSAGES.message(type: MESSAGE_TYPE.ENTRANCE_CARD)),
        ),
        drawer: CustomDrawer().mainDrawer(context),
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
                          data: Constants.PREFERENCES.get(Constants.UID),
                          version: QrVersions.auto,
                          errorCorrectionLevel: QrErrorCorrectLevel.H,
                        ),
                      ),
                      SizedBox(
                        height: 20,
                      ),
                      Text(Constants.MESSAGES.message(
                          type: MESSAGE_TYPE.REMAINING_DAYS) + remainingDays,
                        style: TextStyle(color: Colors.white, fontSize: 16),),
                    ],
                  ),
                ),
              ],
            ),
          ),
        )
    );
  }

  Future<void> getRemainingDays() async {
    String _remainingDays = '';

    try {
      http.Response response = await http.post(
          Constants.SERVER_ADDRESS + '/api/remainingDays', body: jsonEncode(
          <String, String>{
            'userId': Constants.PREFERENCES.get(Constants.UID)
          }));
      if (response.statusCode == 200) {
        _remainingDays = jsonDecode(response.body)['remainingDays'];
      } else {
        CustomAlert().standardAlert(context: context,
            alertType: AlertType.error,
            title: Constants.MESSAGES.message(
                type: MESSAGE_TYPE.ERROR),
            text: "").show();
        _remainingDays = 'NaN';
      }
    } catch (e) {
      _remainingDays = 'NaN';
    }

    setState(() {
      remainingDays = _remainingDays;
    });
  }
}