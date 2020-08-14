import 'package:flutter/material.dart';
import 'package:flutter_gym_app/screen/entrance_page.dart';
import 'package:flutter_gym_app/screen/home_page.dart';
import 'package:flutter_gym_app/screen/training_schedule_page.dart';
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
              ),
              child: GestureDetector(
                onTap: () =>
                    Navigator.push(context,
                        MaterialPageRoute(
                            builder: (context) => HomePage())),
                child: Container(
                  decoration: BoxDecoration(
                    image: DecorationImage(
                        image: AssetImage(Constants.GYM_LOGO)),
                  ),
                ),),
//                Image.asset(Constants.GYM_LOGO),

            ),
            _drawerTile(text: Constants.MESSAGES.message(
                type: MESSAGE_TYPE.ENTRANCE_CARD),
                context: context,
                destination: EntrancePage(),
                icon: Icons.card_membership),
            _drawerTile(text: Constants.MESSAGES.message(
                type: MESSAGE_TYPE.TRAINING_SCHEDULES),
                context: context,
                destination: TrainingSchedulePage(),
                icon: Icons.fitness_center)
          ],
        )
    );
  }

  ListTile _drawerTile(
      {String text, BuildContext context, Widget destination, IconData icon}) {
    return ListTile(
        title: Text(
          text,
          style: TextStyle(color: Colors.white),),
        leading: Icon(icon, color: Colors.white,),
        onTap: () {
          debugPrint(context.toString());
          if (context.widget.toString() == destination.toString())
            Navigator.pop(context);
          else
            Navigator.push(context,
                MaterialPageRoute(
                    builder: (context) => destination));
        }
    );
  }
}