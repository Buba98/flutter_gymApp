import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_gym_app/utils/custom_material_color.dart';
import 'package:flutter_gym_app/utils/language/messages.dart';
import 'package:flutter_spinkit/flutter_spinkit.dart';

import '../constants.dart';

class ButtonWithAnimation extends StatefulWidget{

  ButtonWithAnimation({this.doLogin, this.parentContext});
  final Function doLogin;
  final BuildContext parentContext;

  @override
  ButtonWithAnimationState createState() => ButtonWithAnimationState(doLogin: doLogin, parentContext: parentContext);
}

class ButtonWithAnimationState extends State<ButtonWithAnimation> {

  ButtonWithAnimationState({this.doLogin, this.parentContext});

  final Function doLogin;
  final BuildContext parentContext;
  int index = 0;


  @override
  Widget build(BuildContext context) {
    return Container(
      width: 300,
      child: RaisedButton(
          elevation: 5.0,
          onPressed: () {
            loginCallback();
          },
          padding: EdgeInsets.all(15.0),
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(30.0),
          ),
          color: CustomColor().foreground(),
          child: IndexedStack(index: index, children: <Widget>[
            Center(
              child: Text(
                Constants.MESSAGES.message(
                    type: MESSAGE_TYPE.ACCESS),
                style: TextStyle(
                  color: Colors.white,
                  letterSpacing: 1.5,
                  fontSize: 15.0,
                  fontWeight: FontWeight.bold,
                  fontFamily: 'OpenSans',
                ),
              ),
            ),
            Center(
              child: SpinKitWave(
                color: Colors.white,
                size: 15.0,
              ),
            )
          ],)
      ),
    );
  }

  void loginCallback() async {
    setState(() {
      index = 1;
    });
    await doLogin(parentContext);
    setState(() {
      index = 0;
    });
  }
}