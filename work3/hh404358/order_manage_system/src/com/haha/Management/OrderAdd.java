package com.haha.Management;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.haha.Controller.Updata;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class OrderAdd extends JFrame {
    private JTextField id,ordertime,name,num,manager;
    private JButton button;
    private JButton button_1;

    public void MainJFrame(){
        this.setBounds(0, 0, 400, 450);
        this.setLocationRelativeTo(null);//让窗口在屏幕中间显示
        this.setResizable(false);//让窗口大小不可改变
        getContentPane().setLayout(null);

        JLabel label = new JLabel("订单编号：");
        label.setBounds(85, 89, 87, 22);
        getContentPane().add(label);
        id = new JTextField();
        id.setBounds(147, 90, 142, 21);
        getContentPane().add(id);
        id.setColumns(10);

        JLabel label_1 = new JLabel("下单时间");
        label_1.setBounds(85, 139, 87, 22);
        getContentPane().add(label_1);
        ordertime = new JTextField();
        ordertime.setColumns(10);
        ordertime.setBounds(147, 140, 142, 21);
        getContentPane().add(ordertime);

        JLabel label_3 = new JLabel("商品名称：");
        label_3.setBounds(85, 189, 87, 22);
        getContentPane().add(label_3);
        name = new JTextField();
        name.setColumns(10);
        name.setBounds(147, 190, 142, 21);
        getContentPane().add(name);


        JLabel label_4=new JLabel("购买数量");
        label_4.setBounds(85,239,87,22);
        getContentPane().add(label_4);
        num = new JTextField();
        num.setColumns(10);
        num.setBounds(147, 240, 142, 21);
        getContentPane().add(num);


        JLabel label_5=new JLabel("负责人");
        label_5.setBounds(85,289,87,22);
        getContentPane().add(label_5);
        manager = new JTextField();
        manager.setColumns(10);
        manager.setBounds(147, 290, 142, 21);
        getContentPane().add(manager);
    }

    public OrderAdd() {
        super("订单管理系统");
        MainJFrame();
        button = new JButton("确定");
        button.setBounds(78, 329, 93, 23);
        getContentPane().add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String addId = id.getText();
                String addOrderTime=ordertime.getText();
                String addName = name.getText();
                String addNum = num.getText();
                String addManager = manager.getText();
                if (addName.equals("")||addOrderTime.equals("")||addName.equals("")||addNum.equals("")||addManager.equals("")) {
                    JOptionPane.showMessageDialog(null, "请完整输入要添加的数据");
                } else {
                    String sql="INSERT INTO `order` VALUES('"+addId+"','"+addOrderTime+"','"+addName+"','"+addNum+"','"+addManager+"')";
                    int result = Updata.upData(sql);
                    if (result>0) {
                        JOptionPane.showMessageDialog(null, "添加成功！");
                        JOptionPane.showMessageDialog(null, "记得刷新一下哦！");
                        dispose();

                    } else {
                        JOptionPane.showMessageDialog(null, "添加失败！");
                    }
                }

            }
        });

        button_1 = new JButton("取消");
        button_1.setBounds(208, 329, 93, 23);
        getContentPane().add(button_1);
        button_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dispose();
            }
        });

    }



}

