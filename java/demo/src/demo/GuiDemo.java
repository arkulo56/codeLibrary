package demo;

import java.awt.*;

class GuiDemo{

      public static void main(String args[]){

       Frame frame=new Frame("第一个图形用户界面应用程序");

       Label lbl=new Label("这是我的第一个图形用户界面！");

       lbl.setBackground(Color.pink);

       frame.setLayout(new FlowLayout());

       frame.add(lbl);     

       frame.setSize(200,100);

       frame.setVisible(true);

      }

}