package com.imooc.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

//课程列表
public class CourseList {
	public List<Course> courseList;
	
	public CourseList()
	{
		courseList = new ArrayList<Course>();
	}
	public boolean add(Course c)
	{
		if(courseList.add(c))
		{
			return true;
		}else
		{
			return false;
		}
	}
	public Course del(int index)
	{
		Course d = courseList.remove(index);
		return d;
	}
	private void prt()
	{
		for(Course i:courseList)
		{
			System.out.println("课程："+i.id+":"+i.name);
		}
	}
	public String getByName(String name)
	{
		Iterator<Course> iter = courseList.iterator();
		while(iter.hasNext())
		{
			Course c = iter.next();
			if(c.name.equals(name))
			{
				System.out.println("查询到课程"+name+"的ID为:"+c.id);
				return c.id;
			}
		}
		return "0";
	}
	
	
	public static void main(String[] argc)
	{
		System.out.println("请您录入课程信息：");
		Scanner in = new Scanner(System.in);
		CourseList cl = new CourseList();
		while(true)
		{
			System.out.println("请录入课程编号或者exit退出：");
			String input = in.nextLine();
			if(input.equals("exit")) break;
			System.out.println("请输入课程名称：");
			String name = in.nextLine();
			Course newOne = new Course(input, name);
			if(cl.add(newOne))
			{
				System.out.println("添加课程"+newOne.name+"成功！");
			}else
			{
				System.out.println("添加课程"+newOne.name+"失败！");
			}
		}
		cl.prt();
		
		//根据一个课程名称查询
		String res = cl.getByName("离散数学");
		int result = Integer.parseInt(res);
		if(result!=0)
		{
			System.out.println("离散数学的编号是："+result);
		}else
		{
			System.out.println("抱歉！没有找到离散数学！");
		}
		
	}
	
}
