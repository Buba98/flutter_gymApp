class UserTrainingSchedule{
  final int id;
  final int userId;
  final int trainingScheduleId;
  final DateTime startDate;
  final DateTime endDate;
  final String comment;

  UserTrainingSchedule({this.id, this.userId, this.trainingScheduleId,
      this.startDate, this.endDate, this.comment});
}