#NullPointerException
##情况1 
使用**Arrays.sort**时数组的元素没有**都**赋值，可能出现该报错
``````java
 ProductPractice[] A = new ProductPractice[5];
        A[0] = new ProductPractice("aa",21);
        A[1] = new ProductPractice("bb",44);
        A[2] = new ProductPractice("cc",55);
        A[3] = new ProductPractice("dd",66);
        Arrays.sort(A);
``````