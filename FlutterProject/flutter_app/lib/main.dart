import 'package:flutter/material.dart';
import 'package:flutter_app/ui/menu_dashboard_layout.dart';

void main() {
  runApp(MyApp());
}
class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'Flutter Demo',
        debugShowCheckedModeBanner: true,
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: MenuDashboardLayout());
  }
}
