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
}