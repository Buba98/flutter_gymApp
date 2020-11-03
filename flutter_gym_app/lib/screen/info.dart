import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_gym_app/constants.dart';
import 'package:fluttericon/zocial_icons.dart';
import 'package:url_launcher/url_launcher.dart';

class Info extends StatefulWidget {
  //final Function toggleView;

  Info();

  //SignIn(this.toggleView);
  @override
  _InfoState createState() => _InfoState();
}

class _InfoState extends State<Info> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      drawer: mainDrawer(context),
      appBar: AppBar(title: Text("Info")),
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
                      Row(
                        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                        children: [
                          Text(
                            "Posizione palestra",
                            style: TextStyle(
                                color: Colors.white,
                                letterSpacing: 1.5,
                                fontSize: 15.0,
                                fontWeight: FontWeight.bold,
                                fontFamily: 'OpenSans'
                            ),
                          ),
                          SizedBox.fromSize(
                            size: Size(56, 56), // button width and height
                            child: ClipOval(
                              child: Material(
                                color: foregroundColor, // button color
                                child: InkWell(
                                  splashColor: backgroundColor, // splash color
                                  onTap: () => _launchURL("https://goo.gl/maps/CiDyZ59uGwjzqEMX6"),
                                  child: Column(
                                    mainAxisAlignment: MainAxisAlignment.center,
                                    children: <Widget>[
                                      Icon(Icons.location_on_outlined), // icon
                                    ],
                                  ),
                                ),
                              ),
                            ),
                          )
                        ],
                      ),
                      SizedBox(height: 20,),
                      Row(
                        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                        children: [
                          Text(
                            "Social",
                            style: TextStyle(
                                color: Colors.white,
                                letterSpacing: 1.5,
                                fontSize: 15.0,
                                fontWeight: FontWeight.bold,
                                fontFamily: 'OpenSans'
                            ),
                          ),
                          SizedBox.fromSize(
                            size: Size(56, 56), // button width and height
                            child: ClipOval(
                              child: Material(
                                color: foregroundColor, // button color
                                child: InkWell(
                                  splashColor: backgroundColor, // splash color
                                  onTap: () => _launchURL("https://www.instagram.com/bodypower04/"), // button pressed
                                  child: Column(
                                    mainAxisAlignment: MainAxisAlignment.center,
                                    children: <Widget>[
                                      Icon(Zocial.instagram), // icon
                                    ],
                                  ),
                                ),
                              ),
                            ),
                          ),
                          SizedBox.fromSize(
                            size: Size(56, 56), // button width and height
                            child: ClipOval(
                              child: Material(
                                color: foregroundColor, // button color
                                child: InkWell(
                                  splashColor: backgroundColor, // splash color
                                  onTap: () => _launchURL("https://www.facebook.com/palestrabodypowertrani"), // button pressed
                                  child: Column(
                                    mainAxisAlignment: MainAxisAlignment.center,
                                    children: <Widget>[
                                      Icon(Zocial.facebook), // icon
                                    ],
                                  ),
                                ),
                              ),
                            ),
                          )
                        ],
                      ),
                      SizedBox(height: 20,),
                      Row(
                        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                        children: [
                          Text(
                            "Developer",
                            style: TextStyle(
                                color: Colors.white,
                                letterSpacing: 1.5,
                                fontSize: 15.0,
                                fontWeight: FontWeight.bold,
                                fontFamily: 'OpenSans'
                            ),
                          ),
                          SizedBox.fromSize(
                            size: Size(56, 56), // button width and height
                            child: ClipOval(
                              child: Material(
                                color: foregroundColor, // button color
                                child: InkWell(
                                  splashColor: backgroundColor, // splash color
                                  onTap: () => _launchURL("mailto:grecovincenzo98@gmail.com"),
                                  child: Column(
                                    mainAxisAlignment: MainAxisAlignment.center,
                                    children: <Widget>[
                                      Icon(Zocial.gmail), // icon
                                    ],
                                  ),
                                ),
                              ),
                            ),
                          ),
                          SizedBox.fromSize(
                            size: Size(56, 56), // button width and height
                            child: ClipOval(
                              child: Material(
                                color: foregroundColor, // button color
                                child: InkWell(
                                  splashColor: backgroundColor, // splash color
                                  onTap: () => _launchURL("https://www.instagram.com/vincent_greco_98/"),
                                  child: Column(
                                    mainAxisAlignment: MainAxisAlignment.center,
                                    children: <Widget>[
                                      Icon(Zocial.instagram), // icon
                                    ],
                                  ),
                                ),
                              ),
                            ),
                          ),
                        ],
                      ),
                    ],
                  ),
                )
              ],
            ),
          )
      ),
    );
  }

  _launchURL(String url) async{
    if (await canLaunch(url))
      await launch(url);
    else
      throw "Could not launch $url";
  }
}