import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_gym_app/utils/constants.dart';
import 'package:flutter_gym_app/utils/custom_material_color.dart';
import 'package:flutter_gym_app/utils/language/messages.dart';
import 'package:flutter_gym_app/utils/training/training_schedule.dart';
import 'package:flutter_gym_app/utils/widgets/custom_alert.dart';
import 'package:flutter_gym_app/utils/widgets/custom_drawer.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter_gym_app/utils/widgets/custom_grid_tile.dart';
import 'package:rflutter_alert/rflutter_alert.dart';
import 'package:http/http.dart' as http;

import 'entrance_page.dart';


class TrainingSchedulePage extends StatefulWidget{
  @override
  _TrainingSchedulePageState createState() => _TrainingSchedulePageState();
}

class _TrainingSchedulePageState extends State<TrainingSchedulePage> {


  @override
  Widget build(BuildContext context) {
//    if (Constants.PREFERENCES.get(Constants.USER_OR_OWNER) == 'user') {
//      return _userSchedule();
//    } else {
//      return _ownerSchedule();
//    }
    return _userSchedule();
  }

  Scaffold _ownerSchedule() {
    return Scaffold(
      backgroundColor: CustomColor().background(),
      appBar: AppBar(
        backgroundColor: CustomColor().foreground(),
        title: Text(
            Constants.MESSAGES.message(type: MESSAGE_TYPE.ENTRANCE_CARD)),
      ),
      drawer: CustomDrawer().coloredDrawer(context),
      body: Center(
        child: SingleChildScrollView(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Padding(
                padding: EdgeInsets.only(left: 30, right: 30),
                child: Column(
                  children: [
                  ],
                ),
              )
            ],
          ),
        ),
      ),
    );
  }

  List<GridTile> _gridTileList = [];

  Scaffold _userSchedule() {
    _getGridTileList();
    return Scaffold(
      backgroundColor: CustomColor().background(),
      appBar: AppBar(
        backgroundColor: CustomColor().foreground(),
        title: Text(
            Constants.MESSAGES.message(type: MESSAGE_TYPE.ENTRANCE_CARD)),
      ),
      drawer: CustomDrawer().mainDrawer(context),
      body: Padding(
        padding: EdgeInsets.only(top: 20),
        child: GridView(
          gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
            crossAxisCount: 2,
            childAspectRatio: 0.9,
          ),
          children: <GridTile>[
            CustomGridTile().tileSchedule(
                context: context,
                text: Constants.MESSAGES.message(
                    type: MESSAGE_TYPE.ENTRANCE_CARD),
                icon: Icons.card_membership,
                destination: EntrancePage()),
            CustomGridTile().tileEntrance(
                context: context,
                text: Constants.MESSAGES.message(
                    type: MESSAGE_TYPE.TRAINING_SCHEDULES),
                icon: Icons.fitness_center,
                destination: TrainingSchedulePage()
            ),
//            CustomGridTile().tileFunctionSelection(title: ),
          ],
        ),
      ),
    );
  }

  Future<void> _getGridTile() async {
    http.Response response = await http.post(
        Constants.SERVER_ADDRESS + '/api/getTrainingSchedule', body: jsonEncode(
        <String, String>{'uid': Constants.PREFERENCES.get(Constants.UID)}));
    if (response.statusCode == 200) {
      TrainingSchedule trainingSchedule = TrainingSchedule.fromJson(jsonDecode(response.body)['trainingSchedule']);
      setState(() {

      });

    } else {
      CustomAlert().standardAlert(context: context,
          alertType: AlertType.error,
          title: Constants.MESSAGES.message(
              type: MESSAGE_TYPE.ERROR),
          text: Constants.MESSAGES.message(
              type: MESSAGE_TYPE.ERROR_LOGIN));
    }
  }
}