import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:gap/gap.dart';
import 'package:intl/intl.dart';
import 'package:todo_app/model/todo_model.dart';
import 'package:todo_app/provider/date_time_provider.dart';
import 'package:todo_app/provider/radio_provider.dart';
import 'package:todo_app/provider/service_provider.dart';
import 'package:todo_app/provider/task_title_provider.dart';
import 'package:todo_app/widget/date_time_widget.dart';
import 'package:todo_app/widget/radio_widget.dart';
import 'package:todo_app/widget/textfield_widget.dart';


import '../constants/app_style.dart';

class AddNewTaskMethod extends ConsumerWidget {
  AddNewTaskMethod({
    super.key,
  });

  final titleController = TextEditingController();
  final descriptionController = TextEditingController();

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final dateProv = ref.watch(dateProvider);
    return Container(
      padding: const EdgeInsets.all(30),
      height: MediaQuery
          .of(context)
          .size
          .height * 0.70,
      decoration: BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.circular(16),
      ),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          const SizedBox(
            width: double.infinity,
            child: Text(
              "New Task Todo",
              textAlign: TextAlign.center,
              style: TextStyle(
                  fontSize: 28,
                  fontWeight: FontWeight.bold,
                  color: Colors.black),
            ),
          ),
          Divider(
            thickness: 1.2,
            color: Colors.grey.shade200,
          ),
          const Gap(12),
          const Text(
            "Title Task",
            style: AppStyle.headingOne,
          ),
          const Gap(6),
          TextFieldWidget(hintText: "Add Task Name", maxLine: 1, txtController: titleController,),
          const Gap(12),
          const Text("Description", style: AppStyle.headingOne),
          const Gap(6),
          TextFieldWidget(hintText: "Add Description", maxLine: 5, txtController: descriptionController,),
          const Gap(12),
          const Text("Category", style: AppStyle.headingOne),
          Row(
            children: [
              Expanded(child: RadioWidget(titleRadio: "LRN",
                categColor: Colors.green,
                valueInput: 1,
                onChangeValue: () =>
                    ref.read(radioProvider.notifier).update((state) => 1),)
              ),
              Expanded(child: RadioWidget(titleRadio: "WRK",
                  categColor: Colors.blue.shade700,
                  valueInput: 2,
                  onChangeValue: () =>
                      ref.read(radioProvider.notifier).update((state) => 2))
              ),
              Expanded(child: RadioWidget(titleRadio: "GEN",
                  categColor: Colors.amberAccent.shade700,
                  valueInput: 3,
                  onChangeValue: () =>
                      ref.read(radioProvider.notifier).update((state) => 3))
              ),
            ],
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              DateTimeWidget(titleText: 'Date',
                  valueText: dateProv.toString(),
                  iconSection: CupertinoIcons.calendar,
                onTap: () async {
                final DateTime? getValue = await showDatePicker(context: context, initialDate: DateTime.now(),
                    firstDate: DateTime(2021),lastDate: DateTime(2030));
                if(getValue != null) {
                  final format = DateFormat.yMd();
                  ref.read(dateProvider.notifier).update((state) => format.format(getValue));
                }
                },
          ),
              const Gap(22),
              DateTimeWidget(titleText: 'Time',
                valueText: ref.watch(timeProvider),
                iconSection: CupertinoIcons.clock,
                onTap: () async{
                final getTime = await showTimePicker(context: context, initialTime: TimeOfDay.now());

                if(getTime != null) {

                  ref.read(timeProvider.notifier).update((state) => getTime.format(context));
                }

                              },
              ),
            ],
          ),
          const Gap(20),
          Row(children: [
            Expanded(child: ElevatedButton(
              style: ElevatedButton.styleFrom(
                backgroundColor: Colors.white,
                foregroundColor: Colors.blue.shade800,
                elevation: 0,
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(8),
                ),
                side: BorderSide(color: Colors.blue.shade800),
                padding: const EdgeInsets.symmetric(vertical: 14),
              ),
              onPressed: () => Navigator.pop(context),
              child: const Text("Cancel"),
            ),
            ),
            const Gap(20),
            Expanded(child: ElevatedButton(
              style: ElevatedButton.styleFrom(
                backgroundColor: Colors.blue.shade800,
                foregroundColor: Colors.white,
                elevation: 0,
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(8),
                ),
                padding: const EdgeInsets.symmetric(vertical: 12),
              ),
              onPressed: () {

                final getRadioValue = ref.read(radioProvider);


                String category = '';

                switch(getRadioValue){
                  case 1:
                    category = 'Learning';
                    break;
                  case 2:
                    category = 'Working';
                    break;
                  case 3:
                    category = 'General';
                    break;
                }

                ref.read(serviceProvider).addNewTask(TodoModel(titleTask: ref.read(titleController.text as ProviderListenable<String>),
                    description: ref.read(descriptionController.text as ProviderListenable<String>),
                    category: category,
                    dateTask: ref.read(dateProvider),
                    timeTask: ref.read(timeProvider),
                  isDone: false,
                ));

                print('Data is saving');

                titleController.clear();
                descriptionController.clear();
                ref.read(radioProvider.notifier).update((state) => 0);
                Navigator.pop(context);
              },
              child: const Text("Create"),
            ),
            ),
          ],
          )
        ],
      ),
    );
  }

}







