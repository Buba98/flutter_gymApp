import 'package:flutter/cupertino.dart';
import 'package:flutter_gym_app/utils/training/single_training_schedule.dart';

class TrainingSchedule {
  TrainingSchedule({
    this.name,
    this.description,
    @required this.listTrainingSchedules,
    @required this.expirationDate,
    @required this.startingDate});

  List<SingleTrainingSchedule> listTrainingSchedules;
  String name;
  String description;
  DateTime expirationDate;
  DateTime startingDate;

  TrainingSchedule.fromJson(Map<String, dynamic> json)
      : name = json['name'],
        description = json['description'],
        expirationDate = DateTime.parse(json['expirationDate']),
        startingDate = DateTime.parse(json['startingDate']),
        listTrainingSchedules = json['listTrainingSchedules'];

//  Map<String, dynamic> toJson() =>
//      {
//        'name': name,
//        'description': description,
//        'expirationDate': expirationDate.toString(),
//        'startingDate': startingDate.toString(),
//        'listTrainingSchedules': listTrainingSchedules.
//      };
}