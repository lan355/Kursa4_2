package ua.cours.classes.task;

public class Core {
   public boolean isFree=true;
   public Process currentProcess = null;

   public String getState()
   {
      String _temp = " ";
      if(this.isFree)
         _temp+="is free";
      else
         _temp+= "Working on Process: " + currentProcess;


      return _temp ;
   }

   public Process getCurrentProcess()
   {
      return currentProcess;
   }
}
