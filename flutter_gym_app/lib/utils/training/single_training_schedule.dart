import 'dart:convert';

import 'package:flutter/cupertino.dart';

import 'exercise.dart';

class SingleTrainingSchedule {
  SingleTrainingSchedule({
    this.name,
    this.description,
    @required this.exercises,
  });

  List<Exercise> exercises;
  String name;
  String description;

  SingleTrainingSchedule.fromJson(Map<String, dynamic> json)
      : name = json['name'],
        description = json['description'],
        exercises = _listExercisesFromJson(json['exercises']);

  Map<String, dynamic> toJson() =>
      {
        'name': name,
        'description': description,
        'exercises': exercises
      };

  static List<Exercise> _listExercisesFromJson(String string) {
    final jsonResponse = json.decode(string);
    List<Exercise> listToReturn = [];

    for (var i in jsonResponse)
      listToReturn.add(Exercise.fromJson(i));

    return listToReturn;
  }
}