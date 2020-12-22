import 'package:flutter/material.dart';

import '../navigation_bloc.dart';

class MyCardsPage extends StatelessWidget with NavigationStates {
  final Function onMenuTap;

  const MyCardsPage({Key key, this.onMenuTap}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        borderRadius: BorderRadius.all(Radius.circular(40)),
        color: Colors.grey,
      ),
      child: SingleChildScrollView(
        scrollDirection: Axis.vertical,
        physics: ClampingScrollPhysics(),
        child: Container(
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
                  Text("My Cards",
                      style: TextStyle(fontSize: 24, color: Colors.white)),
                ],
              ),
              SizedBox(height: 50),
              Container(
                height: 200,
                child: PageView(
                  controller: PageController(viewportFraction: 0.8),
                  scrollDirection: Axis.horizontal,
                  pageSnapping: true,
                  children: <Widget>[
                    CardWidget(Card(
                        "CyberHalva",
                        "2077 2077 2077 2077",
                        "Johny Silverhand",
                        "02/77",
                        [Colors.yellowAccent, Colors.deepPurple])),
                    CardWidget(Card(
                        "Witcher",
                        "2007 2012 2015 20??",
                        "Geralt of Rivia",
                        "02/21",
                        [Colors.black45, Colors.red]))
                  ],
                ),
              ),
              SizedBox(height: 20),
              Text(
                "Transactions",
                style: TextStyle(color: Colors.white, fontSize: 20),
              ),
              ListView.separated(
                shrinkWrap: true,
                itemBuilder: (context, index) {
                  return ListTile(
                    title: Text("Macbook"),
                    subtitle: Text("Apple"),
                    trailing: Text("-2900"),
                  );
                },
                separatorBuilder: (context, index) {
                  return Divider(height: 16);
                },
                itemCount: 10,
              ),
              SizedBox(height: 20),
            ],
          ),
        ),
      ),
    );
  }
}

class CardWidget extends StatelessWidget {
  final Card _card;

  CardWidget(this._card);

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: const EdgeInsets.symmetric(horizontal: 8),
      width: 100,
      decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(12),
          gradient: LinearGradient(
              begin: Alignment.topRight,
              end: Alignment.bottomLeft,
              colors: _card.cardColor)),
      child: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              _card.cardName,
              style: TextStyle(fontSize: 28, color: Colors.white),
            ),
            SizedBox(
              height: 40,
            ),
            Center(
                child: Text(
              _card.cardNumber,
              style: TextStyle(fontSize: 24, color: Colors.white),
            )),
            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 16.0),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Text(
                    _card.cardOwner,
                    style: TextStyle(color: Colors.white),
                  ),
                  Text(
                    _card.cardEndDate,
                    style: TextStyle(color: Colors.white),
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}

class Card {
  String cardName;
  String cardNumber;
  String cardOwner;
  String cardEndDate;
  List<Color> cardColor;

  Card(this.cardName, this.cardNumber, this.cardOwner, this.cardEndDate,
      this.cardColor);
}
