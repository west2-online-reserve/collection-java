public class tan {
        private long id;
        private String name;
        private int total;
        private boolean isClosed;
        public tan(long id, String name, int total, boolean isClosed) {
            this.id=id;
            this.name=name;
            this.total=total;
            this.isClosed=isClosed;
        }

        public long getId() {
            return id;
        }
        public String getName(){
            return name;
        }
        public int getTotal(){
            return total;
        }

        public boolean getIsClosed() {
            return isClosed;
        }

        public void setId(long id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public void setIsClosed(boolean isClosed) {
            this.isClosed = isClosed;
        }


        public String toString() {

            return "摊号："+getId()+
                    "\n摊主姓名："+getName()+
                    "\n在售西瓜数："+getTotal()+
                    "\n是否休摊整改："+getIsClosed();
        }
        public static void purchase(tan tan, int numsOfpurchases){
            if(numsOfpurchases<0|| tan.getIsClosed() ||numsOfpurchases> tan.getTotal()){
                System.out.println("购买失败。");
            }else{
                System.out.println("购买成功。");
                tan.setTotal(tan.getTotal()-numsOfpurchases);
            }
        }
        public void restock(int numsOfpurchases){
            if (numsOfpurchases>200||numsOfpurchases<0){
                System.out.println("进货失败。");
            }else{
                total+=numsOfpurchases;
                System.out.println("进货成功");
            }
        }
        public static void closetans(tan[] tans){
            for(int i=0;i<=tans.length-1;i++){
                if(!tans[i].isClosed){
                    tans[i].isClosed=true;
                }
                else{
                    System.out.println("全体休业");
                }
            }
        }
    }

