package com.haha.Management;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import com.haha.Goods.Goods;
import com.haha.Controller.*;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class GoodsManage extends JFrame {
    private JTextField textField;
    Select select = new Select();
    Updata updata = new Updata();
    Object[] header= {"商品编号","商品名称","单价","库存数量"};
    String sql = "SELECT goodID,goodName,price,restNum FROM `goods`";
    Object[][] data= select.getGoods(sql);
    DefaultTableModel df = new DefaultTableModel(data, header);
    int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
    int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
    JTable jTable = new JTable(df);
    JScrollPane jsp=new JScrollPane(jTable,v,h);

    public GoodsManage() throws SQLException {
        super("订单管理系统");
        this.setBounds(0, 0, 700, 450);
        this.setLocationRelativeTo(null);//让窗口在屏幕中间显示
        this.setResizable(false);//让窗口大小不可改变
        getContentPane().setLayout(null);


        jsp.setBounds(10, 10, 515, 320);
        getContentPane().add(jsp);

        //显示所有商品信息
        ShowAll();
        //修改商品信息
        Modify();
        //删除
        Delete();
        //添加
        Add();
        //查询
        Query();


    }




    private void ShowAll() {
        JButton button_1 = new JButton("显示所有商品");
        button_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "SELECT goodID,goodName,price,restNum FROM `goods`";
                Object[][] data = new Object[0][];
                try {
                    data = Select.getGoods(sql);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                df.setDataVector(data, header);
            }
        });

        button_1.setBounds(535, 80, 127, 30);
        getContentPane().add(button_1);

    }

    private void Modify() {
        JButton button_2 = new JButton("修改商品信息");
        button_2.setBounds(535, 140, 127, 30);
        getContentPane().add(button_2);
        button_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GoodsModify goods = new GoodsModify();
                goods.setVisible(true);

            }
        });

    }
    private void Delete() {
        JButton button_3 = new JButton("删除商品");
        button_3.setBounds(535, 200, 127, 30);
        getContentPane().add(button_3);
        button_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jTable.getSelectedColumn()<0) {
                    JOptionPane.showMessageDialog(null, "请选中要删除的数据！");
                } else {
                    int goodsID = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 0).toString());
                    String sql="delete from `goods` where goodID="+goodsID;
                    int result = updata.upData(sql);
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

    private void Add() {
        JButton button_4 = new JButton("添加商品");
        button_4.setBounds(535, 258, 127, 30);
        getContentPane().add(button_4);
        button_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                GoodsAdd goodsAdd = new GoodsAdd();
                goodsAdd.setVisible(true);
            }
        });

    }


    private void Query() {
        JLabel label = new JLabel("商品编号：");
        label.setBounds(40, 354, 112, 32);
        getContentPane().add(label);

        textField = new JTextField();
        textField.setBounds(154, 358, 127, 26);
        getContentPane().add(textField);
        textField.setColumns(10);

        JButton button = new JButton("按编号查询");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String sql = "SELECT goodID,goodName,price,restNum FROM `goods` WHERE goodID ="+textField.getText();
                Object[][] data = new Object[0][];
                try {
                    data = Select.getGoods(sql);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                df.setDataVector(data, header);
            }
        });
        button.setBounds(305, 355, 112, 30);
        getContentPane().add(button);

    }

}


