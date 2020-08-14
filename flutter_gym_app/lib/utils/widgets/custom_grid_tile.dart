import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_gym_app/screen/entrance_page.dart';
import 'package:flutter_gym_app/utils/language/messages.dart';
import '../constants.dart';
import '../custom_material_color.dart';
import 'custom_alert.dart';

class CustomGridTile {

  GridTile tileFunctionSelection(
      {String title, String image, BuildContext context, StatefulWidget screen}) {
    return GridTile(
        child: GestureDetector(
          child: Padding(
              padding: EdgeInsets.all(15),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Container(
                    height: 150,
                    width: 200,
                    decoration: BoxDecoration(
                        color: Colors.white,
                        image: DecorationImage(
                          image: AssetImage(image),
                          fit: BoxFit.cover,
                        ),
                        borderRadius: BorderRadius.all(Radius.circular(20)),
                        boxShadow: [BoxShadow(
                            color: const Color(0x80000000),
                            offset: Offset(10, 10),
                            blurRadius: 15,
                            spreadRadius: -1
                        ),
                        ]
                    ),
                  ),
                  SizedBox(height: 13,),
                  Center(
                    child: Text(title, style: TextStyle(color: Colors.white,
                        fontSize: 18,
                        fontFamily: 'Rowdies'),),
                  )
                ],
              )
          ),
          onTap: () {
            Navigator.push(
                context, MaterialPageRoute(builder: (context) => screen));
          },
        )
    );
  }

  GridTile tileEntrance(
      {BuildContext context, IconData icon, String text, Widget destination}) {
    return GridTile(
        child: GestureDetector(
          child: Padding(
              padding: EdgeInsets.all(15),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Container(
                    height: 150,
                    width: 200,
                    decoration: BoxDecoration(
                      color: Colors.white,
                      borderRadius: BorderRadius.all(Radius.circular(20)),
                    ),
                    child: SizedBox(
                      height: 150,
                      width: 200,
                      child: IconButton(
                        iconSize: 100,
                        padding: new EdgeInsets.all(0.0),
                        color: Colors.white,
                        icon: Icon(icon),
                        onPressed: null,
                      ),
                    ),
                  ),

                  SizedBox(height: 13,),
                  Center(
                    child: Text(
                      text,
                      style: TextStyle(color: Colors.white,
                          fontSize: 18,
                          fontFamily: 'Rowdies'),),
                  )
                ],
              )
          ),
          onTap: () =>
              Navigator.push(context,
                  MaterialPageRoute(builder: (context) => destination)),
        )
    );
  }

  GridTile tileSchedule(
      {BuildContext context, IconData icon, String text, Widget destination}) {
    return GridTile(
        child: GestureDetector(
          child: Padding(
              padding: EdgeInsets.all(15),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Container(
                    height: 150,
                    width: 200,
                    decoration: BoxDecoration(
                      color: Colors.white,
                      borderRadius: BorderRadius.all(Radius.circular(20)),
                    ),
                    child: SizedBox(
                      height: 150,
                      width: 200,
                      child: IconButton(
                        iconSize: 100,
                        padding: new EdgeInsets.all(0.0),
                        color: Colors.white,
                        icon: Icon(icon),
                        onPressed: null,
                      ),
                    ),
                  ),

                  SizedBox(height: 13,),
                  Center(
                    child: Text(
                      text,
                      style: TextStyle(color: Colors.white,
                          fontSize: 18,
                          fontFamily: 'Rowdies'),),
                  )
                ],
              )
          ),
          onTap: () =>
              Navigator.push(context,
                  MaterialPageRoute(builder: (context) => destination)),
        )
    );
  }
}