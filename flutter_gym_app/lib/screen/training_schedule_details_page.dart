import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_gym_app/utils/constants.dart';
import 'package:flutter_gym_app/utils/custom_material_color.dart';
import 'package:flutter_gym_app/utils/language/messages.dart';
import 'package:flutter_gym_app/utils/training/exercise.dart';
import 'package:flutter_gym_app/utils/training/single_training_schedule.dart';
import 'package:flutter_gym_app/utils/widgets/custom_drawer.dart';
import 'package:url_launcher/url_launcher.dart';

class TrainingScheduleDetailsPage extends StatefulWidget{
  final BuildContext context;
  final SingleTrainingSchedule singleTrainingSchedule;

  TrainingScheduleDetailsPage({this.context, this.singleTrainingSchedule})

;

  @override
  State<StatefulWidget> createState() => _TrainingScheduleDetailsPageState(context: context, singleTrainingSchedule: singleTrainingSchedule);
}

class _TrainingScheduleDetailsPageState extends State<TrainingScheduleDetailsPage> {

  final BuildContext context;
  final SingleTrainingSchedule singleTrainingSchedule;

  _TrainingScheduleDetailsPageState(
      {this.context, this.singleTrainingSchedule});

  @override
  Widget build(BuildContext context) {
    String appBarTitle;
    if (singleTrainingSchedule.name != null) {
      appBarTitle = singleTrainingSchedule.name;
    }

    List<TableRow> tableRowList = [];

    tableRowList.add(TableRow(
        children: [
          TableCell(
            child: Center(
              child: Text(
                  Constants.MESSAGES.message(
                      type: MESSAGE_TYPE.NUMBER_ABBREVIATION)
              ),
            ),
          ),
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
          TableCell(
            child: Center(child: Text(
                Constants.MESSAGES.message(
                    type: MESSAGE_TYPE.VIDEO))),
          ),
        ]
    ));

    int count = 0;

    while (count < singleTrainingSchedule.exercises.length) {
      Exercise exercise = singleTrainingSchedule.exercises[count];
      RaisedButton raisedButton;

      if (exercise.urlVideo != null) {
        raisedButton = RaisedButton(
          elevation: 5.0,
          onPressed: () => _launchURL(exercise.urlVideo),
          padding: EdgeInsets.all(1.0),
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(5.0),
          ),
          color: CustomColor().foreground(),
          child:
          Center(
              child: Icon(Icons.play_arrow)
          ),
        );
      } else {
        raisedButton = RaisedButton(
          elevation: 5.0,
          onPressed: () => _launchURL(exercise.name.replaceAll(' ', '+')),
          padding: EdgeInsets.all(15.0),
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(30.0),
          ),
          color: CustomColor().foreground(),
          child:
          Center(
              child: Icon(Icons.search)
          ),
        );
      }

      tableRowList.add(TableRow(
          children: [
            TableCell(
              child: Center(
                  child: Text(count.toString())
              ),
            ),
            TableCell(
              child: Center(child: Text(exercise.name)),
            ),
            TableCell(
              child: Center(child: Text(exercise.sets.toString())),
            ),
            TableCell(
              child: Center(child: Text(exercise.reps.toString())),
            ),
            TableCell(
              child: Center(child: Text(exercise.secondForRep.toString())),
            ),
            TableCell(
              child: Center(child: raisedButton,),
            ),
          ]
      ));
      count++;
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
                                    type: MESSAGE_TYPE.SECOND_FOR_REP))),
                          ),
                          TableCell(
                            child: Center(child: Text(
                                Constants.MESSAGES.message(
                                    type: MESSAGE_TYPE.VIDEO))),
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

  _launchURL(String url) async {
    if (await canLaunch(url)) {
      await launch(url);
    } else {
      throw 'Could not launch $url';
    }
  }
}