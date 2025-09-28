package resultByPageBAO;

import java.util.ArrayList;

public class PageResult<T> {
    private int pageNumber;//当前页码
    private int pageSize;//每一页显示的数量
    private int total;//总记录
    private int totalPage;//总页数
    private ArrayList<T> data;//当前页数据

    public PageResult(int  pageNumber, int pageSize,ArrayList<T> data) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.data = data;
    }

    public PageResult(int pageNumber, int pageSize, int total, int totalPage, ArrayList<T> data) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.total = total;
        this.totalPage = totalPage;
        this.data = data;
    }

    //set
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public void setData(ArrayList<T> data) {
        this.data = data;
    }

    //get
    public int getPageNumber() {
        return pageNumber;
    }
    public int getPageSize() {
        return pageSize;
    }
    public int getTotal() {
        return total;
    }
    public int getTotalPage() {
        return totalPage;
    }
    public ArrayList<T> getData() {
        return data;
    }
}
