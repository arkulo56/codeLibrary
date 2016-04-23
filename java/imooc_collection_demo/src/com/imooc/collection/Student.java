package com.imooc.collection;
/*学生类*/
import java.util.HashSet;
import java.util.Set;

public class Student {
	public String id;
	public String name;
	public Set courses;
	
	public Student(String id,String name)
	{
		this.id=id;
		this.name = name;
//		set是一个接口，所以不能直接赋值，还是需要实例化一个HashSet对象
		this.courses = new HashSet();
	}
}
