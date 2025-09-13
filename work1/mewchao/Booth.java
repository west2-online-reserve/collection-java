/**
 * @projectName（项目名称）: west2online_work1
 * @className（类名称）: Booth
 * @description（类描述）: 西瓜摊类
 * @author（创建人）: mewchao
 * @createDate（创建时间）: 2023-09-17
 */
public class Booth {
    public Booth() {
    }
    private long id;
    private String name="unnamed";
    private int total;
    boolean isClosed=false;
    public Booth(long id,String name,int total,boolean isClosed){
        this.id=id;
        this.name=name;
        this.total=total;
        this.isClosed=isClosed;
    }
    /**
     * @description:get函数
     */
    public long getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public int getTotal(){
        return this.total;
    }
    public boolean getIsClosed(){
        return this.isClosed;
    }
    /**
     * @description:set函数
     */
    public void setId(long id){
        this.id = id;
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
    /**
     * @description:toString输出西瓜摊的信息
     */
    public String toString(){
        return "西瓜摊\n摊号:"+this.id+"\n摊主姓名为:"+this.name+"\n在售西瓜数为:"+this.total+"\n是否休摊整改:"+(this.isClosed?"是":"否")+"\n";
    }
    /**
     * @description:toString向目标摊位卖家 购买指定数量的西瓜
     * @param num:购买数量
     */
    static void purchase(Booth booth,int num){
        if(num<0){
            System.out.println("请购买数量大于0的西瓜");
        }
        else if(booth.getIsClosed()){
            System.out.println("该商家正在休摊整改，无法进行购买");
        }
        else if(num>booth.getTotal()){
            System.out.println("购买数量大于正在售卖的数量，无法进行购买");
        }
        else {
            booth.setTotal(booth.getTotal()-num);
            System.out.printf("购买成功，你购买的西瓜数量为%d\n",num);
            System.out.printf("剩余数量为%d\n",booth.getTotal());
        }
    }
    /**
     * @description:为对应摊位进货
     * @param num:进货数量
     */
    public void restock(int num){
        if(num<0||num>200){
            System.out.printf("您进货的数量为:%d 商家应该进货的数量为0<num<200\n",num);
        }
        else{
            this.total+=num;
            System.out.printf("进货数量为%d,进货成功！！\n",num);
        }
    }
    /**
     * @description:把一些西瓜摊关闭
     * @param booths:一个西瓜摊数组
     */
    static void closeBooths(Booth[] booths){
        for(Booth booth:booths){
            if(booth.getIsClosed()) {
                System.out.println(booth.toString());
            }
            else booth.setIsClosed(true);
        }
    }

}

