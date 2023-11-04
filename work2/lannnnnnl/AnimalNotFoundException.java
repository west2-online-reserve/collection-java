package westwork2;

//自定义未找到动物异常
class AnimalNotFoundException extends RuntimeException {
 AnimalNotFoundException(String message) {
     super(message);
 }
}
