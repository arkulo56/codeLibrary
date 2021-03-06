package view;

/*女神系统*/
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import action.GoddessAction;
import model.Goddess;

public class GoddessView {
	public static void main(String[] args) throws SQLException {
		prt();
		
		Scanner in = new Scanner(System.in);
		GoddessAction ac = new GoddessAction();
		Goddess newGoddes = new Goddess();
		int step = 0;
		int stage = 0;
		while(true){
			String input = in.nextLine();
			//获取所有女神信息
			if(input.equals("a") || input.equals("A"))
			{
				System.out.println("所有女生信息如下：");
				java.util.List<Goddess> res = ac.getGoddessAll();
				for(int i=0;i<res.size();i++)
				{
					System.out.println(res.get(i).getId()+"/"+res.get(i).getName()+"/"+res.get(i).getAge()+"/"+res.get(i).getPhone());
				}
				
			}
			//获取某个女神信息
			if(input.equals("g") || input.equals("G") || stage==1)
			{
				stage=1;
				System.out.println("请输入女神姓名：");
				String name = in.nextLine();
				List<Map<String, String>> wh = new ArrayList();
				Map<String, String> ch = new HashMap<String,String>();
				ch.put("name", "name");
				ch.put("relation", "=");
				ch.put("value", "'"+name+"'");
				wh.add(ch);
				Goddess res = ac.getGoddess(wh);
				if(res.getId()>0)
				{
					System.out.println(res.getId()+"/"+res.getName()+"/"+res.getAge()+"/"+res.getPhone());
				}else
				{
					System.out.println("没有查到该女神信息");
				}
				stage=0;
			}

			//添加一位女神
			if(input.equals("t") || input.equals("T") || stage==2)
			{
				stage=2;		
				if(step==0)
				{
					System.out.println("请输入女神姓名:");
					String name2 = in.nextLine();
					newGoddes.setName(name2);
					step=1;
				}
				if(step==1)
				{
					System.out.println("请输入女神年龄:");
					String age = in.nextLine();
					int ageInt = Integer.parseInt(age);
					newGoddes.setAge(ageInt);
					step=2;
				}
				if(step==2)
				{
					System.out.println("请输入女生手机号：");
					String phone = in.nextLine();
					newGoddes.setPhone(phone);
					step=3;
				}
				if(step==3)
				{
					newGoddes.setAddtime(new Date());
					if(ac.addGoddess(newGoddes))
					{
						System.out.println("添加女神成功!");
					}else
					{
						System.out.println("添加女神失败!");
					}
					stage=0;
					step=0;
				}
			}
			//删除女神
			if(input.equals("d") || input.equals("D"))
			{
				System.out.println("请输入要删除的女神编号：");
				String id = in.nextLine();
				int ids = Integer.parseInt(id);
				if(ac.delGoddess(ids))
				{
					System.out.println("删除女神成功!");
				}else
				{
					System.out.println("删除女神失败!");
				}
				
			}
			//修改女神信息
			if(input.equals("u") || input.equals("U") || stage==3)
			{
				stage=3;
				if(step==0)
				{
					System.out.println("请输入女神姓名：");
					String name = in.nextLine();
					List<Map<String, String>> wh = new ArrayList();
					Map<String, String> ch = new HashMap<String,String>();
					ch.put("name", "name");
					ch.put("relation", "=");
					ch.put("value", "'"+name+"'");
					wh.add(ch);
					newGoddes = ac.getGoddess(wh);
					if(newGoddes.getId()<=0)
					{
						System.out.println("没有该女神！");
						stage=0;
						step=0;
						continue;
					}
					step=1;
				}
				if(step==1)
				{
					System.out.println("女神姓名'"+newGoddes.getName()+"'要改为(直接回车跳过)：");
					String nvName = in.nextLine();
					if(!nvName.isEmpty())
					{
						newGoddes.setName(nvName);
					}
					step=2;
				}
				if(step==2)
				{
					System.out.println("女神年龄'"+newGoddes.getAge()+"'要改为：");
					String nvAge = in.nextLine();
					if(!nvAge.isEmpty())
					{
						int age = Integer.parseInt(nvAge);
						newGoddes.setAge(age); 
					}
					step=3;
				}
				if(step==3)
				{
					System.out.println("女神手机号'"+newGoddes.getPhone()+"'要改为：");
					String nvPhone = in.nextLine();
					if(!nvPhone.isEmpty())
					{
						newGoddes.setPhone(nvPhone); 
					}
					step=4;
				}
				if(step==4)
				{
					if(ac.modifyGoddess(newGoddes)>0)
					{
						System.out.println("女神信息修改成功！");
					}else
					{
						System.out.println("女神信息修改失败！");
					}
					stage=0;
					step=0;
				}
				
			}
			
			
			
			if(input.equals("r") || input.equals("R"))
			{
				prt();
			}
			if(input.equals("e") || input.equals("E"))
			{
				System.out.println("成功退出女神系统！");
				break;
			}
		}
	}
	
	public static void prt()
	{
		System.out.println("～～～～欢迎来到女神系统～～～～");
		System.out.println("查看所有女神信息A");
		System.out.println("查看某个女神信息G");
		System.out.println("添加一位女神T");
		System.out.println("删除一位女神D");
		System.out.println("修改女神信息U");
		System.out.println("离开女神系统E");
		System.out.println("重看菜单R");
		System.out.println("～～～～～～～～～～～～～～～～");
	}
	
}
