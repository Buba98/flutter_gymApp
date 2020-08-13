import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_gym_app/utils/constants.dart';
import 'package:flutter_gym_app/utils/custom_material_color.dart';
import 'package:flutter_gym_app/utils/language/messages.dart';
import 'package:rflutter_alert/rflutter_alert.dart';


class CustomAlert {

  Alert standardAlert({BuildContext context, AlertType alertType, String title, String text}){
    return Alert(
      context: context,
      type: alertType,
      title: title,
      desc: text,
      buttons: [
        DialogButton(
          child: Text(
            Constants.MESSAGES.message(type: MESSAGE_TYPE.OK),
            style: TextStyle(color: Colors.white, fontSize: 20),
          ),
          onPressed: () => Navigator.of(context, rootNavigator: true).pop(),
          color: CustomColor().foreground(),
        )
      ],
    );
  }

  Alert welcomeMessage({BuildContext context}) {
      return Alert(
        context: context,
        type: AlertType.info,
        title: Constants.MESSAGES.message(type: MESSAGE_TYPE.WELCOME) + Constants.GYM_NAME,
        buttons: [
          DialogButton(
            child: Text(
              Constants.MESSAGES.message(type: MESSAGE_TYPE.LETS_START),
              style: TextStyle(color: Colors.white, fontSize: 20),
            ),
            onPressed: () => Navigator.pop(context),
            color: CustomColor().foreground(),
          )
        ],
      );
  }
}