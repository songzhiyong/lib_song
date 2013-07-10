package com.jerome.utils.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.Writer;

public class CreateFile {
 public static void main(String[] args) {
  // readerFile("E:/workspace6/Arrays/src/myArray/Nodes.java");
//  readerFileToo();
  //writeInMemroy();
  writeFile2();
 }

 // 经典读取数据一
 public static String readerFile() {
  // E:/workspace6/Arrays/src/myArray/Nodes.java
  String s, s2 = "";
  try {
   BufferedReader in = new BufferedReader(new FileReader
     ("E:/workspace6/Arrays/src/myArray/Nodes.java"));
   try {
    while ((s = in.readLine()) != null) {
     s2 += s + "\n";
    }
    in.close();
    // System.out.println(s2);
   } catch (IOException e) {
    e.printStackTrace();
   }
  } catch (FileNotFoundException e) {
   e.printStackTrace();
  }
  return s2;
 }

 // 输入即返回
 public static void readerFileToo() {
  do {
   BufferedReader brIn = new BufferedReader(new InputStreamReader(
     System.in));
   System.out.println("Enter a line:");
   try {
    System.out.println(brIn.readLine().concat("+++++++"));
   } catch (IOException e) {
    e.printStackTrace();
   }
  } while (true);
 }

 // 写入到内存(单个字符占一行)
 public static void writeInMemroy() {
  StringReader sr = new StringReader(readerFile());
  int c;
  try {
   while ((c = sr.read()) != -1) {
    System.out.println((char)c);
   }
  } catch (IOException e) {
   e.printStackTrace();
  }
 }
 
 //文件写入  
 public static void writeFile2(){
  BufferedReader brWrite = new BufferedReader(new StringReader(readerFile()));
  PrintWriter pwOut = new PrintWriter(new BufferedWriter(FileWriter("IODemo.out")));
  int lintCount = 1;
  String s = "";
  try {
   while((s = brWrite.readLine()) != null){
    pwOut.println(lintCount+"--->"+s);
   }
  } catch (IOException e) {
   e.printStackTrace();
  }
  pwOut.close();
 }

 private static Writer FileWriter(String string) {
  return null;
 }

 public static void writeFile() {
  File file = new File("C：/Sound.txt");
  FileOutputStream fos = null;
  FileInputStream fis = null;
  try {
   file.createNewFile();
   fos = new FileOutputStream(file);
   fos.write(44646);
   fos.close();
   fis = new FileInputStream(file);
   System.out.println(fis.read());
   fis.close();
  } catch (IOException e) {
   e.printStackTrace();
  }
 }
}