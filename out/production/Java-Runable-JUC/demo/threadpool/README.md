# 线程窜创建
newSingleThreadExecutor：单线程池，同时只有一个线程在跑。   
newCachedThreadPool() ：回收型线程池，可以重复利用之前创建过的线程，运行线程最大数是Integer.MAX_VALUE。   
newFixedThreadPool() ：固定大小的线程池，跟回收型线程池类似，只是可以限制同时运行的线程数量   
newScheduledThreadPool： 循坏，延迟任务