import 'package:flutter/cupertino.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../provider/service_provider.dart';
import '../widget/card_todo_widget.dart';

class TodoList extends ConsumerWidget {
  @override
  Widget build(BuildContext context,WidgetRef ref) {
    final todoData = ref.watch(fetchStreamProvider);
    return ListView.builder(
      itemCount: todoData.value?.length?? 0,
      shrinkWrap: true,
      itemBuilder: (context, index) => CardTodoListWidget(getIndex: index),
    );
  }
}