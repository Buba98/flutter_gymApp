enum MESSAGE_TYPE {
  LOGIN,
  EMAIL,
  PASSWORD,
  FORGOTTEN_PASSWORD,
  ACCESS,
  WELCOME,
  SCAN_HERE,
  LETS_START,
  DETAILS,
  DONE,
  OK,
  ERROR_LOGIN,
  ERROR,
  ENTRANCE_CARD,
  REMAINING_DAYS,
  SCAN,
  INSERT_UID,
  TRAINING_SCHEDULES,
  EXERCISE,
  SETS,
  REPS,
  VIDEO,
  NUMBER_ABBREVIATION,
  SECOND_FOR_REP
}

abstract class Messages {
  String message({type: MESSAGE_TYPE});
}