import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_gym_app/utils/constants.dart';
import 'package:flutter_gym_app/utils/custom_material_color.dart';
import 'package:flutter_gym_app/utils/language/messages.dart';
import 'package:flutter_gym_app/utils/training/training_schedule.dart';
import 'package:flutter_gym_app/utils/widgets/custom_drawer.dart';

class TrainingScheduleDetailsPage extends StatefulWidget{
  final BuildContext context;
  final TrainingSchedule trainingSchedule;

  TrainingScheduleDetailsPage({this.context, this.trainingSchedule})

  @override
  State<StatefulWidget> createState() => _TrainingScheduleDetailsPageState(context: context, trainingSchedule: trainingSchedule);
}

class _TrainingScheduleDetailsPageState extends State<TrainingScheduleDetailsPage> {

  final BuildContext context;
  final TrainingSchedule trainingSchedule;

  _TrainingScheduleDetailsPageState({this.context, this.trainingSchedule});

  @override
  Widget build(BuildContext context) {
    String appBarTitle;
    if (trainingSchedule.name != null) {
      appBarTitle = trainingSchedule.name;
    } else {
      appBarTitle = trainingSchedule.expirationDate.toString();
    }

    return Scaffold(
      backgroundColor: CustomColor().background(),
      appBar: AppBar(
        backgroundColor: CustomColor().foreground(),
        title: Text(appBarTitle),
      ),
      drawer: CustomDrawer().coloredDrawer(context),
      body: Center(
        child: SingleChildScrollView(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Padding(
                padding: EdgeInsets.only(left: 30, right: 30),
                child: Table(
                  border: TableBorder.all(
                    color: Colors.white,
                    width: 0.5,
                  ),
                  children: [
                    TableRow(
                        children: [
                          TableCell(
                            child: Center(child: Text(
                                Constants.MESSAGES.message(
                                    type: MESSAGE_TYPE.EXERCISE))),
                          ),
                          TableCell(
                            child: Center(child: Text(
                                Constants.MESSAGES.message(
                                    type: MESSAGE_TYPE.SETS))),
                          ),
                          TableCell(
                            child: Center(child: Text(
                                Constants.MESSAGES.message(
                                    type: MESSAGE_TYPE.REPS))),
                          ),
                          TableCell(
                            child: Center(child: Text(
                                Constants.MESSAGES.message(
                                    type: MESSAGE_TYPE.SETS))),
                          ),
                        ]
                    )
                  ],
                ),
              )
            ],
          ),
        ),
      ),
    );
  }
}


