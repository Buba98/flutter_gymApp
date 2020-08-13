import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_gym_app/utils/constants.dart';
import 'package:flutter_gym_app/utils/custom_material_color.dart';
import 'package:flutter_gym_app/utils/language/messages.dart';
import 'package:flutter_gym_app/utils/widgets/button_with_animation.dart';
import 'package:flutter_gym_app/utils/widgets/custom_alert.dart';
import 'package:http/http.dart' as http;
import 'package:rflutter_alert/rflutter_alert.dart';

import 'home_page.dart';


class LoginPage extends StatelessWidget {

  final TextEditingController passwordController = TextEditingController();
  final TextEditingController emailController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: CustomColor().background(),
      body: Center(
        child: SingleChildScrollView(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Image.asset(Constants.GYM_LOGO, height: MediaQuery.of(context).size.height / 3),
              SizedBox(
                height: 50,
              ),
              Padding(
                padding: EdgeInsets.only(left: 30, right: 30),
                child: Column(
                  children: [
                    Container(
                        decoration: BoxDecoration(
                            borderRadius: BorderRadius.circular(10.0),
                            color: Colors.white
                        ),
                        child: TextField(
                          controller: emailController,
                          keyboardType: TextInputType.emailAddress,
                          decoration: InputDecoration(
                            border: InputBorder.none,
                            contentPadding: EdgeInsets.only(top: 14.0),
                            prefixIcon: Icon(
                              Icons.account_circle,
                              color: CustomColor().background(),
                            ),
                            hintText: Constants.MESSAGES.message(
                                type: MESSAGE_TYPE.EMAIL),
                          ),
                        )
                    ),
                    SizedBox(
                      height: 20,
                    ),
                    Container(
                      decoration: BoxDecoration(
                          borderRadius: BorderRadius.circular(10.0),
                          color: Colors.white
                      ),
                      child: TextField(
                        controller: passwordController,
                        obscureText: true,
                        decoration: InputDecoration(
                          border: InputBorder.none,
                          contentPadding: EdgeInsets.only(top: 14.0),
                          prefixIcon: Icon(
                            Icons.lock_outline,
                            color: CustomColor().background(),
                          ),
                          hintText: Constants.MESSAGES.message(
                              type: MESSAGE_TYPE.PASSWORD),
                        ),
                      ),
                    ),
                    Container(
                        padding: EdgeInsets.only(top: 10),
                        alignment: Alignment.centerRight,
                        child: GestureDetector(
                          onTap: () {
                            //todo create a alert with a text panel to enter email and a send button
//                            Navigator.push(context, MaterialPageRoute(
//                                builder: (context) => ResetPasswordPage()),
//                            );
                          },
                          child: Text(Constants.MESSAGES.message(
                              type: MESSAGE_TYPE.FORGOTTEN_PASSWORD),
                            style: TextStyle(color: Colors.white, fontSize: 16),
                            textAlign: TextAlign.right,),
                        )
                    ),
                    SizedBox(
                      height: 30,
                    ),
                    ButtonWithAnimation(
                        doLogin: doLogin, parentContext: context),
                  ],
                ),
              )
            ],
          ),
        ),
      ),
    );
  }

  void doLogin(BuildContext context) async {
    final email = Text(emailController.text).data;
    final password = Text(passwordController.text).data;
    http.Response response = await http.post(
        Constants.SERVER_ADDRESS + '/api/login', body: jsonEncode(
        <String, String>{'email': email, 'password': password}));
    if (response.statusCode == 200) {
      Constants.PREFERENCES.setString(
          Constants.UID, jsonDecode(response.body)['userId']);
      Navigator.push(context, MaterialPageRoute(builder: (context) => HomePage()));
    } else {
      CustomAlert().standardAlert(context: context,
          alertType: AlertType.error,
          title: Constants.MESSAGES.message(
              type: MESSAGE_TYPE.ERROR),
          text: Constants.MESSAGES.message(
              type: MESSAGE_TYPE.ERROR_LOGIN));
    }
  }
}