package Y.druid;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

public class DruidDataSourceFactory extends UnpooledDataSourceFactory{
    public DruidDataSourceFactory(){
        this.dataSource = new DruidDataSource();
    }
}
