import 'dart:core';

class User {
  final String name;
  final String surname;
  final String email;
  final String fiscalCode;
  final birthday;
  final String phone;
  final insurances;
  final bool owner;
  final int id;
  final String uuid;

  User({this.name, this.surname, this.email, this.fiscalCode, this.birthday,
      this.phone, this.insurances, this.owner, this.id, this.uuid});
}