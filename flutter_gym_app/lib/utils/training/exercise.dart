import 'dart:convert';

import 'package:flutter/cupertino.dart';

class Exercise {
  Exercise({
    @required this.name,
    @required this.reps,
    @required this.sets,
    this.secondForRep,
    this.weight,
    this.urlVideo,
    this.gifOrImage
  });

  String name;
  int reps;
  int sets;
  int secondForRep;
  int weight;
  String urlVideo;
  String gifOrImage;

  Exercise.fromJson(Map<String, dynamic> json)
      : name = json['name'],
        reps = json['reps'],
        sets = json['sets'],
        secondForRep = json['secondForRep'],
        weight = json['weight'],
        urlVideo = json['urlVideo'],
        gifOrImage = json['gifOrImage'];

  Map<String, dynamic> toJson() =>
      {
        'name': name,
        'reps': reps,
        'sets': sets,
        'secondForRep': secondForRep,
        'weight': weight,
        'urlVideo': urlVideo,
        'gifOrImage': gifOrImage
      };
}