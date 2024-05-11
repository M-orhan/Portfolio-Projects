import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:gap/gap.dart';

import '../common/show_model.dart';
import '../constants/constants.dart';

class TodoListBody extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return LayoutBuilder(
      builder: (context, constraints) {
        return SingleChildScrollView(
          child: Padding(
            padding: const EdgeInsets.symmetric(horizontal: AppConstants.paddingMedium),
            child: Column(
              children: [
                Gap(AppConstants.paddingMedium),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          "Today's Task",
                          style: Theme.of(context).textTheme.headline6,
                        ),
                        Text(
                          "Wednesday, 11 May",
                          style: Theme.of(context).textTheme.bodyText2,
                        ),
                      ],
                    ),
                    ElevatedButton(
                      style: ElevatedButton.styleFrom(
                        backgroundColor: const Color(0xFFD5E8FA),
                        foregroundColor: Colors.blue.shade800,
                        elevation: 0,
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(AppConstants.borderRadius),
                        ),
                      ),
                      onPressed: () => showModalBottomSheet(
                        isScrollControlled: true,
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(AppConstants.borderRadius),
                        ),
                        context: context,
                        builder: (context) => AddNewTaskMethod(),
                      ),
                      child: const Text("+ New Task"),
                    ),
                  ],
                ),
                Gap(AppConstants.paddingMedium),
                // Card list task
                TodoListBody(),
              ],
            ),
          ),
        );
      },
    );
  }
}