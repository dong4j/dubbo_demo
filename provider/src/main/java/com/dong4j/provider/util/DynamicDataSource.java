package com.dong4j.provider.util;

/**
 * Created by Code.Ai on 16/7/22.
 * Description:
 */
//public class DynamicDataSource extends AbstractRoutingDataSource{
//    private Lock lock             = new ReentrantLock();
//    private int  counter          = 0;
//
//    @Override
//    //protected Object determineCurrentLookupKey() {
//    //    return DataSourceHolder.getDataSource();
//    //}
//
//    protected Object determineCurrentLookupKey() {
//        lock.lock();
//        try{
//            counter++;
//            int dataSourceNumber = 3;
//            return counter % dataSourceNumber;
//        }finally{
//            lock.unlock();
//        }
//    }
//
//
//    public int getDataSourceNumber(){
//        return counter;
//    }
//}
