import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_gym_app/constants.dart';
import 'package:flutter_gym_app/entity/user.dart';
import 'package:flutter_gym_app/handler/backendApi.dart';
import 'package:flutter_gym_app/handler/helperFunctions.dart';
import 'package:flutter_gym_app/provider/auth.dart';

class SignIn extends StatefulWidget {
  //final Function toggleView;

  SignIn();

  //SignIn(this.toggleView);
  @override
  _SignInState createState() => _SignInState();
}

class _SignInState extends State<SignIn> {

  final TextEditingController passwordController = TextEditingController();
  final TextEditingController emailController = TextEditingController();
  AuthService authService = new AuthService();

  bool wrongAttempt = false;
  bool forgotPassword = false;

  _SignInState();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: backgroundColor,
      body: Center(
        child: SingleChildScrollView(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Image.asset(gymLogo,
                height: MediaQuery.of(context).size.height/3,
              ),
              SizedBox(
                height: 50,
              ),
              Padding(
                padding: EdgeInsets.only(left: 30, right: 30),
                child: Column(
                  children: [
                    wrongAttempt ?
                        Container(
                          child: Text("Email e/o password errata",
                            style: TextStyle(color: Colors.red, fontSize: 16),
                            textAlign: TextAlign.right),
                ) : SizedBox(height: 0),
                    forgotPassword ?
                    Container(
                      child: Text("Inserire email per recupero password",
                          style: TextStyle(color: Colors.white, fontSize: 16),
                          textAlign: TextAlign.right),
                    ) : SizedBox(height: 0),
                    SizedBox(
                        height: 20
                    ),
                    Container(
                      decoration:BoxDecoration(
                        borderRadius: BorderRadius.circular(10.0),
                        color: Colors.white
                      ),
                      child: TextField(
                        controller: emailController,
                        keyboardType: TextInputType.emailAddress,
                        decoration: InputDecoration(
                          border: InputBorder.none,
                          contentPadding:  EdgeInsets.only(top: 14.0),
                          prefixIcon: Icon(
                            Icons.account_circle,
                            color: backgroundColor,
                          ),
                          hintText: "Inserisci email"
                        )
                      ),
                    ),
                    SizedBox(
                        height: 20
                    ),
                    !forgotPassword ?
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
                          contentPadding: EdgeInsets.only(top:14.0),
                          prefixIcon: Icon(
                            Icons.lock,
                            color: backgroundColor,
                          ),
                          hintText: "Inserisci password"
                        ),
                      ),
                    ) : SizedBox(height: 0),
                    !forgotPassword ?
                    Container(
                        padding: EdgeInsets.only(top: 10),
                        alignment: Alignment.centerRight,
                        child: GestureDetector(
                          onTap: () {
                            setState(() {
                              wrongAttempt = false;
                              forgotPassword = true;
                            });
                          },
                          child: Text("Password dimenticata?",
                            style: TextStyle(color: Colors.white, fontSize: 16),
                            textAlign: TextAlign.right,),
                        ),
                    )
                        :
                    Container(
                      padding: EdgeInsets.only(top: 10),
                      alignment: Alignment.centerRight,
                      child: GestureDetector(
                        onTap: () {
                          setState(() {
                            wrongAttempt = false;
                            forgotPassword = false;
                          });
                        },
                        child: Text("Torna al log in",
                          style: TextStyle(color: Colors.white, fontSize: 16),
                          textAlign: TextAlign.right,),
                      ),
                    ),
                    SizedBox(
                      height: 10,
                    ),

                    forgotPassword ?
                    Container(
                      width: 300,
                      child: RaisedButton(
                        elevation: 5.0,
                        onPressed: _forgotPassword,
                        padding: EdgeInsets.all(15.0),
                        shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(30.0)
                        ),
                        color: foregroundColor,
                        child: Text(
                          "Ricevi mail per cambio password",
                          style: TextStyle(
                              color: Colors.white,
                              letterSpacing: 1.0,
                              fontSize: 15.0,
                              fontWeight: FontWeight.bold,
                              fontFamily: 'OpenSans'
                          ),
                        ),
                      ),
                    )
                        :
                    Container(
                      width: 300,
                      child: RaisedButton(
                        elevation: 5.0,
                        onPressed: _signIn,
                        padding: EdgeInsets.all(15.0),
                        shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(30.0)
                        ),
                        color: foregroundColor,
                        child: Text(
                          "Log In",
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
              )
            ],
          ),
        ),
      ),
    );
  }
  
  _signIn() async {

    Map<String, dynamic> uid = await authService.signInWithEmailAndPassword(emailController.text, passwordController.text);

    if(uid == null) {
      HelperFunctions.saveUserLoggedIn(false);
      emailController.clear();
      passwordController.clear();
      setState(() {
        wrongAttempt = true;
        forgotPassword = false;
      });
    }
    else {
      User user = await BackendApi.getUserInfo();
      HelperFunctions.saveUUIDUser(user.uid);
      HelperFunctions.saveUserLoggedIn(true);
      HelperFunctions.saveUserEmail(user.email);
      HelperFunctions.saveUserOwner(user.isOwner);
    }
  }
  
  _forgotPassword() async {
    BackendApi.resetPassword(email: emailController.text);
    setState(() {
      wrongAttempt = false;
      forgotPassword = false;
    });
  }

}