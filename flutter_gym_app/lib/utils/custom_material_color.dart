import 'package:flutter/material.dart';
import 'package:flutter_gym_app/utils/constants.dart';

class CustomColor {
  MaterialColor materialBackground(){
    return new MaterialColor(0xFF93cd48, Constants.COLOR_CODES)[0];
  }

  MaterialColor materialForeground(){
    return new MaterialColor(0xFF93cd48, Constants.COLOR_CODES)[1];
  }

  Color background(){
    return Constants.COLOR_CODES[0];
  }


  Color foreground(){
    return Constants.COLOR_CODES[1];
  }
}