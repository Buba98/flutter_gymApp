import 'package:flutter/cupertino.dart';
import 'package:flutter_gym_app/utils/training/training_schedule.dart';

class TrainingScheduleDetailsPage extends StatefulWidget{
  final BuildContext context;
  final TrainingSchedule trainingSchedule;

  TrainingScheduleDetailsPage({this.context, this.trainingSchedule})

  @override
  State<StatefulWidget> createState() => _TrainingScheduleDetailsPageState(context: context, trainingSchedule: trainingSchedule);
}

class _TrainingScheduleDetailsPageState extends State<TrainingScheduleDetailsPage>{

  final BuildContext context;
  final TrainingSchedule trainingSchedule;

  _TrainingScheduleDetailsPageState({this.context, this.trainingSchedule});

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    throw UnimplementedError();
  }
}



