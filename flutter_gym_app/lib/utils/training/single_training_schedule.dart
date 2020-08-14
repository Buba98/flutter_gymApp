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
}