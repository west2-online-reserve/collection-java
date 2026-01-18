package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.mapper.CommodityMapper;
import org.example.pojo.Commodity;
import org.example.service.CommodityService;
import org.example.utils.MyBatisUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class CommodityServiceImpl implements CommodityService {

    @Override
    public void add(String name, double price) throws IOException {
        try(SqlSession session = MyBatisUtils.getSqlSession()){
            CommodityMapper mapper = session.getMapper(CommodityMapper.class);
            Commodity commodity = new Commodity(name,price);
            mapper.add(commodity);
            List<Commodity> list = mapper.queryAllShop();
            list.forEach(System.out::println);
            session.commit();
            System.out.println("成功新增该商品");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //显示保存的所有商品列表
    @Override
    public List<Commodity> queryAll() {
        List<Commodity> list = null;
        int pageNum =  1;
        int pageSize = 5;
        try (SqlSession session = MyBatisUtils.getSqlSession()) {
            CommodityMapper mapper = session.getMapper(CommodityMapper.class);
            Scanner sc = new Scanner(System.in);
            String control=" ";
            while(true){
                if(control.charAt(0)=='n'){
                    pageNum += 1;
                }else if (control.charAt(0)=='p'){
                    pageNum -=1;
                }else if(control.charAt(0)=='q'){
                    break;
                }else if(control==" "){
                }
                else{
                    System.out.println("输入不符合格式，请重新输入");
                }
                PageHelper.startPage(pageNum,5);
                list = mapper.queryAll();
                PageInfo<Commodity> pageInfo = new PageInfo<>(list);
                System.out.println("查询结果：");
                pageInfo.getList().forEach(System.out::println);
                System.out.println("当前页码：" + pageInfo.getPageNum()+" 总记录数：" + pageInfo.getTotal()+" 总页数：" + pageInfo.getPages());
                if(pageNum>pageInfo.getTotal()){
                    pageNum -=pageSize;
                }
                System.out.println("输入n为下一页,p为上一页,输入q则退出翻页功能");
                control = sc.next();
                session.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    //显示当前能够购买的商品列表
    @Override
    public List<Commodity> queryAllShop(){
        List<Commodity> list = null;
        int pageNum = 1;
        int pageSize = 5;
        try (SqlSession session = MyBatisUtils.getSqlSession()) {
            CommodityMapper mapper = session.getMapper(CommodityMapper.class);
            Scanner sc = new Scanner(System.in);
            String control=" ";
            while(true){
                if(control.charAt(0)=='n'){
                    pageNum += 1;
                }else if (control.charAt(0)=='p'){
                    pageNum -= 1;
                }else if(control.charAt(0)=='q'){
                    break;
                }
                PageHelper.startPage(pageNum,5);
                list = mapper.queryAllShop();
                PageInfo<Commodity> pageInfo = new PageInfo<>(list);
                System.out.println("查询结果：");
                pageInfo.getList().forEach(System.out::println);
                System.out.println("当前页码：" + pageInfo.getPageNum()+" 总记录数：" + pageInfo.getTotal()+" 总页数：" + pageInfo.getPages());
                if(pageNum>pageInfo.getTotal()){
                    pageNum -=pageSize;
                }
                System.out.println("输入n为下一页,p为上一页,输入q则退出翻页功能");

                control = sc.next();
                session.commit();
            }
            list = mapper.queryAllShop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void deleteById(int id) {
        try (SqlSession session = MyBatisUtils.getSqlSession()) {
            CommodityMapper mapper = session.getMapper(CommodityMapper.class);
            mapper.deleteById(id);
            session.commit();
            System.out.println("该商品已删除");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Commodity queryById(int id) {
        Commodity commodity=null;
        try (SqlSession session = MyBatisUtils.getSqlSession()) {
            CommodityMapper mapper = session.getMapper(CommodityMapper.class);
            commodity = mapper.queryById(id);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commodity;
    }

    public static void main(String[] args) {
        CommodityServiceImpl commodityService = new CommodityServiceImpl();
        commodityService.queryAllShop();
    }
}
