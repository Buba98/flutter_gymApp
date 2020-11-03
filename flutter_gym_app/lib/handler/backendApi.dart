import 'dart:convert';

import 'package:flutter_gym_app/constants.dart';
import 'package:flutter_gym_app/entity/user.dart';
import 'package:http/http.dart' as http;

import 'helperFunctions.dart';

class BackendApi{
  static Future<String> signInWithEmailAndPassword({String email, String password}) async {
    final http.Response response = await http.post(
        serverUrl + 'api/v1/standard/signIn',
      body: jsonEncode(<String, String>{
        'email' : email,
        'password' : password
      })
    );

    if(response.statusCode == 200 && jsonDecode(response.body)['status'] == 200)
      return jsonDecode(response.body)['token'];
    else
      return null;
  }
  
  static Future<bool> signUpByOwner({String fiscalCode, String name, String surname, String birthday, String email, String phoneNumber, bool owner}) async {
    final http.Response response = await http.post(
        serverUrl + 'api/v1/administration',
        body: jsonEncode(<String, dynamic>{
          "uuidAuthentication": HelperFunctions.getUUIDUser(),
          "requestType": 0,
          "fiscalCode": fiscalCode,
          "name": name,
          "surname": surname,
          "birthday": birthday,
          "password": defaultPassword,
          "email": email,
          "phoneNumber": phoneNumber,
          "owner": owner
        })
    );

    if(response.statusCode == 200 && jsonDecode(response.body)['status'] == 200)
      return true;
    else
      return false;
  }

  static Future<bool> resetPassword({String email}) async {
    final http.Response response = await http.post(
      serverUrl + 'api/v1/standard/resetPassword',
      body: jsonEncode(<String, dynamic>{
        "email" : email
      })
    );

    if (response.statusCode == 200 && jsonDecode(response.body)['status'] == 200)
      return true;
    else
      return false;
  }

  static Future<bool> signOut() async {
    final http.Response response = await http.post(
        serverUrl + 'api/v1/standard/resetPassword',
        body: jsonEncode(<String, dynamic>{
        "uuid" : HelperFunctions.getUUIDUser()
        }));

    if (response.statusCode == 200 && jsonDecode(response.body)['status'] == 200)
      return true;
    else
      return false;
  }

  static Future<User> getUserInfo() async {
    String uid = await HelperFunctions.getUUIDUser();
    final http.Response response = await http.post(
        serverUrl + 'api/v1/standard/getInfo',
        body: jsonEncode(<String, dynamic>{
          "uuid": uid
        })
    );
    if (response.statusCode == 200) {
      dynamic json = jsonDecode(response.body);
      if (json['status'] == 200) {
        return new User(
          uid: uid,
          email: json['email'],
          isOwner: json['isOwner']
        );
      } else
        return null;
    }
    else
      return null;
  }

  static Future<int> entranceRegistration(String userId) async {
    String uid = await HelperFunctions.getUUIDUser();
    final http.Response response = await http.post(
      serverUrl + 'api/v1/administration',
      body: jsonEncode(<String, dynamic>{
        "uuid" : uid,
        "userId" : userId
      })
    );

    if(response.statusCode == 200){
      switch(jsonDecode(response.body)["status"]){
        case 200: return 0;
        case 400: return 1;
        case 600: return -1;
        default: return -1;
      }
    } else
      return -1;
  }
}