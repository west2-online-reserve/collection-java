package DAWSearch.jar;

import java.util.Comparator;
//自定义Comparator来实现按国家升序再按姓氏升序
public class myComparator implements Comparator<Athletes> {
    @Override
    public int compare(Athletes p1, Athletes p2) {
        int countryCompare = p1.getCountryName().compareTo(p2.getCountryName());
        if (countryCompare != 0) {
            return countryCompare;
        }
        String p1LastName=p1.getFullName().substring(p1.getFullName().lastIndexOf(" ")+1);
        String p2LastName=p2.getFullName().substring(p2.getFullName().lastIndexOf(" ")+1);
        return p1LastName.compareTo(p2LastName);
    }
}
