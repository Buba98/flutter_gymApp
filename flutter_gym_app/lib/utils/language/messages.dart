enum MESSAGE_TYPE {
  LOGIN,
  EMAIL,
  PASSWORD,
  FORGOTTEN_PASSWORD,
  ACCESS
}

abstract class Messages {
  String message({type: MESSAGE_TYPE});
}