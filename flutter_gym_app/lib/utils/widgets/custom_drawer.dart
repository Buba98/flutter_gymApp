import 'package:flutter/material.dart';
import 'package:flutter_gym_app/screen/entrance_page.dart';
import 'package:flutter_gym_app/utils/constants.dart';
import 'package:flutter_gym_app/utils/custom_material_color.dart';
import 'package:flutter_gym_app/utils/language/messages.dart';

class CustomDrawer {

  Theme coloredDrawer(BuildContext context) {
    return Theme(
      data: Theme.of(context).copyWith(
        canvasColor: CustomColor().background(),
      ),
      child: mainDrawer(context),
    );
  }

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
                child:
                Image.asset(Constants.GYM_LOGO)
            ),
            ListTile(
                title: Text(
                  Constants.MESSAGES.message(
                      type: MESSAGE_TYPE.ENTRANCE_CARD),
                  style: TextStyle(color: Colors.white),),
                leading: Icon(Icons.card_membership, color: Colors.white,),
                onTap: () {
                  debugPrint(context.toString());
                  if (context.widget.toString() == EntrancePage().toString())
                    Navigator.pop(context);
                  else
                    Navigator.push(context,
                        MaterialPageRoute(
                            builder: (context) => EntrancePage()));
                }
            ),
          ],
        )
    );
  }
}