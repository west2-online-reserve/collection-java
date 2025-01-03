package lib;/*    */
/*    */ 
/*    */ import java.io.FileReader;
/*    */ import java.io.Reader;
/*    */ 
/*    */ public class GetText
/*    */ {
/*    */   public static String getText(String url) {
/*    */     String s0;
/* 10 */     StringBuilder sb = new StringBuilder();
/* 11 */     try (Reader fileReader = new FileReader(url)) {
/*    */       int i;
/* 13 */       while ((i = fileReader.read()) != -1) {
/* 14 */         sb.append((char)i);
/*    */       }
/* 16 */       s0 = sb.toString();
/* 17 */     } catch (Exception e) {
/* 18 */       throw new RuntimeException(e);
/*    */     } 
/* 20 */     return s0;
/*    */   }
/*    */ }


/* Location:              C:\Users\27705\Desktop\WestOnlineTask2-1.0-SNAPSHOT-jar-with-dependencies.jar!\com\westonline\lib\lib.GetText.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */