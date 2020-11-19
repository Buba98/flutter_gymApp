import 'dart:async';
import 'dart:async';
import 'dart:convert';
import 'dart:html';

import 'package:flutter_gym_app/constants.dart';
import 'package:flutter_gym_app/entity/currentSubscription.dart';
import 'package:flutter_gym_app/entity/user.dart';
import 'package:http/http.dart' as http;

import 'helperFunctions.dart';

class BackendApi {

  Map<String, dynamic> serverUnreachable = {
    "statusCode": 600,
    "token": "Server unreachable"
  };

  static Future<http.Response> _messageToServer(String json,
      destination) async {
    return await http.post(
        destination,
        body: json
    );
  }

  static Future<Map<String, dynamic>> signInWithEmailAndPassword(String email,
      String password) async {
    final http.Response response = await _messageToServer(
        jsonEncode(<String, dynamic>{
          'email': email,
          'password': password
        }), serverUrl + 'api/v1/standard/signIn');

    Map<String, dynamic> map;

    if (response.statusCode == 200) {
      map = jsonDecode(response.body);
    } else {
      map = {
        "statusCode": 600,
        "token": "Server unreachable"
      };
    }

    return map;
  }


  static Future<Map<String, dynamic>> signUp(String fiscalCode, String name,
      String surname, String birthday, String email, String phoneNumber,
      bool owner) async {
    final http.Response response = await _messageToServer(
        jsonEncode(<String, dynamic>{
          'fiscalCode': fiscalCode,
          'name': name,
          'surname': surname,
          'birthday': birthday,
          'email': email,
          'password': defaultPassword,
          'phone': phoneNumber,
          'owner': owner
        }), serverUrl + 'api/v1/administration'
    );

    Map<String, dynamic> map;

    if (response.statusCode == 200) {
      map = jsonDecode(response.body);
    } else {
      map = {
        "statusCode": 600,
        "token": "Server unreachable"
      };
    }
  }

  static Future<Map<String, dynamic>> resetPassword({String email}) async {
    final http.Response response = await _messageToServer(
      jsonEncode(<String, dynamic>{
        "email" : email
      }), serverUrl + 'api/v1/standard/resetPassword'
    );

    if (response.statusCode == 200){
      return jsonDecode(response.body);
    } else {
      return <String, dynamic>{
        "statusCode": 600,
        "token": "Server unreachable"
      };
    }
  }

  static Future<Map<String, dynamic>> signOut() async {
    final http.Response response = await _messageToServer(jsonEncode(<String, dynamic>{
      "uuid" : HelperFunctions.getUUIDUser()
    }), serverUrl + 'api/v1/standard/resetPassword');

    if (response.statusCode == 200)
      return jsonDecode(response.body);
    else
      return (<String, dynamic>{
        "statusCode": 600,
        "token": "Server unreachable"
      });
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

  static Future<CurrentSubscription> currentSubscription() async{
    String uid = await HelperFunctions.getUUIDUser();
    final http.Response response = await http.post(
      serverUrl + 'api/v1/standard/currentSubscription',
      body: jsonEncode(
        <String, String>{
          "uuid" :  uid
        }
      )
    );

    if(response.statusCode == 200){
      dynamic json = jsonDecode(response.body);

      if (json['status'] == 200){
        json = json['currentSubscription'];
        return CurrentSubscription.fromJson(json);  
      }
    }
    return null;
  }
}