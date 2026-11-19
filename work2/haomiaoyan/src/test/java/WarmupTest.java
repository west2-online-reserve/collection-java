import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WarmupTest {
    @Test void 普通回文()   { assertTrue(Warmup.isPalindrome("racecar")); }
    @Test void 带空格标点() { assertTrue(Warmup.isPalindrome("A man, a plan, a canal: Panama")); }
        @Test void 空串()       { assertTrue(Warmup.isPalindrome(""));  }
        @Test void 非回文()     { assertFalse(Warmup.isPalindrome("hello")); }
        @Test void 数组反转()   { assertArrayEquals(new int[]{3,2,1}, Warmup.reverse(new int[]{1,2,3})); }
    }