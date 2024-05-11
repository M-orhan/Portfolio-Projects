import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:gap/gap.dart';
import 'package:todo_app/provider/service_provider.dart';
import '../common/show_model.dart';
import '../widget/card_todo_widget.dart';

class HomePage extends ConsumerWidget {
  const HomePage({super.key});



  @override
  Widget build(BuildContext context,WidgetRef ref) {
    final todoData = ref.watch(fetchStreamProvider);

    return Scaffold(
      backgroundColor: Colors.grey.shade200,
      appBar: AppBar(
        backgroundColor: Colors.white,
        foregroundColor: Colors.black,
        elevation: 0,
        title: ListTile(
          leading: CircleAvatar(
            backgroundColor: Colors.amber.shade200,
            radius: 30,
            child: Image.asset('lib/assets/images/todolist.png'),
          ),
          title: Text(
            "Your Todo List",
            style: TextStyle(fontSize: 12, color: Colors.grey.shade300),
          ),
          subtitle: const Text(
            "Make Your Plans",
            style: TextStyle(
                fontSize: 14, color: Colors.black, fontWeight: FontWeight.bold),
          ),
        ),
        actions: [
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 20),
            child: Row(
              children: [
                IconButton(
                    onPressed: () {},
                    icon: const Icon(CupertinoIcons.calendar)),
                IconButton(
                    onPressed: () {}, icon: const Icon(CupertinoIcons.bell)),
              ],
            ),
          )
        ],
      ),
      body: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 30),
          child: Column(
            children: [
              const Gap(20),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  const Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        "Today's Task",
                        style: TextStyle(
                            fontSize: 20,
                            fontWeight: FontWeight.bold,
                            color: Colors.black),
                      ),
                      Text(
                        "Wednesday, 11 May",
                        style: TextStyle(color: Colors.grey),
                      ),
                    ],
                  ),
                  ElevatedButton(
                    style: ElevatedButton.styleFrom(
                      backgroundColor: const Color(0xFFD5E8FA),
                      foregroundColor: Colors.blue.shade800,
                      elevation: 0,
                      shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(8)),
                    ),
                    onPressed: () => showModalBottomSheet(
                        isScrollControlled: true,
                        shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(16)),
                        context: context,
                        builder: (context) => AddNewTaskMethod()),
                    child: const Text("+ New Task"),
                  ),
                  //Card list task
                ],
              ),
              const Gap(20),
              ListView.builder(
                  itemCount: todoData.value?.length?? 0,
                  shrinkWrap: true,
                  itemBuilder: (context, index) =>
                  CardTodoListWidget(getIndex: index)),
            ],
          ),
        ),
      ),
    );
  }
}