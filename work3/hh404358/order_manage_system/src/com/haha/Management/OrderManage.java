package com.haha.Management;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.haha.Connection.DbConnection;
import com.haha.Controller.*;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class OrderManage extends JFrame {
    private JTextField textField;
    Select select = new Select();
    Updata updata = new Updata();
    Object[] header= {"订单编号","下单时间","商品名称","购买数量","利润","负责人"};
    String sql = "SELECT orderID,orderTime,goodsName,price ,num,manager FROM `goods` inner join `order` on goods.goodName=order.goodsName ";
    Object[][] data =  Select.getOrders(sql);
    DefaultTableModel df = new DefaultTableModel(data, header);
    int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
    int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;

    int preID;
    //订单表格
    JTable jTable = new JTable(df);
    JScrollPane jsp=new JScrollPane(jTable,v,h);


    public OrderManage() throws SQLException {
        //订单管理系统主界面
        super("订单管理系统");
        this.setBounds(0, 0, 700, 450);
        this.setLocationRelativeTo(null);//让窗口在屏幕中间显示
        this.setResizable(false);//让窗口大小不可改变
        getContentPane().setLayout(null);

        jsp.setBounds(10, 10, 515, 320);
        getContentPane().add(jsp);

        //”刷新“按钮
        JButton button_1 = new JButton("刷新");
        button_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "set autocommit=0;";
                String sql1="start transaction;";
                String sql2="SELECT orderID,orderTime,goodsName,price ,num,manager FROM `goods` inner join `order` on goods.goodName=order.goodsName order by orderID limit 0,10;";
                String sql3="commit;";
                String sql4="rollback; ";
                Object[][] data = new Object[0][];
                try {
                    DbConnection.updateInfo(sql1);
                    data = Select.getOrders(sql2);
                    DbConnection.updateInfo(sql3);
                    DbConnection.updateInfo(sql4);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                df.setDataVector(data, header);
            }
        });

        button_1.setBounds(535, 40, 127, 30);
        getContentPane().add(button_1);


        //“修改订单”按钮
        modifyButton();
        //“删除订单”按钮
        //根据编号查询
        deleteButton();
        //“添加订单”
        addButton();
        //查询订单
        queryButton();
        //显示商品信息
        ShowGoods();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置关闭模式
//        //设置关闭增删查改功能后显示主界面
//        this.addWindowListener(new WindowAdapter() {
//
//            public void windowClosing(WindowEvent e) {
//                super.windowClosing(e);
//                //加入动作
//                OrderManage m = null;
//                try {
//                    m = new OrderManage();
//                } catch (SQLException ex) {
//                    throw new RuntimeException(ex);
//                }
//                m.setVisible(true);
//            }
//        });


    }

    public void addButton(){
        JButton button_4 = new JButton("添加订单");
        button_4.setBounds(535, 190, 127, 30);
        getContentPane().add(button_4);
        button_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                OrderAdd ordersAdd = new OrderAdd();
                ordersAdd.setVisible(true);
            }
        });
    }

    public void deleteButton(){
        JButton button_3 = new JButton("删除订单");
        button_3.setBounds(535, 140, 127, 30);
        getContentPane().add(button_3);
        button_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jTable.getSelectedColumn()<0) {
                    JOptionPane.showMessageDialog(null, "请选中要删除的数据！");
                } else {
                    int orderID = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 0).toString());
                    String sql="delete from `order` where orderID="+orderID;
                    int result = Updata.upData(sql);
                    if (result>0) {
                        JOptionPane.showMessageDialog(null, "删除成功！");
                        JOptionPane.showMessageDialog(null, "记得刷新一下哦！");
                    } else {
                        JOptionPane.showMessageDialog(null, "删除失败！");
                    }
                }
            }
        });
    }

    public void queryButton(){
        JLabel label = new JLabel("订单编号：");
        label.setBounds(40, 354, 112, 32);
        getContentPane().add(label);

        textField = new JTextField();
        textField.setBounds(154, 358, 127, 26);
        getContentPane().add(textField);
        textField.setColumns(10);

        JButton button = new JButton("按编号查询");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String sql = "SELECT orderID,orderTime,goodsName,price,num,manager FROM `goods` inner join `order` on goods.goodName=order.goodsName where orderID="+textField.getText();
                Object[][] data = new Object[0][];
                try {
                    data = Select.getOrders(sql);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                df.setDataVector(data, header);
            }
        });
        button.setBounds(305, 355, 112, 30);
        getContentPane().add(button);
    }

    public void modifyButton(){

        JButton button_2 = new JButton("修改订单");
        button_2.setBounds(535, 90, 127, 30);
        getContentPane().add(button_2);
        button_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                OrderModify ordersAdd = new OrderModify();
                ordersAdd.setVisible(true);

            }
        });
    }
    public void ShowGoods(){
        JButton button_4 = new JButton("显示商品信息");
        button_4.setBounds(535, 298, 127, 30);
        getContentPane().add(button_4);
        button_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                GoodsManage goods = null;
                try {
                    goods = new GoodsManage();
                    goods.setVisible(true);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }
    public static void main(String[] args) throws SQLException {
        OrderManage t = new OrderManage();
        t.setVisible(true);
    }
}



