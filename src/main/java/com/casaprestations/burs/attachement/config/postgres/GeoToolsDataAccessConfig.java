package com.casaprestations.burs.attachement.config.postgres;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.geotools.data.postgis.PostgisNGDataStoreFactory;
import org.geotools.jdbc.JDBCDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:geotools.properties")

public class GeoToolsDataAccessConfig {

 @Autowired
 Environment env;

 @Bean
 public JDBCDataStore postGisDataStore() {
  PostgisNGDataStoreFactory postGisDataStoreFactory = new PostgisNGDataStoreFactory();
  Map<String, String> connexionParams = new HashMap<String, String>();
  //connexionParams.put(PostgisNGDataStoreFactory.DBTYPE.key, "postgis");
  connexionParams.put(PostgisNGDataStoreFactory.HOST.key, env.getProperty("geotools.db.host"));
  connexionParams.put(PostgisNGDataStoreFactory.PORT.key, env.getProperty("geotools.db.port"));
  connexionParams.put(PostgisNGDataStoreFactory.DATABASE.key, env.getProperty("geotools.db.database"));
  connexionParams.put(PostgisNGDataStoreFactory.USER.key, env.getProperty("geotools.db.username"));
  connexionParams.put(PostgisNGDataStoreFactory.PASSWD.key, env.getProperty("geotools.db.password"));

  JDBCDataStore postGisDataStore = null;
  try {
   postGisDataStore = postGisDataStoreFactory.createDataStore(connexionParams);
  } catch (IOException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  return postGisDataStore;
 }

}