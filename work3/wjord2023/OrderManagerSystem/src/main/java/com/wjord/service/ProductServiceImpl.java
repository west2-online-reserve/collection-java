package com.wjord.service;

import com.wjord.dao.OrderDao;
import com.wjord.dao.ProductDao;
import com.wjord.info.Product;
import com.wjord.methods.RandomBarcodeGenerator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private Product product;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private OrderDao orderDao;

    private boolean isValidProductCode(String productCode) {
        // 使用正则表达式验证手机号码格式
        String regex = "^[0-9]{12}$";
        return Pattern.matches(regex, productCode);
    }

    private boolean isValidProduct(String productName, int productStock, BigDecimal productPrice) {
        if (productName == null) {
            return false;
        }
        // 设置一个-1的值假设客户端如果没有传参数则默认是-1
        if (productStock < 0 && productStock != -1) {
            return false;
        }
        return productPrice != null && productPrice.compareTo(BigDecimal.ZERO) >= 0;
    }

    @Override
    public void insertProduct(String productName, @Valid int productStock, @Valid BigDecimal productPrice) {
        if (!isValidProduct(productName, productStock, productPrice)) {
            System.out.println("创建失败,请输入正确的产品信息");
            return;
        }
        if (productDao.selectProductByName(productName) != null) {
            System.out.println("该产品已存在");
        } else {
            RandomBarcodeGenerator randomBarcodeGenerator = new RandomBarcodeGenerator();
            product.setProductCode(randomBarcodeGenerator.generateRandomBarcode());
            product.setProductName(productName);
            product.setProductStock(productStock);
            product.setProductPrice(productPrice);
            product.setCreateTime(LocalDateTime.now());
            product.setUpdateTime(LocalDateTime.now());
            productDao.insertProduct(product);
            System.out.println("成功创建了一个产品");
        }
    }

    @Override
    public void updateProduct(String productCode, String productName, int productStock, BigDecimal productPrice) {
        if (!isValidProductCode(productCode)) {
            System.out.println("更新失败,请输入正确的产品号");
            return;
        }
        if (!isValidProduct(productName, productStock, productPrice)) {
            System.out.println("更新失败,请输入正确的产品信息");
            return;
        }
        if (selectProduct(productCode) != null) {
            if (productName != null) {
                product.setProductName(productName);
            } else {
                product.setProductName(productDao.selectProductByCode(productCode).getProductName());
            }
            if (productStock >= 0) {
                product.setProductStock(productStock);
            } else {
                product.setProductStock(productDao.selectProductByCode(productCode).getProductStock());
            }
            if (productPrice != null) {
                product.setProductPrice(productPrice);
            } else {
                product.setProductPrice(productDao.selectProductByCode(productCode).getProductPrice());
            }
            product.setCreateTime(productDao.selectProductByCode(productCode).getCreateTime());
            product.setUpdateTime(productDao.selectProductByCode(productCode).getUpdateTime());
            productDao.updateProductByCode(productCode, product);
            System.out.println("成功更新了一个产品");
        } else {
            System.out.println("更新失败,该产品不存在");
        }
    }

    @Override
    public void deleteProduct(String productCode) {
        if (!isValidProductCode(productCode)) {
            System.out.println("删除失败,请输入正确的产品号");
            return;
        }
        if (!isValidProductCode(productCode)) {
            System.out.println("删除失败,请输入正确的产品号");
            return;
        }
        if (selectProduct(productCode) != null) {
            if (orderDao.selectOrdersByProductCode(productCode) != null) {
                String string = orderDao.selectOrdersByProductCode(productCode).toString();
                orderDao.deleteOrderByProductCode(productCode);
                productDao.deleteProductByCode(productCode);
                System.out.println("成功删除了一个产品以及其关联的订单信息:\n" + string);
            } else {
                productDao.deleteProductByName(productCode);
                System.out.println("成功删除了一个产品");
            }
        } else {
            System.out.println("删除失败,该产品不存在");
        }
    }

    @Override
    public Product selectProduct(String productCode) {
        if (!isValidProductCode(productCode)) {
            System.out.println("查询失败,请输入正确的产品号");
            return null;
        }
        if (!isValidProductCode(productCode)) {
            System.out.println("查询失败,请输入正确的产品号");
            return null;
        }
        Product selected = productDao.selectProductByCode(productCode);
        return selected;
    }

    @Override
    public Product selectProductByName(String productName) {
        if (productName == null) {
            System.out.println("查询失败,请输入正确的产品名");
            return null;
        }
        Product selected = productDao.selectProductByName(productName);
        return selected;
    }

    @Override
    public List<Product> selectAllProducts() {
        List<Product> selected = productDao.selectAllProducts();
        return selected;
    }

    @Override
    public int selectTotalProductCount() {
        int selected = productDao.selectTotalProductCount();
        return selected;
    }

    @Override
    public List<Product> selectSortedProductByPrice() {
        List<Product> selected = productDao.sortProductByPrice();
        return selected;
    }

    @Override
    public List<Product> selectSortedProductByUpdateTime() {
        List<Product> selected = productDao.sortProductByUpdatedTime();
        return selected;
    }


}
