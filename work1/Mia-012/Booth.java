public class Booth {
        private long id;
        private String name;
        private int total;
        private boolean isClosed;
        public long getId() {
                return id;
        }

        public void setId(long id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public int getTotal() {
                return total;
        }

        public void setTotal(int total) {
                this.total = total;
        }

        public boolean isClosed() {
                return isClosed;
        }

        public void setClosed(boolean closed) {
                isClosed = closed;
        }

        public String toString()
        {
                System.out.printf("%d %s %d %B\n",getId(),getName(),getTotal(),isClosed());
                return "输出完成";
        }
        public void inItl(long Id,String Name,int Total,boolean IsClosed)
        {
                setId(Id);
                setName(Name);
                setTotal(Total);
                setClosed(IsClosed);
        }
        public static void purchase(Booth b,int buy)
        {
                if(buy<=0)
                {
                        System.out.println("卖不了负数个瓜");
                }
                else if(b.isClosed)
                {
                        System.out.println("休摊啦！下次再来！");
                }
                else if(buy> b.total)
                {
                        System.out.println("没那么多瓜");
                }
                else
                {
                        b.total-=buy;
                        System.out.println("交易成功");
                }
        }
        public void restock(int buyIn)
        {
                if(buyIn>200)
                {
                        System.out.println("进货超量");
                }
                else if(buyIn<0)
                        System.out.println("数量错误");
                else
                        this.total+=buyIn;
        }
        public static void closeBooths(Booth[] booths)
        {
            for (Booth booth : booths) {
                if (booth.isClosed) {
                    String s = booth.toString();
                        System.out.println(s);
                } else booth.isClosed = true;
            }
        }


}
