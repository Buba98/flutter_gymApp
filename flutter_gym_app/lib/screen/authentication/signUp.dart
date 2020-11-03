import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_gym_app/constants.dart';
import 'package:flutter_gym_app/handler/backendApi.dart';

class SignUp extends StatefulWidget {
  //final Function toggleView;

  SignUp();

  //SignIn(this.toggleView);
  @override
  _SignUpState createState() => _SignUpState();
}

class _SignUpState extends State<SignUp> {

  final TextEditingController email = TextEditingController();
  final TextEditingController fiscalCode = TextEditingController();
  final TextEditingController name = TextEditingController();
  final TextEditingController surname = TextEditingController();
  final TextEditingController phoneNumber = TextEditingController();
  final TextEditingController birthdayController = TextEditingController();
  DateTime birthday = DateTime.now();
  bool isOwner = false;
  bool wrongAttempt = false;
  bool serverError = false;

  Future<void> _selectDate(BuildContext context) async {
    final DateTime picked = await showDatePicker(
        context: context,
        initialDate: birthday,
        firstDate: DateTime(1900),
        lastDate: DateTime(DateTime.now().year, 12));
    if (picked != null && picked != birthday)
      setState(() {
        birthday = picked;
        birthdayController.text = "${birthday.toLocal()}".split(' ')[0];
      });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      drawer: mainDrawer(context),
      appBar: AppBar(title: Text("Registrazione utente")),
      backgroundColor: backgroundColor,
      body: Center(
          child: SingleChildScrollView(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                SizedBox(height: 30),
                Padding(
                  padding: EdgeInsets.only(left: 30, right: 30),
                  child: Column(
                    children: [
                      serverError ?
                      Container(
                        child: Text("Errore nel server, riprova",
                            style: TextStyle(color: Colors.red, fontSize: 16),
                            textAlign: TextAlign.right),
                      ) : SizedBox(height: 10),
                      wrongAttempt ?
                      Container(
                        child: Text("Errore inserimento dati, riprova",
                            style: TextStyle(color: Colors.red, fontSize: 16),
                            textAlign: TextAlign.right),
                      ) : SizedBox(height: 10),
                      SizedBox(height: 10),
                      Container(
                        child: Text("Inserire dati per registrazione",
                            style: TextStyle(color: Colors.white, fontSize: 16),
                            textAlign: TextAlign.right),
                      ),
                      SizedBox(height: 20,),
                      _createInput(
                          name, TextInputType.name, Icons.account_circle,
                          "Inserisci nome"),
                      SizedBox(height: 20,),
                      _createInput(
                          surname, TextInputType.name, Icons.account_circle,
                          "Inserisci cognome"),
                      SizedBox(height: 20,),
                      _createInput(
                          fiscalCode, TextInputType.name, Icons.account_circle,
                          "Inserisci codice fiscale"),
                      SizedBox(height: 20,),
                      _createInput(
                          phoneNumber, TextInputType.phone, Icons.phone,
                          "Inserisci numero di telefono"),
                      SizedBox(height: 20,),
                      _createInput(
                          email, TextInputType.emailAddress, Icons.email,
                          "Inserisci numero di telefono"),
                      SizedBox(height: 20,),
                      Container(
                        decoration: BoxDecoration(
                            borderRadius: BorderRadius.circular(10.0),
                            color: Colors.white
                        ),
                        child: TextField(
                            readOnly: true,
                            controller: birthdayController,
                            onTap: () {
                              _selectDate(context);
                            },
                            keyboardType: TextInputType.datetime,
                            decoration: InputDecoration(
                                border: InputBorder.none,
                                contentPadding: EdgeInsets.only(top: 14.0),
                                prefixIcon: Icon(
                                  Icons.date_range,
                                  color: backgroundColor,
                                ),
                                hintText: "Inserisci data di nascita"
                            )
                        ),
                      ),
                      SizedBox(height: 20,),
                      Row(
                        children: [
                          Theme(data: ThemeData(
                              unselectedWidgetColor: Colors.white
                          ), child: Checkbox(
                              value: isOwner,
                              activeColor: foregroundColor,
                              checkColor: Colors.white,
                              onChanged: (bool value) {
                                setState(() {
                                  isOwner = value;
                                });
                              })),
                          Text(
                            "Impiegato in palestra",
                            style: TextStyle(
                                color: Colors.white,
                                letterSpacing: 1.5,
                                fontSize: 15.0,
                                fontWeight: FontWeight.bold,
                                fontFamily: 'OpenSans'
                            ),
                          ),
                        ],
                      ),
                      SizedBox(height: 20,),
                      Container(
                        width: 300,
                        child: RaisedButton(
                          elevation: 5.0,
                          onPressed: _signUp,
                          padding: EdgeInsets.all(15.0),
                          shape: RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(30.0)
                          ),
                          color: foregroundColor,
                          child: Text(
                            "Sign Up",
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
          )
      ),
    );
  }

  Container _createInput(TextEditingController textEditingController, TextInputType textInputType, IconData icon, String hintText){
    return Container(
      decoration:BoxDecoration(
          borderRadius: BorderRadius.circular(10.0),
          color: Colors.white
      ),
      child: TextField(
          controller: textEditingController,
          keyboardType: textInputType,
          decoration: InputDecoration(
              border: InputBorder.none,
              contentPadding:  EdgeInsets.only(top: 14.0),
              prefixIcon: Icon(
                icon,
                color: backgroundColor,
              ),
              hintText: hintText
          )
      ),
    );
  }

  _signUp() async{
    String _fiscalCode = fiscalCode.text;
    String _email = email.text;
    String _name = name.text;
    String _surname = surname.text;
    String _birthday = birthdayController.text;
    String _phoneNumber = phoneNumber.text;

    if (_fiscalCode.isEmpty || _email.isEmpty || _name.isEmpty || _surname.isEmpty ||
    _birthday.isEmpty || _phoneNumber.isEmpty){
      setState(() {
        serverError = false;
        wrongAttempt = true;
      });
    } else {
      setState(() {
        serverError = false;
        wrongAttempt = false;
      });
      if (await BackendApi.signUpByOwner(fiscalCode: _fiscalCode, name: _name, surname: _surname, birthday: _birthday, email: _email, phoneNumber: _phoneNumber, owner: isOwner))
        setState(() {
          wrongAttempt = false;
          serverError = false;
        });
      else
        setState(() {
          wrongAttempt = false;
          serverError = true;
        });

    }
  }

}