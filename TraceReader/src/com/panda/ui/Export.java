/*    */ package com.panda.ui;
/*    */ 
/*    */ import com.panda.trace.BytesHelper;
/*    */ import com.panda.trace.MethodLog;
/*    */ import com.panda.trace.ThreadList;
/*    */ import com.panda.trace.Trace;
/*    */ import com.panda.trace.TraceThread;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ import java.io.RandomAccessFile;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Map.Entry;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ class Export
/*    */ {
/*    */   public static void main(String[] args)
/*    */   {
/* 22 */     System.out.println("args[0]:" + args[0]);
/*    */     try
/*    */     {
/* 25 */       byte[] bytes = BytesHelper.toByteArray(args[0]);
/* 26 */       Trace trace = new Trace(bytes);
/* 27 */       ThreadList list = trace.getThreadList();
/*    */       
/* 29 */       Iterator entries = list.getThreads().entrySet().iterator();
/*    */       
/*    */ 
/* 32 */       while (entries.hasNext())
/*    */       {
/* 34 */         Map.Entry entry = (Map.Entry)entries.next();
/* 35 */         TraceThread thread = (TraceThread)entry.getValue();
/*    */         
/* 37 */         RandomAccessFile output = new RandomAccessFile(args[0] + "_" + thread.getName().replaceAll("/", "_") + ".txt", "rw");
/* 38 */         output.writeBytes("=============================\n");
/* 39 */         output.writeBytes("Thread name = " + thread.getName() + ":\n");
/* 40 */         List<MethodLog> methods = thread.getMethods();
/* 41 */         for (MethodLog method : methods)
/*    */         {
/* 43 */           output.writeBytes(method.getOriginFullName() + "\n");
/*    */         }
/* 45 */         output.close();
/*    */       }
/*    */       
/* 48 */       System.out.println("export success");
/*    */     }
/*    */     catch (IOException e) {
/* 51 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }


/* Location:              /media/fangdegao/code/backup/tmp/Export.jar!/com/panda/ui/Export.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */