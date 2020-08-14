import 'package:flutter/cupertino.dart';
import 'package:flutter_gym_app/utils/training/single_training_schedule.dart';

import 'exercise.dart';

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
        email = json['email'];

  Map<String, dynamic> toJson() =>
      {
        'name': name,
        'email': email,
      };
}