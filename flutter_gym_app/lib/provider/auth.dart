import 'package:flutter_gym_app/handler/backendApi.dart';

class AuthService {

  Future<String> signInWithEmailAndPassword(String email, String password) async {
    try {
      return await BackendApi.signInWithEmailAndPassword(
          email: email, password: password);
    } catch (e) {
      print(e.toString());
      return null;
    }
  }

  Future signUpByOwner(String fiscalCode, String name, String surname,
      String birthday, String email, String phoneNumber, bool owner) async {
    try {
      return await BackendApi.signUpByOwner(
        fiscalCode: fiscalCode,
        name: name,
        surname: surname,
        birthday: birthday,
        email: email,
        phoneNumber: phoneNumber,
        owner: owner
      );
    } catch (e) {
      print(e.toString());
      return null;
    }
  }

  Future resetPassword(String email) async {
    try {
      return await BackendApi.resetPassword(email: email);
    } catch (e) {
      print(e.toString());
      return null;
    }
  }

  Future signOut() async {
    try {
      return await BackendApi.signOut();
    } catch (e) {
      print(e.toString());
      return null;
    }
  }
}
