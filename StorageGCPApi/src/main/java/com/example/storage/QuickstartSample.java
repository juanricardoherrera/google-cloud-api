/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.storage;

import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

/**
 *
 * @author juan.herrera
 */
public class QuickstartSample {
    
    public static void main(String ... args){
        
        Storage storage = StorageOptions.getDefaultInstance().getService();
        
        //String bucketName = args[0];
        String bucketName = "bucket-test-my-ap";
        
        if(!existBucket(bucketName)){
            Bucket bucket = storage.create(BucketInfo.of(bucketName));
            System.out.printf("Bucket %s created.%n", bucket.getName());
        }else{
            System.out.printf("Bucket %s already exist.%n", bucketName);
        }

    }
    
    public static boolean existBucket(String bucketName){
        
        Storage storage = StorageOptions.getDefaultInstance().getService();
        
        Page<Bucket> buckets = storage.list();
        for(Bucket bucket : buckets.iterateAll()){
            if(bucket.getName().equals(bucketName))
                return true;
        }
        return false;
    }
    
}
