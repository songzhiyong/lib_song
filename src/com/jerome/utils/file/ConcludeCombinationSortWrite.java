package com.jerome.utils.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class ConcludeCombinationSortWrite {
 /**
  * 大数据排序合并
  * 
  * @param args
  */
 public static void main(String[] args) throws IOException {
  // 写入文件的路径
  String filePath = "D:\\456";
  // 切分文件的路径
  String sqlitFilePath = "D:\\456\\123";

  //数据的个数
  int CountNumbers=10000000;
  
  //子文件的个数
  int CountFile=10;
  
  //精度
  int countAccuracy=30*CountFile;
  
  long startNumber=System.currentTimeMillis();
  // 写入大数据文件
  WriteData(filePath,CountNumbers);
  System.out.println("存储完毕");
  
  // 将大数据文件切分到另外的十个小文件中
  sqlitFileDate(filePath, sqlitFilePath,CountFile);
  System.out.println("文件切割完毕！");

  // 把每个文件的数据进行排序
  singleFileDataSort(sqlitFilePath,CountFile);
  System.out.println("每个子文件排序完毕！");
  
  //精度调整，十个文件数据进行比较整合
  deathDataFile(filePath,sqlitFilePath,countAccuracy,CountFile);
  System.out.println("整合完毕");
  long stopNumber=System.currentTimeMillis();
  System.out.println("耗时"+(stopNumber-startNumber)/1000+"毫秒");
 }

 // 写入大数据文件
 public static void WriteData(String path,int CountNumbers) throws IOException {
  path = path + "\\12114.txt";
  FileWriter fs = new FileWriter(path);
  BufferedWriter fw=new BufferedWriter(fs);
  for (int i = 0; i < CountNumbers; i++) {
   fw.write(new Random().nextInt(Integer.MAX_VALUE) + "\r\n");
  }
  fw.close();
  fs.close();
  
 }

 // 将大数据文件切分到另外的十个小文件中
 public static void sqlitFileDate(String filepath, String sqlitPath,
   int CountFile) throws IOException {
  FileWriter fs = null;
  BufferedWriter fw=null;
  FileReader fr = new FileReader(filepath + "\\12114.txt");
  BufferedReader br = new BufferedReader(fr); // 读取获取整行数据
  
  int i = 1;
  LinkedList WriterLists=new LinkedList();    //初始化文件流对象集合
  LinkedList fwLists=new LinkedList();
  for (int j = 1; j <= CountFile; j++) {
   
    //声明对象
    fs = new FileWriter(sqlitPath + "\\12" + j + ".txt",false);
    fw=new BufferedWriter(fs);
    
    
    //将对象装入集合
    WriterLists.add(fs);
    fwLists.add(fw);
  }
  //判断是文件流中是否还有数据返回
  while (br.ready()) {
   
   int count=1;//初始化第一文件流
   for (Iterator iterator = fwLists.iterator(); iterator.hasNext();) {
    BufferedWriter type = (BufferedWriter) iterator.next();
    if(i==count)//判断轮到第几个文件流写入数据了
    {
     //写入数据，跳出，进行下一个文件流，下一个数据的写入
     type.write(br.readLine() + "\r\n");
     break;
    }
    count++;
   }
   //判断是否到了最后一个文件流了
   if (i >= CountFile) {
    i = 1;
   } else
    i++;
  }
  br.close();
  fr.close();
  for (Iterator iterator = fwLists.iterator(); iterator.hasNext();) {
   BufferedWriter object = (BufferedWriter) iterator.next();
   object.close();
  }
  //遍历关闭所有子文件流
  for (Iterator iterator = WriterLists.iterator(); iterator.hasNext();) {
   FileWriter object = (FileWriter) iterator.next();
   object.close();
  }
 }

 // 把每个文件的数据进行排序
 public static void singleFileDataSort(String path1,int CountFile) throws IOException {
  LinkedList nums = null;
  for (int i = 1; i <= CountFile; i++) {
   nums = new LinkedList();
   String path = path1 + "\\12" + i + ".txt";
   try {
    FileReader fr = new FileReader(path);
    BufferedReader br = new BufferedReader(fr);
    while (br.ready()) {
     // 将读取的单个数据加入到集合里面
     nums.add(Integer.parseInt(br.readLine()));
    }
    // 对集合进行排序
    Collections.sort(nums);

    // 将排序好的数据写入源文件
    numberSort(nums, path);
    br.close();
    fr.close();
   } catch (NumberFormatException e) {
    e.printStackTrace();
   } catch (FileNotFoundException e) {
    e.printStackTrace();
   } catch (IOException e) {
    e.printStackTrace();
   }
  }
 }

 // 对每个文件数据进行排序，再写入源文件
 public static void numberSort(LinkedList list, String path) {
  try {
   FileWriter fs = new FileWriter(path);
   BufferedWriter fw=new BufferedWriter(fs);
   for (Iterator iterator = list.iterator(); iterator.hasNext();) {
    Object object = (Object) iterator.next();
    fw.write(object + "\r\n");
   }
   fw.close();
   fs.close();
  } catch (IOException e) {
   e.printStackTrace();
  }
 }

 // 文件数据最终整合(精度调整)
 public static void deathDataFile(String filepath, String sqlitFilePath1,
   int countAccuracy, int CountFile) throws IOException {
  LinkedList nums = new LinkedList();                                       //添加数据，进行排序
  Object temp = null;                                                       // 记录每次排序剩下的最后一个数字
  boolean ispass = false;
  LinkedList ispasses = null;                                               //记录数据文件的状态信息
  FileWriter fs = new FileWriter(filepath + "\\Sort.txt", false);           //创建文件流，以便整合的数据写入
  BufferedWriter bw=new BufferedWriter(fs);
  FileReader fr = null;                                                     //声明读取文件流
  BufferedReader br = null;                                                 //声明BufferedReader
  LinkedList WriterLists = new LinkedList(); // 初始化文件流对象集合
  LinkedList WriterListFile = new LinkedList();
  for (int j = 1; j <= CountFile; j++) {

   // 声明对象，开启所有子文件流访问所有子文件的数据
   fr = new FileReader(sqlitFilePath1 + "\\12" + j + ".txt");
   
   //开启所有BufferedReader，方便下次的整行的读取
   br = new BufferedReader(fr);
   
   // 将所有 FileReader对象装入集合
   WriterListFile.add(fr);
   
   // 将所有 BufferedReader对象装入集合
   WriterLists.add(br);
  }

  for (;;) {

   // 将十个源文件的是否有数据情况存入集合，以方便后面做判断
   ispasses = new LinkedList();
   
   // 分别读取十个源文件的单个数据
   for (Iterator iterator = WriterLists.iterator(); iterator.hasNext();) {
    BufferedReader object = (BufferedReader) iterator.next();
    Object obj = null;
    while (object.ready()) {
     //添加所有文件流的每次的数据
     nums.add(Integer.parseInt(object.readLine().toString()));
     break;
    }
    if (object.ready() == false)
     ispasses.add("true");           //将各文件中的数据状态存入集合中
   }
   
   // 决断是否是第一次进来
   if (nums.size() % countAccuracy == 0 && ispass == false) {

    // 对集合进行排序
    Collections.sort(nums);

    // 接收最大的数据，其它的数据写入总排序文件
    temp = numberSortData(nums, filepath, false, countAccuracy, bw);
    
    //重新初始化集合
    nums = new LinkedList();

    // 添加上一组比较剩下的数据
    nums.add(temp);
    ispass = true;

    // 记录源文件的数据数量，以便下次的遍历
    continue;
   }
   if (ispass) {
    if (nums.size() % countAccuracy == 1 && nums.size() > 1) {
     // 对集合进行排序
     Collections.sort(nums);

     // 接收最大的数据，其它的数据写入总排序文件
     temp = numberSortData(nums, filepath, true, countAccuracy,
       bw);
     nums = new LinkedList();
     nums.add(temp);
     continue;
    }

   }
   // 记录下一组数据的位置

   // 判断是不是十个文件都没有数据
   if (ispasses.size() == CountFile) {
    Collections.sort(nums);
    temp = numberSortData(nums, filepath, true, countAccuracy, bw);
    nums = new LinkedList();
    break;
   }
  }

  bw.close();
  //关闭写入流
  fs.close();
  
  //关闭所有的BufferedReader
  for (Iterator iterator = WriterLists.iterator(); iterator.hasNext();) {
   BufferedReader object2 = (BufferedReader) iterator.next();
   object2.close();
  }
  
  //关闭所有的FileReader
  for (Iterator iterator = WriterListFile.iterator(); iterator.hasNext();) {
   FileReader object = (FileReader) iterator.next();
   object.close();
  }
 }

 // 对数据进行排序,写入最终文件中(精度调整)
 public static Object numberSortData(LinkedList list, String filePath,
   boolean ispass, int countAccuracy,BufferedWriter fs) {
  Object temp = 0;                                                        //记录最后一个值
  int tempCount = 0;                                                      //记录写入的数据位置
  try {
   for (Iterator iterator = list.iterator(); iterator.hasNext();) {
    Object object = (Object) iterator.next();

    // 判断是否是最后一个数
    if (tempCount == list.size() - 1) {
     // 判断集合里面不足一百個數了
     if (list.size() < countAccuracy + 1 && ispass) {
      temp = null;
     } else {
      temp = object;
      break;
     }
    }

    // 写入数据源
    fs.write(object + "\r\n");

    // 记录数据的下标
    tempCount++;
   }
  } catch (IOException e) {
   e.printStackTrace();
  }
  return temp;
 }
}