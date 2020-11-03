import 'package:shared_preferences/shared_preferences.dart';

class HelperFunctions{

  static String _isUserLoggedInKey = "ISLOGGEDIN";
  static String _uuidUserKey = "UUIDKEY";
  static String _isOwnerKey = "OWNERKEY";
  static String _emailUserKey = "EMAILKEY";

  static Future<bool> saveUserLoggedIn(bool isUserLoggedIn) async {
    SharedPreferences preferences = await SharedPreferences.getInstance();
    return await preferences.setBool(_isUserLoggedInKey, isUserLoggedIn);
  }
  
  static Future<bool> isUserLoggedIn() async{
    try {
      SharedPreferences preferences = await SharedPreferences.getInstance();

      switch(preferences.getBool(_isOwnerKey)){
        case true:
          return true;
        case false:
          return false;
        default:
          return false;
      }
    } catch (e){
      return false;
    }
  }

  static Future<bool> saveUserOwner(bool isOwner) async {
    SharedPreferences preferences = await SharedPreferences.getInstance();
    return await preferences.setBool(_isOwnerKey, isOwner);
  }

  static Future<bool> isUserOwner() async{
    SharedPreferences preferences = await SharedPreferences.getInstance();
    return preferences.getBool(_isUserLoggedInKey);
  }

  static Future<bool> saveUUIDUser(String uid)  async {
    SharedPreferences preferences = await SharedPreferences.getInstance();
    return await preferences.setString(_uuidUserKey, uid);
  }

  static Future<String> getUUIDUser() async{
    SharedPreferences preferences = await SharedPreferences.getInstance();
    if(await isUserLoggedIn())
      return preferences.getString(_uuidUserKey);
    else
      return null;
  }

  static Future<bool> saveUserEmail(String email) async{
    SharedPreferences preferences = await SharedPreferences.getInstance();
    return await preferences.setString(_emailUserKey, email);
  }

  static Future<String> getUserEmailSharedPreference() async{
    SharedPreferences preferences = await SharedPreferences.getInstance();
    return preferences.getString(_emailUserKey);
  }
}