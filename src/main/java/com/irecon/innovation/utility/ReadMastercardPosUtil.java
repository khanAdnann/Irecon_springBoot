package com.irecon.innovation.utility;
/**
 * @author Ankit INT12016 & Created by Ankit Kesarwani
 * 
 * 
 * @since May 4, 2025
 */




import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.irecon.innovation.services.MastercardPosRawDataService;
import com.irecon.innovation.services.Impl.MasterCardPosRawDataServiceImpl;
import com.irecon.innovation.services.Impl.MastercardPosToListSeviceImpl;


public class ReadMastercardPosUtil {
  int count = 0;
  
  Connection con;
  
  Statement st;
  
  int part_id;
  
  String man_flag = "N", upload_flag = "Y";
  
  String value = null;
  
	private static final Logger logger = LoggerFactory.getLogger(ReadMastercardPosUtil.class);

  public boolean uploadPOSData(MultipartFile file, String fileName, String fileDate) throws ParseException {
    String[] filenameSplit = fileName.split("_");
    DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    String filedt = null;
   
    String flag = "";
    String new_date = "";
    try {
      System.out.println(fileName);
      filedt = fileDate;
      flag = "UPLOAD_FLAG";
      this.upload_flag = "Y";
      this.part_id = 1;
   

      ConnectionUtil conn = new ConnectionUtil();
      if (uploadData(file, conn.getconn(), fileName, fileDate))
        System.out.println("datewwe " + fileDate); 
      return true;
    } catch (Exception e) {
      System.out.println("Erro Occured");
      e.printStackTrace();
      logger.error(e.getMessage());
      return false;
    } 
  }

  

  

  public boolean uploadData(MultipartFile file, Connection con, String filename, String new_date) {
    try {
      System.setProperty("file.encoding", "latin1");
      Field charset = Charset.class.getDeclaredField("defaultCharset");
      charset.setAccessible(true);
      charset.set(null, null);
      StringBuilder sb = new StringBuilder();
      BufferedReader bfrd = new BufferedReader(new InputStreamReader(file.getInputStream()));

      Scanner sc = new Scanner(bfrd);
      while (sc.hasNextLine()) {
        sc.useDelimiter(",//s");
        sb.append(sc.next());
      } 
      try {
        MastercardPosToListSeviceImpl to_List_Impl = new MastercardPosToListSeviceImpl();
        MastercardPosRawDataService rid = MasterCardPosRawDataServiceImpl.getInstance();
        MasterCardPosRawDataServiceImpl jcsv = (MasterCardPosRawDataServiceImpl)MasterCardPosRawDataServiceImpl.getInstance();
        rid._read1251(to_List_Impl.to_block(sb.toString()), filename, new_date);
      } catch (NullPointerException ne) {
        ne.printStackTrace();
        System.out.println("Inside null pointer exception");
        System.out.println("Complete");
        return true;
      } catch (Exception e) {
        e.printStackTrace();
        logger.error(e.getMessage());
        System.exit(0);
      } 
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
      logger.error(e.getMessage());
      System.exit(0);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      logger.error(e.getMessage());
      System.exit(0);
    } catch (IOException e) {
      e.printStackTrace();
      logger.error(e.getMessage());
      System.exit(0);
    } catch (SecurityException e1) {
      e1.printStackTrace();
      logger.error(e1.getMessage());
      System.exit(0);
    } catch (NoSuchFieldException e1) {
      e1.printStackTrace();
      logger.error(e1.getMessage());
      System.exit(0);
    } catch (IllegalAccessException e1) {
      e1.printStackTrace();
      logger.error(e1.getMessage());
      System.exit(0);
    } 
    return true;
  }
  
  
  
  public static boolean read_method(String fileDate, MultipartFile file) {
    try {
      ReadMastercardPosUtil readcbs = new ReadMastercardPosUtil();
      String filename = file.getOriginalFilename();
      System.out.println(file.getName());
      readcbs.uploadPOSData(file, filename, fileDate );
      System.out.println("Process Completed");
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    } 
  }
}
