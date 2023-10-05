
public class Booth {
        private long id;
        private String name;
        private int total;
        private boolean isClosed;
        public long getId(){
            return this.id;
        }
        public void setId(long id){
            this.id = id;
        }
        public String getName(){
            return this.name;
        }
        public void setName(String name){
            this.name = name;
        }
        public int getTotal(){
            return this.total;
        }
        public void setTotal(int total) {
            this.total = total;
        }
        public boolean getIsClosed(){
            return this.isClosed;
        }
        public void setIsClosed(boolean isClosed){
            this.isClosed = isClosed;
        }
        @Override
        public String toString() {
            return "Booth{"+
                    "id:"+id+",name:"+name+",total:"+total+",isClosed:";
        }
        public Booth(long id,String name,int total,boolean isClosed){
            this.id = id;
            this.name = name;
            this.total = total;
            this.isClosed = isClosed;
        }
        public static void purchase(Booth name,int buyNum){
            if (buyNum<0){
                System.out.println("购买失败，因为购买数量小于0");
            }else if(name.getIsClosed()){
                System.out.println("购买失败，因为处于休摊状态");
            }else if(buyNum>name.total){
                System.out.println("购买失败，因为购买西瓜数大于在售西瓜数");
            }else {
                System.out.println("购买成功");
            }
        }
        public void restock(int stockNum){
            if (stockNum>200 || stockNum<0){
                System.out.println("进货失败");
            }else {
                System.out.println("进货成功");
            }
        }



        public static void closeBooths(Booth[] booths){
            for (int i = 0; i < booths.length; i++) {
                if (!booths[i].getIsClosed()){
                    booths[i].setIsClosed(true);
                }else {
                    System.out.println(booths[i].toString());
                }

            }
        }





    }

