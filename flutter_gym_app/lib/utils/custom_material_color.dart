import 'package:flutter/material.dart';
import 'package:flutter_gym_app/utils/constants.dart';

class CustomColor {

  static const Map<int, Color> _COLOR_CODES = {
    0: Color.fromRGBO(18, 05, 16, 1.0), //background
    1: Color.fromRGBO(222, 44, 65, 1.0) //foreground
  };

  MaterialColor materialBackground(){
    return new MaterialColor(0xFF93cd48, _COLOR_CODES)[0];
  }

  MaterialColor materialForeground(){
    return new MaterialColor(0xFF93cd48, _COLOR_CODES)[1];
  }

  Color background(){
    return _COLOR_CODES[0];
  }


  Color foreground(){
    return _COLOR_CODES[1];
  }
}