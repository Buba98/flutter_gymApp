import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_gym_app/screen/entrance_page.dart';
import 'package:flutter_gym_app/screen/training_schedule_page.dart';
import 'package:flutter_gym_app/utils/constants.dart';
import 'package:flutter_gym_app/utils/custom_material_color.dart';
import 'package:flutter_gym_app/utils/language/messages.dart';
import 'package:flutter_gym_app/utils/widgets/custom_grid_tile.dart';

class HomePage extends StatefulWidget{
  HomePage();

  @override
  State<StatefulWidget> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {

  _HomePageState();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: CustomColor().background(),
      body: Padding(
        padding: EdgeInsets.only(top: 20),
        child: GridView(
          gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
            crossAxisCount: 2,
            childAspectRatio: 0.9,
          ),
          children: <GridTile>[
            CustomGridTile().tileEntrance(
                context: context,
                text: Constants.MESSAGES.message(
                    type: MESSAGE_TYPE.ENTRANCE_CARD),
                icon: Icons.card_membership,
                destination: EntrancePage()),
            CustomGridTile().tileEntrance(
              context: context,
              text: Constants.MESSAGES.message(type: MESSAGE_TYPE.TRAINING_SCHEDULES),
              icon: Icons.fitness_center,
              destination: TrainingSchedulePage()
            ),
//            CustomGridTile().tileFunctionSelection(title: ),
          ],
        ),
      ),
    );
  }
}