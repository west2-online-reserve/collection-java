public class Cat extends animal{
        public Cat(String n, int a, String s, double p){
            super(n,a,s,p);
        }

        public String toString(){
            return "名字是：" + name +
                    "\n年龄是：" + age +
                    "\n性别是：" + sex +
                    "\n价格是：" + price+
                    "\n";
        }
    }

