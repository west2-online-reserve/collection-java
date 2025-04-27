package MySystem.DaoImpl;

import MySystem.Entity.Goods;

import java.util.ArrayList;

public abstract class BaseDaoIml<E> {

    final public String SQL_INS = "INSERT INTO %s(%s) VALUES (%s);";//sql插入语句

    final public String SQL_UPDATE = "UPDATE %s SET %s=?,%s=?,%s=? WHERE %s;";//sql更新语句

    final public String SQL_DEL = "DELETE FROM %s WHERE %s;";//sql删除语句

    final public String SQL_SEL = "SELECT %S FROM %S;";//sql查询语句

    final public String SQL_SEL_SORT = "SELECT %S FROM %S ORDER BY %s;";//sql查询并排序语句

    final public String SQL_SEL_BY = "SELECT %s FROM %s WHERE %s;";//sql条件查询语句

    final public String SQL_SEL_BY_SORT = "SELECT %s FROM %s WHERE %s ORDER BY %s;";//sql条件查询并排序语句

    final public String SQL_SEL_BY_PAGE = "SELECT %s FROM %s WHERE %s LIMIT %d, %d;";//sql分页查询

    final public String SQL_SEL_BY_SORT_PAGE = "SELECT %s FROM %s WHERE %s ORDER BY %s LIMIT %d, %d;";//sql分页查询

    final public String SQL_COUNT = "SELECT COUNT(%s) FROM %s WHERE %s;";//sql聚合查询

    final public String OnePlaceholders = "?";//该表（goods）所需的一个占位符

    final public String TwoPlaceholders = "?,?";//该表（goods）所需的两个占位符

    final public String ThreePlaceholders = "?,?,?";//该表（goods）所需的三个占位符

    public abstract int insert(E... entitys);//插入

    public abstract int update(E... entities);//更新

    public abstract int deleteById(int id);//删除

    public abstract E getById(int id);//通过id获取

    public abstract ArrayList<E> getByAmountAbove(int amount);//获取特定金额以上

    public abstract ArrayList<E> getByAmountBelow(int amount);//获取特定金额以下

    public abstract ArrayList<E> getByPage(int pagenum, int linage);//页数，每页行数

    public abstract ArrayList<E> getByPage(String sortWay, int pagenum, int linage);//获取List<E>通过分页获取

    public abstract ArrayList<E> getAll();//获取所有

    public abstract ArrayList<E> getAll(String sortWay);

    public abstract int getCount();//聚合查询总和


}
