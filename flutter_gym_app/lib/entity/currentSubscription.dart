class CurrentSubscription {
  int totalEntrances;
  int leftEntrances;
  DateTime startSubscription;
  DateTime endSubscription;


  CurrentSubscription(this.totalEntrances, this.leftEntrances,
      this.startSubscription, this.endSubscription);

  CurrentSubscription.fromJson(Map<String, dynamic> json)
      :
        totalEntrances = json['totalEntrances'],
        leftEntrances = json['leftEntrances'],
        startSubscription = json['startSubscription'],
        endSubscription = json['endSubscription'];
}