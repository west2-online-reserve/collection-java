package com.wjord.service;

import com.wjord.dao.BuyerDao;
import com.wjord.dao.OrderDao;
import com.wjord.info.Buyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

@Service
@Transactional//应该这样就开启了事务了吧，不过好像我没有什么事务的需要
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private Buyer buyer;
    @Autowired
    private BuyerDao buyerDao;
    @Autowired
    private OrderDao orderDao;

    /* 原先想用spring的校验器进行校验的，但是不知道这校验器怎么这么难用，测试很麻烦，还要爆红，搞了两三个小时没有搞好，想想还是自己写表达式了
       好像springboot的比较简单，后面如果学完会改用注解的方式进行校验
     */
    //TODO:使用注解的方式进行校验
    private boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber != null) {
            // 使用正则表达式验证手机号码格式
            String regex = "^1[3|4|5|7|8][0-9]\\d{8}$";
            return Pattern.matches(regex, phoneNumber);
        }
        return false;

    }

    @Override
    public void insertBuyer(String buyerPhone, String buyerName, String buyerAddress) {
        if (!isValidPhoneNumber(buyerPhone)) {
            System.out.println("创建失败,请输入正确的手机号码");
            return;
        }
        if (selectBuyerByPhone(buyerPhone) != null) {
            System.out.println("创建失败,该买家已存在");
        } else {
            buyer.setBuyerName(buyerName);
            buyer.setBuyerPhone(buyerPhone);
            buyer.setBuyerAddress(buyerAddress);
            buyer.setCreateTime(LocalDateTime.now());
            buyer.setUpdateTime(LocalDateTime.now());
            buyerDao.insertBuyer(buyer);
            System.out.println("成功创建了一个买家信息");
        }
    }

    /**
     * 更新买家信息，如果提供了买家电话，则使用电话来更新买家信息，如果没有提供电话但是提供了买家姓名，则使用姓名来更新买家信息
     *
     * @param buyerPhone
     * @param buyerName
     * @param buyerAddress
     */
    @Override
    public void updateBuyerByPhone(String buyerPhone, String buyerName, String buyerAddress) {
        if (buyerPhone != null) {
            if (!isValidPhoneNumber(buyerPhone)) {
                System.out.println("更新失败,请输入正确的手机号码");
                return;
            }
        }
        if (buyerPhone != null) {
            if (selectBuyerByPhone(buyerPhone) != null) {
                if (buyerName != null) {
                    buyer.setBuyerName(buyerName);
                } else {
                    buyer.setBuyerName(buyerDao.selectBuyerByPhone(buyerPhone).getBuyerName());
                }
                if (buyerAddress != null) {
                    buyer.setBuyerAddress(buyerAddress);
                } else {
                    buyer.setBuyerAddress(buyerDao.selectBuyerByPhone(buyerPhone).getBuyerAddress());
                }
                buyer.setCreateTime(buyerDao.selectBuyerByPhone(buyerPhone).getCreateTime());
                buyer.setUpdateTime(LocalDateTime.now());
                buyerDao.updateBuyerByPhone(buyerPhone, buyer);
                System.out.println("成功更新了一个买家信息");
            } else {
                System.out.println("更新失败,该买家不存在");
            }
        } else if (buyerName != null) {
            if (selectBuyerCountByName(buyerName) == 1) {
                if (buyerAddress != null) {
                    buyer.setBuyerAddress(buyerAddress);
                } else {
                    buyer.setBuyerAddress(buyerDao.selectBuyerByName(buyerName).getBuyerAddress());
                }
                buyer.setBuyerPhone(buyerDao.selectBuyerByName(buyerName).getBuyerPhone());
                buyer.setCreateTime(buyerDao.selectBuyerByName(buyerName).getCreateTime());
                buyer.setUpdateTime(LocalDateTime.now());
                buyerDao.updateBuyerByName(buyerName, buyer);
                System.out.println("成功更新了一个买家信息");
            } else {
                System.out.println("更新失败,存在多个买家，请提供买家电话以确认买家身份");
            }
        } else {
            System.out.println("更新失败");
        }
    }

    /**
     * TODO：原本我希望可以进行一个询问是否确认要删除，但是发现scanner没办法进行输入不知道是不是spring框架的原因，不过正常是用客户端传数据了，就不搞了
     *
     * @param buyerPhone
     */
    @Override
    public void deleteBuyer(String buyerPhone) {
        if (!isValidPhoneNumber(buyerPhone)) {
            System.out.println("删除失败,请输入正确的手机号码");
            return;
        }
        if (selectBuyerByPhone(buyerPhone) != null) {
            buyerDao.deleteBuyerByPhone(buyerPhone);
            orderDao.deleteOrderByBuyerPhone(buyerPhone);
            System.out.println("成功删除了一个买家信息");
        } else {
            System.out.println("删除失败,该买家不存在");
        }
    }

    @Override
    public Buyer selectBuyerByPhone(String buyerPhone) {
        if (!isValidPhoneNumber(buyerPhone)) {
            System.out.println("查询失败,请输入正确的手机号码");
            return null;
        }
        Buyer selected = buyerDao.selectBuyerByPhone(buyerPhone);
        return selected;
    }

    @Override
    public Buyer selectBuyerByName(String buyerName) {
        if (buyerName == null) {
            System.out.println("查询失败,请输入买家姓名");
            return null;
        }
        Buyer selected = buyerDao.selectBuyerByName(buyerName);
        return selected;
    }

    @Override
    public List<Buyer> selectAllBuyers() {
        List<Buyer> selected = buyerDao.selectAllBuyers();
        return selected;
    }

    @Override
    public int selectTotalBuyerCount() {
        int selected = buyerDao.selectTotalBuyerCount();
        return selected;
    }

    @Override
    public int selectBuyerCountByName(String buyerName) {
        int selected = buyerDao.selectBuyerCountByName(buyerName);
        return selected;
    }
}
