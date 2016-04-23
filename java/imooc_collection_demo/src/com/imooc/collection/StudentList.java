package com.imooc.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentList {
	public List<Student> studentList;
	
	public StudentList()
	{
		studentList = new ArrayList<Student>();
	}
	
	public boolean add(Student s)
	{
		if(studentList.add(s))
		{
			return true;
		}
		return false;
	}
	public Student del(int index)
	{
		Student ts = studentList.remove(index);
		return ts;
	}
	private void prt()
	{
		for(Student i:studentList)
		{
			System.out.println("学生："+i.id+":"+i.name);
		}
	}
	
	
	
	//测试入口
	public static void main(String[] argc)
	{
		StudentList demo = new StudentList();
		Scanner in = new Scanner(System.in);
		System.out.println("请添加学生列表:");
		while(true)
		{
			//循环输入学生id和姓名
			System.out.println("请输入学生编号或者exit退出：");
			String id = in.nextLine();
			if(id.equals("exit")) break;
			System.out.println("请输入学生姓名：");
			String name = in.nextLine();
			//生成student对象
			Student newStudent = new Student(id, name);
			//添加至学生List中
			if(demo.add(newStudent))
			{
				System.out.println("添加"+newStudent.name+"成功!");
			}else
			{
				System.out.println("添加"+newStudent.name+"失败!");
			}
		}
		demo.prt();
		
		while(true)
		{
			System.out.println("如果您想删除学生，请输入学生编号！或者exit退出！");
			String tmp = in.nextLine();
			if(tmp.equals("exit")) break;
			int index = Integer.parseInt(tmp);
			if(index < demo.studentList.size())
			{
				Student ds = demo.del(index);
				System.out.println("成功删除"+ds.name);
				demo.prt();
			}else
			{
				System.out.println("下标越界，请重新输入！");
			}
		}
			
	}
	
}
