// File generated by FlutterFire CLI.
// ignore_for_file: type=lint
import 'package:firebase_core/firebase_core.dart' show FirebaseOptions;
import 'package:flutter/foundation.dart'
    show defaultTargetPlatform, kIsWeb, TargetPlatform;

/// Default [FirebaseOptions] for use with your Firebase apps.
///
/// Example:
/// ```dart
/// import 'firebase_options.dart';
/// // ...
/// await Firebase.initializeApp(
///   options: DefaultFirebaseOptions.currentPlatform,
/// );
/// ```
class DefaultFirebaseOptions {
  static FirebaseOptions get currentPlatform {
    if (kIsWeb) {
      return web;
    }
    switch (defaultTargetPlatform) {
      case TargetPlatform.android:
        return android;
      case TargetPlatform.iOS:
        return ios;
      case TargetPlatform.macOS:
        return macos;
      case TargetPlatform.windows:
        return windows;
      case TargetPlatform.linux:
        throw UnsupportedError(
          'DefaultFirebaseOptions have not been configured for linux - '
          'you can reconfigure this by running the FlutterFire CLI again.',
        );
      default:
        throw UnsupportedError(
          'DefaultFirebaseOptions are not supported for this platform.',
        );
    }
  }

  static const FirebaseOptions web = FirebaseOptions(
    apiKey: 'AIzaSyC6j9FG6b1WDuJH5EazBkP7SJtE6Z5_uzU',
    appId: '1:514001695365:web:ad017ba80d8f2d70a12fec',
    messagingSenderId: '514001695365',
    projectId: 'todo-firestore-63e18',
    authDomain: 'todo-firestore-63e18.firebaseapp.com',
    storageBucket: 'todo-firestore-63e18.appspot.com',
  );

  static const FirebaseOptions android = FirebaseOptions(
    apiKey: 'AIzaSyCeMoUXi9z7sIXp3HUDNOGg97jcackHELU',
    appId: '1:514001695365:android:0f281bc95a473366a12fec',
    messagingSenderId: '514001695365',
    projectId: 'todo-firestore-63e18',
    storageBucket: 'todo-firestore-63e18.appspot.com',
  );

  static const FirebaseOptions ios = FirebaseOptions(
    apiKey: 'AIzaSyCB6D64kqCLHXDjzET6gEHZ1X_akmffTDM',
    appId: '1:514001695365:ios:15636ed9b6ce6136a12fec',
    messagingSenderId: '514001695365',
    projectId: 'todo-firestore-63e18',
    storageBucket: 'todo-firestore-63e18.appspot.com',
    iosBundleId: 'com.example.todoApp',
  );

  static const FirebaseOptions macos = FirebaseOptions(
    apiKey: 'AIzaSyCB6D64kqCLHXDjzET6gEHZ1X_akmffTDM',
    appId: '1:514001695365:ios:15636ed9b6ce6136a12fec',
    messagingSenderId: '514001695365',
    projectId: 'todo-firestore-63e18',
    storageBucket: 'todo-firestore-63e18.appspot.com',
    iosBundleId: 'com.example.todoApp',
  );

  static const FirebaseOptions windows = FirebaseOptions(
    apiKey: 'AIzaSyC6j9FG6b1WDuJH5EazBkP7SJtE6Z5_uzU',
    appId: '1:514001695365:web:0523386449c0f52da12fec',
    messagingSenderId: '514001695365',
    projectId: 'todo-firestore-63e18',
    authDomain: 'todo-firestore-63e18.firebaseapp.com',
    storageBucket: 'todo-firestore-63e18.appspot.com',
  );

}