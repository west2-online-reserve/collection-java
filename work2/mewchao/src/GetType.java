public class GetType {

    public static String getType(Object obj) {
        /**
         *  1. 通过反射获取传来参数的JavaClass对象
         *  2. 获取到JavaClass对象的类型名称
         *  3. 将参数的类型名称返回
         */
        return obj.getClass().getTypeName();
    }

}
