package Booths;

public class Booth {


    private long id;//设置私人变量
        private String name;
        private int total;
        private boolean isClosed;
        /*有参构造 */
        public Booth (long id,String name,int total,boolean isClosed){
            this.id=id;
            this.name=name;
            this.total=total;
            this.isClosed=isClosed;

        }
        /*get方法 */
        public long getId(){
            return id;
        }
        public String getName(){
            return name;
        }
        public int getTotal(){
            return total;
        }
        public boolean getIsClosed(){
            return isClosed;
        }
        /*set方法 */
        public void setId(long id){
            this.id=id;
        }
        public void setName(String name){
            this.name=name;
        }
        public void setTotal(int total){
            this.total=total;
        }
        public void setIsClosed(boolean isClosed){
            this.isClosed=isClosed;
        }
        /*重写的toString方法 */
        public String toString(){
            return"摊号："+id+" "+
                    "摊主名字："+name+" "+
                    "在售西瓜数："+total+" " +
                    "是否休摊整改： "+isClosed;
        }
        /*购买西瓜的方法 */
        public static void purchase(Booth booth,int nums){
            if(nums<=0||booth.getIsClosed()==true||nums>booth.getTotal()){
                System.out.println("交易失败。");
            }
           else{
               booth.setTotal(booth.getTotal()-nums);
                System.out.println("交易成功。");
            }
        }
        /*进货方法 */
        public  void restock(Booth booth, int nums2){
            if(nums2<=0){
                System.out.println("请进货");
            }
            if(nums2>200){
                System.out.println("出货方无法提供这么多的西瓜");
            }
            if(nums2>0&&nums2<200){
                booth.setTotal(booth.getTotal()+nums2);
                System.out.println("进货成功");
            }
        }
        /*歇业方法 */
        public static void closeBooths(Booth[]booths){
            int i=0;
            for(i=0;i<booths.length;i++){
                if(booths[i].getIsClosed()==false){
                    booths[i].setIsClosed(true);
                }else{
                    System.out.println(booths[i].toString());
                }
            }
        }
    }


