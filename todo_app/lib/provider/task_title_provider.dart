import 'package:flutter_riverpod/flutter_riverpod.dart';

final titleProvider = StateProvider<String>((ref) {
  return 'Title Task';
});

final descriptionProvider = StateProvider<String>((ref) {
  return 'Description';
});