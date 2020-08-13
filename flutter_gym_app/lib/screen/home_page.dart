import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_gym_app/utils/custom_material_color.dart';
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
            CustomGridTile().tileEntrance(context: context),
//            CustomGridTile().tileFunctionSelection(title: ),
          ],
        ),
      ),
    );
  }
}