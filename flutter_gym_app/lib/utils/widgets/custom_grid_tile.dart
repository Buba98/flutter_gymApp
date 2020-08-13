import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import '../constants.dart';
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

  GridTile tileEntrance({BuildContext context}) {
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
                          image: AssetImage(Constants.GYM_LOGO),
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
                    child: Text(
                      Constants.GYM_NAME, style: TextStyle(color: Colors.white,
                        fontSize: 18,
                        fontFamily: 'Rowdies'),),
                  )
                ],
              )
          ),
          onTap: () {
            CustomAlert().entranceQr(context: context).show();
          },
        )
    );
  }
}