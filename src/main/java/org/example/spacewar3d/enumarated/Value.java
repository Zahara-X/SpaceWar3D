package org.example.spacewar3d.enumarated;

public enum Value {
     HOST("localhost"), PORT(7070);
     private final Object value;
     Value(Object value) {
         this.value = value;
     }
     public Object getHost() {
         if(value.equals(HOST.value))
             return value;
         return null;
     }
     public Object getPort() {
         if(value.equals(PORT.value))
             return value;
         return null;
     }
}