package shop;

import java.util.Scanner;

/**
 * @author HarveyBlocks
 * @date 2023/08/15 12:19
 **/

public interface AnimalShop {
    void purchase(Scanner scanner);//买入新动物
    void serve(Scanner scanner);//招待客户
    void shutDown();
}
