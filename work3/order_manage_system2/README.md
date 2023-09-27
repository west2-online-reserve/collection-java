# order_manage_system2
## [功能描述]：连接数据库里的订单表和商品单表，对订单和商品进行增删查改
## [开发环境]：idea
## [项目结构简介]：
### Connection
连接数据库
### Controller
对数据库的相关操作
### Goods
商品类
### Order 
订单类
### Management
对订单和商品的相关操作
### Main
启动该订单管理系统
## [编写过程遇到的问题]
1.连接mysql出现问题
重新下载
2程序没有输出
设置界面形式，本来就没有输出，应该加setVisible
3.sql默认值表达式不能含有其他列或其他表的字段
`Biaoming`不是单引号

4.modify
//if(jTable.getSelectedColumn()<0){
//JOptionPane.showMessageDialog(null,"请选择要修改的数据！");
//}else{
//intorderID=Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(),0).toString());
//StringorderTime=jTable.getValueAt(jTable.getSelectedRow(),1).toString();
//Stringname=jTable.getValueAt(jTable.getSelectedRow(),2).toString();
//intnum=Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(),3).toString());
//doubleprofit=Double.parseDouble(jTable.getValueAt(jTable.getSelectedRow(),4).toString());
//Stringmanager=jTable.getValueAt(jTable.getSelectedRow(),5).toString();
//LocalDateTimeot=LocalDateTime.parse(orderTime);
//Orderorder=newOrder(orderID,ot,name,num,profit,manager);
//Stringsql="deletefrom`order`whereorderID="+orderID;
//intresult=updata.upData(sql);
//if(result>0){
//System.out.println("deletesuccess");
//}else{
//System.out.println("deletefail");
//}
//
//Stringsql0="SELECT*FROM`goods`wheregoodName='"+name+"'";
//Selectselect1=newSelect();
//try{
//Object[][]data=select1.getGoods(sql0);
//}catch(SQLExceptionex){
//thrownewRuntimeException(ex);
//}
//intrest=(int)data[0][3];
//System.out.println(rest);
//if(rest>0){
////Stringsql1="UPDATE`order_manage_system`.`order`SET`orderID`="+orderID+"orderTime="+orderTime+"goodsName="+name+"num="+num+"manager="+manager+"WHERE`orderID`="+preID;
//Stringsql2="UPDATE`order_manage_system`.`goods`SET`restNum`="+(rest-num)+"WHERE`goodName`='"+name+"'";//更新库存商品数量
//Stringsql1="INSERTINTO`order`VALUES('"+orderID+"','"+orderTime+"','"+name+"','"+num+"','"+manager+"')";
//if(Updata.upData(sql1)>0){
//Updata.upData(sql2);
//System.out.println("修改成功！");
//JOptionPane.showMessageDialog(null,"修改成功！");
//}
//}
//else{
//System.out.println("此商品已无库存，无法购买");
//}

//OrdersXGorderXG=newOrdersXG(order);
//orderXG.setVisible(true);
//}

