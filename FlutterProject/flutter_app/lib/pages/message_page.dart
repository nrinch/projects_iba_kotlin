import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import '../navigation_bloc.dart';

class MessagesPage extends StatelessWidget with NavigationStates {
  final Function onMenuTap;

  const MessagesPage({Key key, this.onMenuTap}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        borderRadius: BorderRadius.all(Radius.circular(40)),
        color: Colors.redAccent,
      ),
      padding: const EdgeInsets.only(left: 16, right: 16, top: 48),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            mainAxisSize: MainAxisSize.max,
            children: [
              InkWell(
                child: Icon(Icons.menu, color: Colors.white),
                onTap: onMenuTap,
              ),
              Text("Messages",
                  style: TextStyle(fontSize: 24, color: Colors.white)),
            ],

          ),
        CupertinoButton(child: Text("Press"), onPressed: onMenuTap)
        ],
      ),
    );
  }
}
