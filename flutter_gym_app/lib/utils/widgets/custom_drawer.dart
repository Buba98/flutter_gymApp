import 'package:flutter/material.dart';
import 'package:flutter_gym_app/screen/entrance_page.dart';
import 'package:flutter_gym_app/utils/constants.dart';
import 'package:flutter_gym_app/utils/custom_material_color.dart';
import 'package:flutter_gym_app/utils/language/messages.dart';

class CustomDrawer {
  Drawer mainDrawer(BuildContext context) {
    return Drawer(
        child: ListView(
          padding: EdgeInsets.zero,
          children: <Widget>[
            DrawerHeader(
              decoration: BoxDecoration(
                color: CustomColor().foreground(),

//                  image: DecorationImage(
//                      image: AssetImage(Constants.GYM_LOGO),
//                      fit: BoxFit.fitHeight
//                  )
              ),
              child: Padding(
                padding: EdgeInsets.only(top: 2, bottom: 2),
                child: Image.asset(Constants.GYM_LOGO),
              ),
            ),
            ListTile(
                title: Text(
                    Constants.MESSAGES.message(
                        type: MESSAGE_TYPE.ENTRANCE_CARD)),
                leading: Icon(Icons.card_membership),
                onTap: () {
                  debugPrint(context.toString());
                  if(context.widget.toString() == EntrancePage().toString())
                    Navigator.pop(context);
                  else
                    Navigator.push(context,
                      MaterialPageRoute(builder: (context) => EntrancePage()));
                }


            ),
            ListTile(
              title: Text('Scheda allenamento'),
              onTap: () {
//                Navigator.push(context, MaterialPageRoute(builder: (context) => TrainingCardScreen()));
              },
            ),
            ListTile(
              title: Text('Scheda alimentazione'),
              onTap: () {
//                Navigator.push(context, MaterialPageRoute(builder: (context) => SupplyCardScreen()));
              },
            ),
            ListTile(

            )
          ],
        )
    );
  }
}