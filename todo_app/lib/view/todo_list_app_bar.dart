import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import '../constants/constants.dart';

class TodoListAppBar extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return AppBar(
      backgroundColor: Colors.white,
      foregroundColor: Colors.black,
      elevation: 0,
      title: ListTile(
        leading: CircleAvatar(
          backgroundColor: Colors.amber.shade200,
          radius: AppConstants.paddingMedium,
          child: Image.asset('lib/assets/images/todolist.png'),
        ),
        title: Text(
          "Your Todo List",
          style: Theme.of(context).textTheme.headline6,
        ),
        subtitle: Text(
          "Make Your Plans",
          style: Theme.of(context).textTheme.bodyText2,
        ),
      ),
      actions: [
        Padding(
          padding: const EdgeInsets.symmetric(horizontal: AppConstants.paddingSmall),
          child: Row(
            children: [
              IconButton(
                onPressed: () {},
                icon: const Icon(CupertinoIcons.calendar),
              ),
              IconButton(
                onPressed: () {},
                icon: const Icon(CupertinoIcons.bell),
              ),
            ],
          ),
        )
      ],
    );
  }
}
