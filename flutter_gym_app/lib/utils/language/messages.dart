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
  ERROR
}

abstract class Messages {
  String message({type: MESSAGE_TYPE});
}